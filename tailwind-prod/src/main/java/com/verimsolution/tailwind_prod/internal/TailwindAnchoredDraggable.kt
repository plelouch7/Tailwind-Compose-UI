package com.verimsolution.tailwind_prod

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.gestures.DragScope
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * Interface pour représenter les ancres dans TailwindAnchoredDraggable.
 */
interface TailwindDraggableAnchors<T> {
    fun positionOf(value: T): Float
    fun hasAnchorFor(value: T): Boolean
    fun closestAnchor(position: Float): T?
    fun closestAnchor(position: Float, searchUpwards: Boolean): T?
    fun minAnchor(): Float
    fun maxAnchor(): Float
    val size: Int
}

/**
 * Configuration mutable des ancres.
 */
class TailwindDraggableAnchorsConfig<T> {
    internal val anchors = mutableMapOf<T, Float>()
    infix fun T.at(position: Float) {
        anchors[this] = position
    }
}

/**
 * Crée une instance de TailwindDraggableAnchors.
 */
fun <T : Any> TailwindDraggableAnchors(
    builder: TailwindDraggableAnchorsConfig<T>.() -> Unit
): TailwindDraggableAnchors<T> = MapTailwindDraggableAnchors(
    TailwindDraggableAnchorsConfig<T>().apply(builder).anchors
)

/**
 * Modificateur pour activer le glissement avec ancres.
 */
fun <T> Modifier.tailwindAnchoredDraggable(
    state: TailwindAnchoredDraggableState<T>,
    orientation: Orientation,
    enabled: Boolean = true,
    reverseDirection: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) = this.draggable(
    state = state.draggableState,
    orientation = orientation,
    enabled = enabled,
    interactionSource = interactionSource,
    reverseDirection = reverseDirection,
    startDragImmediately = state.isAnimationRunning,
    onDragStopped = { velocity -> launch { state.settle(velocity) } }
)

/**
 * Portée pour le glissement avec ancres.
 */
interface TailwindAnchoredDragScope {
    fun dragTo(newOffset: Float, lastKnownVelocity: Float = 0f)
}

/**
 * État pour gérer le glissement avec ancres.
 */
