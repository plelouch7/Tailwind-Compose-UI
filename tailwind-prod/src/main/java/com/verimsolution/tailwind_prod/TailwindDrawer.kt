package com.verimsolution.tailwind_prod

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.dismiss
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.verimsolution.tailwind_prod.tokens.TailwindDrawerTokens
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * États possibles du TailwindDrawer.
 */
enum class TailwindDrawerValue {
    Closed,
    Open
}

/**
 * Direction d'ouverture du tiroir.
 */
enum class TailwindDrawerDirection {
    Left,
    Right
}

/**
 * État du TailwindDrawer.
 */
class TailwindDrawerState(
    initialValue: TailwindDrawerValue,
    private val confirmStateChange: (TailwindDrawerValue) -> Boolean = { true }
) {
    internal val anchoredDraggableState = TailwindAnchoredDraggableState(
        initialValue = initialValue,
        density = Density(1f),
        animationSpec = tween(durationMillis = 256),
        confirmValueChange = confirmStateChange
    )

    var density: Density? by mutableStateOf(null)
        internal set

    val isOpen: Boolean
        get() = anchoredDraggableState.currentValue == TailwindDrawerValue.Open

    val currentOffset: Float
        get() = anchoredDraggableState.offset

    suspend fun open() {
        anchoredDraggableState.animateTo(TailwindDrawerValue.Open)
    }

    suspend fun close() {
        anchoredDraggableState.animateTo(TailwindDrawerValue.Closed)
    }

    companion object {
        fun Saver(confirmStateChange: (TailwindDrawerValue) -> Boolean) =
            Saver<TailwindDrawerState, TailwindDrawerValue>(
                save = { it.anchoredDraggableState.currentValue },
                restore = { TailwindDrawerState(it, confirmStateChange) }
            )
    }
}

/**
 * Crée et mémorise un TailwindDrawerState.
 */
@Composable
fun rememberTailwindDrawerState(
    initialValue: TailwindDrawerValue,
    confirmStateChange: (TailwindDrawerValue) -> Boolean = { true }
): TailwindDrawerState {
    return rememberSaveable(saver = TailwindDrawerState.Saver(confirmStateChange)) {
        TailwindDrawerState(initialValue, confirmStateChange)
    }
}

/**
 * Drawer modal Flowbite personnalisable sans mainContent.
 */
