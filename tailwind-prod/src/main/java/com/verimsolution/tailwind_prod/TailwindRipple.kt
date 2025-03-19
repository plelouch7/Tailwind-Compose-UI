package com.verimsolution.tailwind_prod

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ObserverModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateSubtree
import androidx.compose.ui.node.observeReads
import androidx.compose.ui.unit.Dp
import com.verimsolution.tailwind_prod.tokens.StateTokens
import kotlinx.coroutines.launch
import kotlin.math.max

/**
 * Crée un effet ripple animé pour le thème Tailwind CSS, inspiré de Material3.
 *
 * @param bounded Si true, le ripple est limité aux bordures et part de la position du clic ; sinon, il part du centre.
 * @param radius Rayon maximal du ripple (par défaut : basé sur la taille de l'élément).
 * @param color Couleur du ripple (par défaut : basée sur le thème Tailwind).
 */
@Stable
fun ripple(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified
): IndicationNodeFactory {
    return if (radius == Dp.Unspecified && color == Color.Unspecified) {
        if (bounded) return DefaultBoundedRipple else DefaultUnboundedRipple
    } else {
        RippleNodeFactory(bounded, radius, color)
    }
}

/**
 * Crée un effet ripple avec une couleur dynamique.
 *
 * @param color Couleur dynamique du ripple via [ColorProducer].
 * @param bounded Si true, le ripple est limité aux bordures.
 * @param radius Rayon maximal du ripple.
 */
@Stable
fun ripple(
    color: ColorProducer,
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified
): IndicationNodeFactory {
    return RippleNodeFactory(bounded, radius, color)
}

/** Default values used by [ripple]. */
object TailwindRippleDefaults {
    /**
     * Représente l'opacité par défaut pour différents états d'interaction.
     */
    val RippleAlpha: TailwindRippleAlpha = TailwindRippleAlpha(
        pressedAlpha = StateTokens.PressedStateLayerOpacity,
        focusedAlpha = StateTokens.FocusStateLayerOpacity,
        draggedAlpha = StateTokens.DraggedStateLayerOpacity,
        hoveredAlpha = StateTokens.HoverStateLayerOpacity
    )
}

/**
 * CompositionLocal temporaire pour basculer vers l'implémentation legacy (sera supprimé plus tard).
 */
val LocalUseFallbackRippleImplementation: ProvidableCompositionLocal<Boolean> =
    staticCompositionLocalOf {
        false
    }

/**
 * CompositionLocal pour fournir une configuration personnalisée des ripples Tailwind.
 */
val LocalTailwindRippleConfig: ProvidableCompositionLocal<TailwindRippleConfiguration?> =
    compositionLocalOf {
        TailwindRippleConfiguration()
    }

/**
 * Configuration pour l'apparence du ripple Tailwind.
 *
 * @param color Couleur override pour le ripple.
 * @param rippleAlpha Opacité override pour le ripple.
 */
@Immutable
class TailwindRippleConfiguration(
    val color: Color = Color.Unspecified,
    val rippleAlpha: TailwindRippleAlpha? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TailwindRippleConfiguration) return false

        if (color != other.color) return false
        if (rippleAlpha != other.rippleAlpha) return false

        return true
    }

    override fun hashCode(): Int {
        var result = color.hashCode()
        result = 31 * result + (rippleAlpha?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "TailwindRippleConfiguration(color=$color, rippleAlpha=$rippleAlpha)"
    }
}

/**
 * Factory pour créer des noeuds de ripple Tailwind.
 */
@Stable
private class RippleNodeFactory
private constructor(
    private val bounded: Boolean,
    private val radius: Dp,
    private val colorProducer: ColorProducer?,
    private val color: Color
) : IndicationNodeFactory {
    constructor(
        bounded: Boolean,
        radius: Dp,
        colorProducer: ColorProducer
    ) : this(bounded, radius, colorProducer, Color.Unspecified)

    constructor(bounded: Boolean, radius: Dp, color: Color) : this(bounded, radius, null, color)

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        val colorProducer = colorProducer ?: ColorProducer { color }
        return DelegatingTailwindRippleNode(interactionSource, bounded, radius, colorProducer)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RippleNodeFactory) return false

        if (bounded != other.bounded) return false
        if (radius != other.radius) return false
        if (colorProducer != other.colorProducer) return false
        return color == other.color
    }

    override fun hashCode(): Int {
        var result = bounded.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + colorProducer.hashCode()
        result = 31 * result + color.hashCode()
        return result
    }
}