class TailwindAnchoredDraggableState<T>(
    initialValue: T,
    private val density: Density,
    internal val positionalThreshold: (totalDistance: Float) -> Float = { it * 0.5f },
    internal val velocityThreshold: () -> Float = { with(density) { 400.dp.toPx() } },
    val animationSpec: AnimationSpec<Float> = tween(durationMillis = 256),
    internal val confirmValueChange: (newValue: T) -> Boolean = { true }
) {
    constructor(
        initialValue: T,
        anchors: TailwindDraggableAnchors<T>,
        density: Density,
        positionalThreshold: (totalDistance: Float) -> Float = { it * 0.5f },
        velocityThreshold: () -> Float = { with(density) { 400.dp.toPx() } },
        animationSpec: AnimationSpec<Float> = tween(durationMillis = 256),
        confirmValueChange: (newValue: T) -> Boolean = { true }
    ) : this(initialValue, density, positionalThreshold, velocityThreshold, animationSpec, confirmValueChange) {
        this.anchors = anchors
        trySnapTo(initialValue)
    }

    private val dragMutex = InternalMutatorMutex()

    internal val draggableState = object : DraggableState {
        private val dragScope = object : DragScope {
            override fun dragBy(pixels: Float) {
                with(anchoredDragScope) { dragTo(newOffsetForDelta(pixels)) }
            }
        }

        override suspend fun drag(
            dragPriority: MutatePriority,
            block: suspend DragScope.() -> Unit
        ) {
            this@TailwindAnchoredDraggableState.anchoredDrag { with(dragScope) { block() } }
        }

        override fun dispatchRawDelta(delta: Float) {
            this@TailwindAnchoredDraggableState.dispatchRawDelta(delta)
        }
    }

    var currentValue: T by mutableStateOf(initialValue)
        private set

    var offset: Float by mutableFloatStateOf(Float.NaN)
        private set

    fun requireOffset(): Float {
        check(!offset.isNaN()) { "Offset not initialized. Ensure anchors are set." }
        return offset
    }

    val isAnimationRunning: Boolean
        get() = dragTarget != null

    var lastVelocity: Float by mutableFloatStateOf(0f)
        private set

    private var dragTarget: T? by mutableStateOf(null)

    var anchors: TailwindDraggableAnchors<T> by mutableStateOf(emptyTailwindDraggableAnchors())
        private set

    val targetValue: T
        get() = dragTarget ?: run {
            val currentOffset = offset
            if (!currentOffset.isNaN()) computeTarget(currentOffset, currentValue, 0f) else currentValue
        }

    fun updateAnchors(newAnchors: TailwindDraggableAnchors<T>, newTarget: T = targetValue) {
        if (anchors != newAnchors) {
            anchors = newAnchors
            val snapSuccessful = trySnapTo(newTarget)
            if (!snapSuccessful) dragTarget = newTarget
        }
    }

    suspend fun settle(velocity: Float) {
        val previousValue = this.currentValue
        val targetValue = computeTarget(offset = requireOffset(), currentValue = previousValue, velocity = velocity)
        if (confirmValueChange(targetValue)) {
            animateTo(targetValue, velocity)
        } else {
            animateTo(previousValue, velocity)
        }
    }

    private fun computeTarget(offset: Float, currentValue: T, velocity: Float): T {
        val currentAnchorPosition = anchors.positionOf(currentValue)
        val velocityThresholdPx = velocityThreshold()
        return if (currentAnchorPosition == offset || currentAnchorPosition.isNaN()) {
            currentValue
        } else if (currentAnchorPosition < offset) {
            if (velocity >= velocityThresholdPx) {
                anchors.closestAnchor(offset, true) ?: currentValue
            } else {
                val upper = anchors.closestAnchor(offset, true) ?: return currentValue
                val distance = abs(anchors.positionOf(upper) - currentAnchorPosition)
                val relativeThreshold = abs(positionalThreshold(distance))
                val absoluteThreshold = abs(currentAnchorPosition + relativeThreshold)
                if (offset < absoluteThreshold) currentValue else upper
            }
        } else {
            if (velocity <= -velocityThresholdPx) {
                anchors.closestAnchor(offset, false) ?: currentValue
            } else {
                val lower = anchors.closestAnchor(offset, false) ?: return currentValue
                val distance = abs(currentAnchorPosition - anchors.positionOf(lower))
                val relativeThreshold = abs(positionalThreshold(distance))
                val absoluteThreshold = abs(currentAnchorPosition - relativeThreshold)
                if (offset < 0) {
                    if (abs(offset) < absoluteThreshold) currentValue else lower
                } else {
                    if (offset > absoluteThreshold) currentValue else lower
                }
            }
        }
    }

    private val anchoredDragScope: TailwindAnchoredDragScope = object : TailwindAnchoredDragScope {
        override fun dragTo(newOffset: Float, lastKnownVelocity: Float) {
            offset = newOffset.coerceIn(anchors.minAnchor(), anchors.maxAnchor())
            lastVelocity = lastKnownVelocity
        }
    }

    suspend fun anchoredDrag(block: suspend TailwindAnchoredDragScope.(TailwindDraggableAnchors<T>) -> Unit) {
        try {
            dragMutex.mutate {
                anchoredDragScope.block(anchors)
            }
        } finally {
            val closest = anchors.closestAnchor(offset)
            if (closest != null && abs(offset - anchors.positionOf(closest)) <= 0.5f && confirmValueChange(closest)) {
                currentValue = closest
            }
        }
    }

    suspend fun anchoredDrag(
        targetValue: T,
        block: suspend TailwindAnchoredDragScope.(TailwindDraggableAnchors<T>, T) -> Unit
    ) {
        if (anchors.hasAnchorFor(targetValue)) {
            try {
                dragMutex.mutate {
                    dragTarget = targetValue
                    anchoredDragScope.block(anchors, targetValue)
                }
            } finally {
                dragTarget = null
                val closest = anchors.closestAnchor(offset)
                if (closest != null && abs(offset - anchors.positionOf(closest)) <= 0.5f && confirmValueChange(closest)) {
                    currentValue = closest
                }
            }
        } else {
            currentValue = targetValue
        }
    }

    fun newOffsetForDelta(delta: Float) = ((if (offset.isNaN()) 0f else offset) + delta).coerceIn(
        anchors.minAnchor(),
        anchors.maxAnchor()
    )

    fun dispatchRawDelta(delta: Float): Float {
        val newOffset = newOffsetForDelta(delta)
        val oldOffset = if (offset.isNaN()) 0f else offset
        offset = newOffset
        return newOffset - oldOffset
    }

    private fun trySnapTo(targetValue: T): Boolean = dragMutex.tryMutate {
        with(anchoredDragScope) {
            val targetOffset = anchors.positionOf(targetValue)
            if (!targetOffset.isNaN()) {
                dragTo(targetOffset)
                dragTarget = null
            }
            currentValue = targetValue
        }
    }
}