@Composable
fun TailwindModalDrawer(
    modifier: Modifier = Modifier,
    drawerState: TailwindDrawerState = rememberTailwindDrawerState(TailwindDrawerValue.Closed),
    direction: TailwindDrawerDirection = TailwindDrawerDirection.Left,
    gesturesEnabled: Boolean = true,
    scrimColor: Color = TailwindDrawerTokens.ScrimColor,
    scrimVisible: Boolean = true,
    disableBodyScrolling: Boolean = true,
    shape: Shape = TailwindDrawerTokens.ContainerShape,
    containerColor: Color = TailwindDrawerTokens.ContainerColor,
    elevation: Dp = TailwindDrawerTokens.ContainerElevation,
    header: @Composable ColumnScope.() -> Unit = {
        DefaultDrawerHeader(drawerState = drawerState, title = "Menu")
    },
    content: @Composable ColumnScope.() -> Unit
) {
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()
    var anchorsInitialized by remember { mutableStateOf(false) }
    var minValue by remember { mutableStateOf(0f) }
    var maxValue by remember { mutableStateOf(0f) }

    LaunchedEffect(density) {
        drawerState.density = density
    }

    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val orientation = Orientation.Horizontal
    val reverseDirection = when (direction) {
        TailwindDrawerDirection.Left -> isRtl
        TailwindDrawerDirection.Right -> !isRtl
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .zIndex(if (drawerState.isOpen) 1f else 0f)
            .then(if (disableBodyScrolling && drawerState.isOpen) Modifier.background(Color.Transparent) else Modifier)
            .tailwindAnchoredDraggable(
                state = drawerState.anchoredDraggableState,
                orientation = orientation,
                enabled = gesturesEnabled,
                reverseDirection = reverseDirection
            )
    ) {
        if (scrimVisible) {
            Scrim(
                open = drawerState.isOpen,
                onClose = {
                    if (gesturesEnabled && drawerState.anchoredDraggableState.confirmValueChange(TailwindDrawerValue.Closed)) {
                        scope.launch { drawerState.close() }
                    }
                },
                fraction = { calculateFraction(minValue, maxValue, drawerState.currentOffset) },
                color = scrimColor
            )
        }
        SubcomposeLayout(
            modifier = Modifier
                .offset {
                    val offsetX = if (drawerState.currentOffset.isNaN()) {
                        if (drawerState.isOpen) 0 else if (direction == TailwindDrawerDirection.Left) -TailwindDrawerTokens.ContainerWidth.roundToPx() else TailwindDrawerTokens.ContainerWidth.roundToPx()
                    } else {
                        drawerState.currentOffset.roundToInt()
                    }
                    IntOffset(if (direction == TailwindDrawerDirection.Right) -offsetX else offsetX, 0)
                }
                .semantics {
                    paneTitle = "Drawer"
                    if (drawerState.isOpen) {
                        dismiss {
                            if (drawerState.anchoredDraggableState.confirmValueChange(TailwindDrawerValue.Closed)) {
                                scope.launch { drawerState.close() }
                            }
                            true
                        }
                    }
                }
        ) { constraints ->
            val placeable = subcompose(0) {
                TailwindDrawerSheet(
                    modifier = Modifier.fillMaxHeight(),
                    shape = if (direction == TailwindDrawerDirection.Left) RoundedEndShape(shape) else RoundedStartShape(shape),
                    containerColor = containerColor,
                    elevation = elevation,
                    header = header,
                    content = content
                )
            }.first().measure(constraints.copy(minWidth = 0, minHeight = 0))
            val width = placeable.width
            val height = placeable.height

            layout(width, height) {
                val currentClosedAnchor = drawerState.anchoredDraggableState.anchors.positionOf(TailwindDrawerValue.Closed)
                val calculatedClosedAnchor = if (direction == TailwindDrawerDirection.Left) -width.toFloat() else width.toFloat()

                if (!anchorsInitialized || currentClosedAnchor != calculatedClosedAnchor) {
                    if (!anchorsInitialized) anchorsInitialized = true
                    minValue = calculatedClosedAnchor
                    maxValue = 0f
                    drawerState.anchoredDraggableState.updateAnchors(
                        TailwindDraggableAnchors {
                            TailwindDrawerValue.Closed at minValue
                            TailwindDrawerValue.Open at maxValue
                        }
                    )
                }
                placeable.placeRelative(0, 0)
            }
        }
    }
}

/**
 * Feuille de contenu avec header et content séparés.
 */
@Composable
fun TailwindDrawerSheet(
    modifier: Modifier = Modifier,
    shape: Shape,
    containerColor: Color,
    elevation: Dp,
    header: @Composable ColumnScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val density = LocalDensity.current
    Box(
        modifier = modifier
            .sizeIn(minWidth = MinimumDrawerWidth, maxWidth = TailwindDrawerTokens.ContainerWidth)
            .fillMaxHeight()
            .background(containerColor, shape)
            .graphicsLayer { shadowElevation = with(density) { elevation.toPx() } }
            .padding(16.dp)
    ) {
        Column {
            header()
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}

/**
 * Header par défaut avec titre et bouton de fermeture.
 */
@Composable
private fun DefaultDrawerHeader(
    drawerState: TailwindDrawerState,
    title: String
) {
    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TailwindText(
            text = title.uppercase(),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TailwindDrawerTokens.HeadlineColor,
                letterSpacing = 0.1.sp
            )
        )
        Box(
            modifier = Modifier
                .size(32.dp)
                .clickable { scope.launch { drawerState.close() } }
                .background(Color.Transparent, shape = RectangleShape),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(12.dp)) {
                drawLine(
                    color = TailwindDrawerTokens.CloseButtonColor,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2f
                )
                drawLine(
                    color = TailwindDrawerTokens.CloseButtonColor,
                    start = Offset(size.width, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 2f
                )
            }
        }
    }
}

/**
 * Item de navigation personnalisable.
 */
