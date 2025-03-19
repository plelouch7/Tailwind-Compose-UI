package com.verimsolution.tailwind_prod

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.collapse
import androidx.compose.ui.semantics.dismiss
import androidx.compose.ui.semantics.expand
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Tokens par défaut inspirés de Tailwind CSS/Flowbite pour le bottom sheet modal.
 */
object TailwindBottomSheetDefaults {
    val backgroundColor = Color(0xFFFFFFFF) // bg-white
    val scrimColor = Color(0x80000000) // bg-black/50
    val shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp) // rounded-t-lg
    val elevation = 8.dp // shadow-lg
    val sheetMaxWidth = 640.dp // max-w-xl
    val dragHandleColor = Color(0xFF9CA3AF) // text-gray-400
    val padding = 16.dp // p-4
    val predictiveBackMaxScaleXDistance = 48.dp
    val predictiveBackMaxScaleYDistance = 24.dp

    // Variantes Flowbite
    val darkBg = Color(0xFF1F2937) // bg-gray-800
    val darkDragHandleColor = Color(0xFFD1D5DB) // text-gray-300
}

/**
 * Variantes Flowbite pour le bottom sheet.
 */
enum class BottomSheetVariant {
    Default,  // Style clair
    Dark      // Style sombre
}

/**
 * États possibles du bottom sheet, inspirés de Material 3.
 */
enum class BottomSheetState {
    Hidden,
    PartiallyExpanded,
    Expanded
}

/**
 * Propriétés personnalisées pour le bottom sheet.
 */
data class BottomSheetProperties(
    val shouldDismissOnBackPress: Boolean = true
)

/**
 * Un bottom sheet modal inspiré de Material 3 et Flowbite/Tailwind CSS, sans dépendance à Material 3.
 *
 * @param onDismissRequest Callback appelé lorsque le bottom sheet est fermé.
 * @param modifier Modificateur appliqué au bottom sheet.
 * @param skipPartiallyExpanded Si vrai, ignore l’état PartiallyExpanded.
 * @param initialState État initial du bottom sheet.
 * @param variant Variante Flowbite (Default ou Dark).
 * @param sheetMaxWidth Largeur maximale du bottom sheet.
 * @param backgroundColor Couleur de fond personnalisée.
 * @param scrimColor Couleur du scrim (overlay) personnalisée.
 * @param shape Forme personnalisée.
 * @param elevation Élévation de l’ombre personnalisée.
 * @param dragHandle Poignée de glissement optionnelle.
 * @param properties Propriétés personnalisées (ex. shouldDismissOnBackPress).
 * @param content Contenu affiché dans le bottom sheet.
 */