/**
 * Snap immédiatement à une valeur cible.
 */
suspend fun <T> TailwindAnchoredDraggableState<T>.snapTo(targetValue: T) {
    anchoredDrag(targetValue) { anchors, latestTarget ->
        val targetOffset = anchors.positionOf(latestTarget)
        if (!targetOffset.isNaN()) dragTo(targetOffset)
    }
}

/**
 * Anime vers une valeur cible.
 */
suspend fun <T> TailwindAnchoredDraggableState<T>.animateTo(targetValue: T, velocity: Float = lastVelocity) {
    anchoredDrag(targetValue) { anchors, latestTarget ->
        val targetOffset = anchors.positionOf(latestTarget)
        if (!targetOffset.isNaN()) {
            var prev = if (offset.isNaN()) 0f else offset
            androidx.compose.animation.core.animate(prev, targetOffset, velocity, animationSpec) { value, velocity ->
                dragTo(value, velocity)
                prev = value
            }
        }
    }
}

/**
 * Crée et mémorise un TailwindAnchoredDraggableState.
 */
@Composable
fun <T> rememberTailwindAnchoredDraggableState(
    initialValue: T,
    density: Density = LocalDensity.current,
    animationSpec: AnimationSpec<Float> = tween(durationMillis = 256),
    confirmValueChange: (T) -> Boolean = { true }
): TailwindAnchoredDraggableState<T> {
    return remember { TailwindAnchoredDraggableState(initialValue, density, animationSpec = animationSpec, confirmValueChange = confirmValueChange) }
}

/**
 * Implémentation par défaut des ancres basée sur une Map.
 */
private class MapTailwindDraggableAnchors<T>(private val anchors: Map<T, Float>) : TailwindDraggableAnchors<T> {
    override fun positionOf(value: T): Float = anchors[value] ?: Float.NaN
    override fun hasAnchorFor(value: T): Boolean = anchors.containsKey(value)
    override fun closestAnchor(position: Float): T? = anchors.minByOrNull { abs(position - it.value) }?.key
    override fun closestAnchor(position: Float, searchUpwards: Boolean): T? = anchors
        .minByOrNull { (_, anchor) ->
            val delta = if (searchUpwards) anchor - position else position - anchor
            if (delta < 0) Float.POSITIVE_INFINITY else delta
        }?.key
    override fun minAnchor(): Float = anchors.values.minOrNull() ?: Float.NEGATIVE_INFINITY
    override fun maxAnchor(): Float = anchors.values.maxOrNull() ?: Float.POSITIVE_INFINITY
    override val size: Int get() = anchors.size
}

/**
 * Ancres vides par défaut.
 */
private fun <T> emptyTailwindDraggableAnchors() = MapTailwindDraggableAnchors<T>(emptyMap())

/**
 * Mutex interne pour gérer les opérations concurrentes.
 */
internal class InternalMutatorMutex {
    private var currentJob: Job? by mutableStateOf(null)
    private var isLocked by mutableStateOf(false)

    @OptIn(InternalCoroutinesApi::class)
    suspend fun <R> mutate(block: suspend () -> R): R = coroutineScope {
        while (isLocked) {
            currentJob?.join() // Attend que l'opération en cours se termine
        }
        isLocked = true
        val job = launch {
            try {
                block()
            } finally {
                isLocked = false
            }
        }
        currentJob = job
        job.join()
        @Suppress("UNCHECKED_CAST")
        job.invokeOnCompletion { job.getCancellationException() }
        block()
    }

    fun tryMutate(block: () -> Unit): Boolean {
        if (!isLocked) {
            block()
            return true
        }
        return false
    }
}