@Composable
fun TailwindDrawerItem(
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    shape: Shape = TailwindDrawerTokens.ActiveIndicatorShape,
    colors: TailwindDrawerItemColors = TailwindDrawerItemDefaults.colors(),
    contentPadding: Dp = 8.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.containerColor(selected),
                shape = if (selected) shape else RectangleShape
            )
            .clickable(onClick = onClick)
            .padding(contentPadding)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                CompositionLocalProvider(LocalContentColor provides colors.iconColor(selected)) {
                    icon()
                }
                Spacer(Modifier.width(12.dp))
            }
            Box(Modifier.weight(1f)) {
                CompositionLocalProvider(LocalContentColor provides colors.textColor(selected)) {
                    label()
                }
            }
            if (badge != null) {
                Spacer(Modifier.width(12.dp))
                CompositionLocalProvider(LocalContentColor provides colors.badgeColor(selected)) {
                    badge()
                }
            }
            if (trailing != null) {
                Spacer(Modifier.width(12.dp))
                CompositionLocalProvider(LocalContentColor provides colors.trailingColor(selected)) {
                    trailing()
                }
            }
        }
    }
}

/**
 * Interface pour les couleurs des items du tiroir.
 */
interface TailwindDrawerItemColors {
    @Composable
    fun iconColor(selected: Boolean): Color

    @Composable
    fun textColor(selected: Boolean): Color

    @Composable
    fun badgeColor(selected: Boolean): Color

    @Composable
    fun trailingColor(selected: Boolean): Color

    @Composable
    fun containerColor(selected: Boolean): Color
}

/**
 * Valeurs par défaut pour les items du tiroir.
 */
object TailwindDrawerItemDefaults {
    @Composable
    fun colors(
        selectedContainerColor: Color = TailwindDrawerTokens.ActiveIndicatorColor,
        unselectedContainerColor: Color = Color.Transparent,
        selectedIconColor: Color = TailwindDrawerTokens.ActiveIconColor,
        unselectedIconColor: Color = TailwindDrawerTokens.InactiveIconColor,
        selectedTextColor: Color = TailwindDrawerTokens.ActiveLabelTextColor,
        unselectedTextColor: Color = TailwindDrawerTokens.InactiveLabelTextColor,
        selectedBadgeColor: Color = selectedTextColor,
        unselectedBadgeColor: Color = unselectedTextColor,
        selectedTrailingColor: Color = selectedTextColor,
        unselectedTrailingColor: Color = unselectedTextColor
    ): TailwindDrawerItemColors = object : TailwindDrawerItemColors {
        @Composable
        override fun iconColor(selected: Boolean): Color = if (selected) selectedIconColor else unselectedIconColor

        @Composable
        override fun textColor(selected: Boolean): Color = if (selected) selectedTextColor else unselectedTextColor

        @Composable
        override fun badgeColor(selected: Boolean): Color = if (selected) selectedBadgeColor else unselectedBadgeColor

        @Composable
        override fun trailingColor(selected: Boolean): Color = if (selected) selectedTrailingColor else unselectedTrailingColor

        @Composable
        override fun containerColor(selected: Boolean): Color = if (selected) selectedContainerColor else unselectedContainerColor
    }
}

/**
 * Scrim pour obscurcir le contenu principal.
 */
@Composable
private fun Scrim(
    open: Boolean,
    onClose: () -> Unit,
    fraction: () -> Float,
    color: Color
) {
    if (open) {
        Canvas(
            Modifier
                .fillMaxSize()
                .pointerInput(Unit) { detectTapGestures { onClose() } }
                .semantics { dismiss { onClose(); true } }
        ) {
            drawRect(color = color, alpha = fraction())
        }
    }
}

/**
 * Calcule la fraction pour l'opacité du scrim.
 */
private fun calculateFraction(a: Float, b: Float, pos: Float) =
    ((pos - a) / (b - a)).coerceIn(0f, 1f)

/**
 * Formes personnalisées pour les extrémités.
 */
private class RoundedEndShape(private val baseShape: Shape) : Shape by baseShape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density) =
        baseShape.createOutline(size, LayoutDirection.Rtl, density)
}

private class RoundedStartShape(private val baseShape: Shape) : Shape by baseShape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density) =
        baseShape.createOutline(size, LayoutDirection.Ltr, density)
}

/**
 * Constantes par défaut.
 */
private val MinimumDrawerWidth = 256.dp // w-64 en Tailwind