@Composable
fun TailwindBottomSheetModal(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    skipPartiallyExpanded: Boolean = false,
    initialState: BottomSheetState = BottomSheetState.Hidden,
    variant: BottomSheetVariant = BottomSheetVariant.Default,
    sheetMaxWidth: Dp = TailwindBottomSheetDefaults.sheetMaxWidth,
    backgroundColor: Color = TailwindBottomSheetDefaults.backgroundColor,
    scrimColor: Color = TailwindBottomSheetDefaults.scrimColor,
    shape: Shape = TailwindBottomSheetDefaults.shape,
    elevation: Dp = TailwindBottomSheetDefaults.elevation,
    dragHandle: @Composable (() -> Unit)? = { DefaultDragHandle(variant) },
    properties: BottomSheetProperties = BottomSheetProperties(),
    content: @Composable ColumnScope.() -> Unit
) {
    val scope = rememberCoroutineScope()
    var currentState by remember { mutableStateOf(initialState) }
    var offsetY by remember { mutableStateOf(0f) } // Décalage temporaire pendant le glissement
    var sheetHeight by remember { mutableStateOf(0f) } // Hauteur du contenu
    var containerHeight by remember { mutableStateOf(0f) } // Hauteur totale de l’écran
    val predictiveBackProgress = remember { Animatable(0f) }
    val density = LocalDensity.current

    // Résoudre les styles selon la variante
    val resolvedBgColor = when (variant) {
        BottomSheetVariant.Default -> backgroundColor
        BottomSheetVariant.Dark -> TailwindBottomSheetDefaults.darkBg
    }

    // Calcul des ancres personnalisées
    fun calculateAnchors(): Map<BottomSheetState, Float> {
        val anchors = mutableMapOf<BottomSheetState, Float>()
        anchors[BottomSheetState.Hidden] = containerHeight
        if (!skipPartiallyExpanded && sheetHeight > (containerHeight / 2)) {
            anchors[BottomSheetState.PartiallyExpanded] = containerHeight / 2f
        }
        if (sheetHeight > 0) {
            anchors[BottomSheetState.Expanded] = max(0f, containerHeight - sheetHeight)
        }
        return anchors
    }

    // Animation pour le décalage
    val anchors = calculateAnchors()
    val targetOffsetY = anchors[currentState] ?: containerHeight
    val animatedOffsetY by animateFloatAsState(
        targetValue = targetOffsetY,
        animationSpec = TweenSpec(durationMillis = 300)
    )

    // Fonction pour déterminer l’état cible après glissement
    fun settleToState(velocity: Float) {
        val newOffset = animatedOffsetY + offsetY
        val anchorsList = anchors.entries.sortedBy { it.value }
        val closestAnchor = anchorsList.minByOrNull { kotlin.math.abs(it.value - newOffset) }
        val newState = closestAnchor?.key ?: BottomSheetState.Hidden

        // Prendre en compte la vélocité pour simuler un "fling"
        currentState = if (kotlin.math.abs(velocity) > 500f) {
            if (velocity > 0) BottomSheetState.Hidden else BottomSheetState.Expanded
        } else {
            newState
        }

        if (currentState == BottomSheetState.Hidden) {
            scope.launch { if (animatedOffsetY >= containerHeight) onDismissRequest() }
        }
        offsetY = 0f // Réinitialiser le décalage temporaire
    }

    // Animation pour le dismiss
    fun animateToDismiss() {
        scope.launch {
            currentState = BottomSheetState.Hidden
            predictiveBackProgress.animateTo(0f)
            if (animatedOffsetY >= containerHeight) onDismissRequest()
        }
    }

    Dialog(
        onDismissRequest = { animateToDismiss() },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = properties.shouldDismissOnBackPress
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { containerHeight = it.height.toFloat() }
        ) {
            // Scrim (overlay)
            val scrimAlpha by animateFloatAsState(
                targetValue = if (currentState != BottomSheetState.Hidden) 1f else 0f,
                animationSpec = TweenSpec(durationMillis = 300)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(scrimColor.copy(alpha = scrimAlpha))
                    .pointerInput(Unit) {
                        detectTapGestures { animateToDismiss() }
                    }
                    .semantics { dismiss { animateToDismiss(); true } }
            )

            // Contenu du bottom sheet
            Box(
                modifier = modifier
                    .align(Alignment.TopCenter)
                    .widthIn(max = sheetMaxWidth)
                    .fillMaxWidth()
                    .offset { IntOffset(0, (animatedOffsetY + offsetY).roundToInt()) }
                    .shadow(elevation, shape)
                    .background(resolvedBgColor, shape)
                    .graphicsLayer {
                        val progress = predictiveBackProgress.value
                        scaleX = 1f - min(
                            with(density) { TailwindBottomSheetDefaults.predictiveBackMaxScaleXDistance.toPx() },
                            size.width
                        ) * progress / size.width
                        scaleY = 1f - min(
                            with(density) { TailwindBottomSheetDefaults.predictiveBackMaxScaleYDistance.toPx() },
                            size.height
                        ) * progress / size.height
                    }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { offsetY = 0f },
                            onDrag = { change, dragAmount ->
                                val newOffset = offsetY + dragAmount.y
                                offsetY = newOffset.coerceIn(
                                    min(0f, containerHeight - sheetHeight) - animatedOffsetY,
                                    containerHeight - animatedOffsetY
                                )
                                scope.launch {
                                    predictiveBackProgress.snapTo(
                                        (offsetY / containerHeight).coerceIn(0f, 1f)
                                    )
                                }
                                change.consume()
                            },
                            onDragEnd = { settleToState(0f) },
                            onDragCancel = { settleToState(0f) }
                        )
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(TailwindBottomSheetDefaults.padding)
                        .onSizeChanged { sheetHeight = it.height.toFloat() }
                ) {
                    dragHandle?.let {
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 8.dp)
                                .semantics(mergeDescendants = true) {
                                    dismiss { animateToDismiss(); true }
                                    if (currentState == BottomSheetState.PartiallyExpanded) {
                                        expand { currentState = BottomSheetState.Expanded; true }
                                    } else if (anchors.containsKey(BottomSheetState.PartiallyExpanded)) {
                                        collapse {
                                            currentState = BottomSheetState.PartiallyExpanded; true
                                        }
                                    }
                                }
                        ) {
                            it()
                        }
                    }
                    content()
                }
            }
        }
    }

    // Afficher le bottom sheet au lancement si initialState n’est pas Hidden
    LaunchedEffect(initialState) {
        if (initialState != BottomSheetState.Hidden) {
            currentState = initialState
        }
    }
}

/**
 * Poignée de glissement par défaut.
 */
@Composable
fun DefaultDragHandle(variant: BottomSheetVariant) {
    val handleColor = when (variant) {
        BottomSheetVariant.Default -> TailwindBottomSheetDefaults.dragHandleColor
        BottomSheetVariant.Dark -> TailwindBottomSheetDefaults.darkDragHandleColor
    }
    Box(
        modifier = Modifier
            .size(width = 36.dp, height = 4.dp)
            .background(handleColor, RoundedCornerShape(2.dp))
    )
}