/**
 * Noeud personnalisé pour gérer la configuration et déléguer le dessin du ripple Tailwind.
 */
private class DelegatingTailwindRippleNode(
    private val interactionSource: InteractionSource,
    private val bounded: Boolean,
    private val radius: Dp,
    private val color: ColorProducer
) : DelegatingNode(), CompositionLocalConsumerModifierNode, ObserverModifierNode {
    private var rippleNode: DelegatableNode? = null

    override fun onAttach() {
        updateConfiguration()
    }

    override fun onObservedReadsChanged() {
        updateConfiguration()
    }

    private fun updateConfiguration() {
        observeReads {
            val configuration = currentValueOf(LocalTailwindRippleConfig)
            if (configuration == null) {
                removeRipple()
            } else {
                if (rippleNode == null) attachNewRipple()
            }
        }
    }

    private fun attachNewRipple() {
        val calculateColor = ColorProducer {
            val userDefinedColor = color()
            if (userDefinedColor.isSpecified) {
                userDefinedColor
            } else {
                val rippleConfig = currentValueOf(LocalTailwindRippleConfig)
                if (rippleConfig?.color?.isSpecified == true) {
                    rippleConfig.color
                } else {
                    currentValueOf(LocalColorScheme).primary // Couleur par défaut Tailwind
                }
            }
        }

        val calculateRippleAlpha = {
            val rippleConfig = currentValueOf(LocalTailwindRippleConfig)
            rippleConfig?.rippleAlpha ?: TailwindRippleDefaults.RippleAlpha
        }

        rippleNode = delegate(
            TailwindRippleModifierNode(
                interactionSource = interactionSource,
                bounded = bounded,
                radius = radius,
                color = calculateColor,
                rippleAlpha = calculateRippleAlpha
            )
        )
    }

    private fun removeRipple() {
        rippleNode?.let { undelegate(it) }
        rippleNode = null
    }
}

/**
 * Noeud interne pour gérer le dessin et l'animation du ripple Tailwind.
 */
private class TailwindRippleModifierNode(
    private val interactionSource: InteractionSource,
    private val bounded: Boolean,
    private val radius: Dp,
    private val color: ColorProducer,
    private val rippleAlpha: () -> TailwindRippleAlpha
) : Modifier.Node(), DrawModifierNode {
    private val rippleRadius = Animatable(0f)
    private var pressPosition: Offset? = null
    private var currentAlpha: Float = 0f
    private var targetRadius: Float = 0f

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> {
                        pressPosition = interaction.pressPosition
                        currentAlpha = rippleAlpha().pressedAlpha
                        rippleRadius.animateTo(
                            targetValue = targetRadius,
                            animationSpec = tween(durationMillis = 300)
                        )
                    }

                    is PressInteraction.Release -> {
                        currentAlpha = 0f
                        rippleRadius.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(durationMillis = 150)
                        )
                    }
                    // TODO: Ajouter d'autres interactions si nécessaire (focus, hover, etc.)
                }
                invalidateSubtree()
            }
        }
    }

    override fun ContentDrawScope.draw() {
        drawContent()

        val resolvedColor = color()
        targetRadius = resolveRadius()
        val resolvedAlpha = currentAlpha
        val currentRadius = rippleRadius.value

        if (currentRadius > 0f) {
            val rippleColor = resolvedColor.copy(alpha = resolvedAlpha)
            val center = if (bounded) pressPosition ?: size.center else size.center

            drawCircle(
                color = rippleColor,
                radius = currentRadius,
                center = center
            )
        }
    }

    private fun ContentDrawScope.resolveRadius(): Float {
        return if (radius != Dp.Unspecified) {
            with(this) { radius.toPx() }
        } else {
            if (bounded) size.minDimension / 2f else max(size.width, size.height)
        }
    }
}

private val DefaultBoundedRipple =
    RippleNodeFactory(bounded = true, radius = Dp.Unspecified, color = Color.Unspecified)
private val DefaultUnboundedRipple =
    RippleNodeFactory(bounded = false, radius = Dp.Unspecified, color = Color.Unspecified)

// Locals nécessaires
internal val LocalInteractionSource = staticCompositionLocalOf { MutableInteractionSource() }