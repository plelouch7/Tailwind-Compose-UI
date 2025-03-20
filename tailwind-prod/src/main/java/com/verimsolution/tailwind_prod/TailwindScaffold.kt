package com.verimsolution.tailwind_prod

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastMapNotNull
import androidx.compose.ui.util.fastMaxBy

/**
 * Tokens par défaut inspirés de Tailwind CSS/Flowbite pour le scaffold.
 */
object TailwindScaffoldDefaults {
    val backgroundColor = Color(0xFFF9FAFB) // bg-gray-50
    val contentColor = Color(0xFF1F2937) // text-gray-800
    val fabSpacing = 16.dp // Espacement du FAB
}

/**
 * Positions possibles pour le Floating Action Button (FAB).
 */
enum class FabPosition {
    Start,    // Début (gauche en LTR, droite en RTL)
    Center,   // Centre
    End,      // Fin (droite en LTR, gauche en RTL)
    EndOverlay // Fin, superposé au BottomBar
}

/**
 * Scaffold inspiré de Tailwind CSS/Flowbite, sans dépendance à Material 3.
 *
 * @param modifier Modificateur appliqué au scaffold.
 * @param topBar Composant supérieur (ex. barre de navigation).
 * @param bottomBar Composant inférieur (ex. barre de navigation).
 * @param snackbarHost Composant pour héberger des snackbars.
 * @param floatingActionButton Bouton d’action flottant (FAB).
 * @param floatingActionButtonPosition Position du FAB.
 * @param backgroundColor Couleur de fond du scaffold.
 * @param contentColor Couleur du contenu.
 * @param contentWindowInsets Marges système à appliquer au contenu.
 * @param content Contenu principal avec PaddingValues pour gérer les offsets.
 */
@Composable
fun TailwindScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    backgroundColor: Color = TailwindScaffoldDefaults.backgroundColor,
    contentColor: Color = TailwindScaffoldDefaults.contentColor,
    contentWindowInsets: PaddingValues = PaddingValues(0.dp), // Par défaut, pas d'insets
    content: @Composable (PaddingValues) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .consumeWindowInsets(contentWindowInsets)
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            TailwindScaffoldLayout(
                fabPosition = floatingActionButtonPosition,
                topBar = topBar,
                bottomBar = bottomBar,
                snackbarHost = snackbarHost,
                floatingActionButton = floatingActionButton,
                contentWindowInsets = contentWindowInsets,
                content = content
            )
        }
    }
}

/**
 * Mise en page interne du scaffold.
 */
@Composable
private fun TailwindScaffoldLayout(
    fabPosition: FabPosition,
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    snackbarHost: @Composable () -> Unit,
    floatingActionButton: @Composable () -> Unit,
    contentWindowInsets: PaddingValues,
    content: @Composable (PaddingValues) -> Unit
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    SubcomposeLayout { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight
        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        // Mesurer les composants
        val topBarPlaceables = subcompose(ScaffoldSlot.TopBar, topBar).fastMap {
            it.measure(looseConstraints)
        }
        val topBarHeight = topBarPlaceables.fastMaxBy { it.height }?.height ?: 0

        val bottomBarPlaceables = subcompose(ScaffoldSlot.BottomBar, bottomBar).fastMap {
            it.measure(looseConstraints)
        }
        val bottomBarHeight = bottomBarPlaceables.fastMaxBy { it.height }?.height ?: 0

        val snackbarPlaceables = subcompose(ScaffoldSlot.Snackbar, snackbarHost).fastMap {
            val leftInset = contentWindowInsets.calculateStartPadding(layoutDirection)
            val rightInset = contentWindowInsets.calculateEndPadding(layoutDirection)
            val bottomInset = contentWindowInsets.calculateBottomPadding()
            it.measure(
                looseConstraints.offset(
                    horizontal = -with(density) { (leftInset + rightInset).toPx().toInt() },
                    vertical = -with(density) { bottomInset.toPx().toInt() }
                )
            )
        }
        val snackbarHeight = snackbarPlaceables.fastMaxBy { it.height }?.height ?: 0
        val snackbarWidth = snackbarPlaceables.fastMaxBy { it.width }?.width ?: 0

        val fabPlaceables = subcompose(ScaffoldSlot.Fab, floatingActionButton).fastMapNotNull { measurable ->
            val leftInset = contentWindowInsets.calculateStartPadding(layoutDirection)
            val rightInset = contentWindowInsets.calculateEndPadding(layoutDirection)
            val bottomInset = contentWindowInsets.calculateBottomPadding()
            measurable.measure(
                looseConstraints.offset(
                    horizontal = -with(density) { (leftInset + rightInset).toPx().toInt() },
                    vertical = -with(density) { bottomInset.toPx().toInt() }
                )
            ).takeIf { it.height != 0 && it.width != 0 }
        }
        val fabPlacement = if (fabPlaceables.isNotEmpty()) {
            val fabWidth = fabPlaceables.fastMaxBy { it.width }!!.width
            val fabHeight = fabPlaceables.fastMaxBy { it.height }!!.height
            val fabLeftOffset = when (fabPosition) {
                FabPosition.Start -> if (layoutDirection == LayoutDirection.Ltr) {
                    TailwindScaffoldDefaults.fabSpacing.roundToPx()
                } else {
                    layoutWidth - TailwindScaffoldDefaults.fabSpacing.roundToPx() - fabWidth
                }
                FabPosition.Center -> (layoutWidth - fabWidth) / 2
                FabPosition.End,
                FabPosition.EndOverlay -> if (layoutDirection == LayoutDirection.Ltr) {
                    layoutWidth - TailwindScaffoldDefaults.fabSpacing.roundToPx() - fabWidth
                } else {
                    TailwindScaffoldDefaults.fabSpacing.roundToPx()
                }
            }
            FabPlacement(left = fabLeftOffset, width = fabWidth, height = fabHeight)
        } else null

        // Calculer les offsets
        val fabOffsetFromBottom = fabPlacement?.let {
            if (bottomBarHeight == 0 || fabPosition == FabPosition.EndOverlay) {
                it.height + TailwindScaffoldDefaults.fabSpacing.roundToPx() +
                        with(density) { contentWindowInsets.calculateBottomPadding().toPx().toInt() }
            } else {
                bottomBarHeight + it.height + TailwindScaffoldDefaults.fabSpacing.roundToPx()
            }
        }

        val snackbarOffsetFromBottom = if (snackbarHeight != 0) {
            snackbarHeight + (fabOffsetFromBottom ?: bottomBarHeight)
        } else 0

        // Contenu principal avec padding ajusté
        val contentPlaceables = subcompose(ScaffoldSlot.MainContent) {
            val innerPadding = PaddingValues(
                top = if (topBarPlaceables.isEmpty()) {
                    contentWindowInsets.calculateTopPadding()
                } else {
                    with(density) { topBarHeight.toDp() }
                },
                bottom = if (bottomBarPlaceables.isEmpty() || bottomBarHeight == 0) {
                    contentWindowInsets.calculateBottomPadding()
                } else {
                    with(density) { bottomBarHeight.toDp() }
                },
                start = contentWindowInsets.calculateStartPadding(layoutDirection),
                end = contentWindowInsets.calculateEndPadding(layoutDirection)
            )
            content(innerPadding)
        }.fastMap { it.measure(looseConstraints) }

        // Disposition finale
        layout(layoutWidth, layoutHeight) {
            contentPlaceables.fastForEach { it.place(0, 0) }
            topBarPlaceables.fastForEach { it.place(0, 0) }
            bottomBarPlaceables.fastForEach { it.place(0, layoutHeight - bottomBarHeight) }
            snackbarPlaceables.fastForEach {
                it.place(
                    (layoutWidth - snackbarWidth) / 2 +
                            with(density) { contentWindowInsets.calculateStartPadding(layoutDirection).toPx().toInt() },
                    layoutHeight - snackbarOffsetFromBottom
                )
            }
            fabPlacement?.let { placement ->
                fabPlaceables.fastForEach {
                    it.place(placement.left, layoutHeight - fabOffsetFromBottom!!)
                }
            }
        }
    }
}

/**
 * Placement du FAB.
 */
data class FabPlacement(
    val left: Int,
    val width: Int,
    val height: Int
)

/**
 * Slots pour le SubcomposeLayout.
 */
private enum class ScaffoldSlot {
    TopBar,
    MainContent,
    Snackbar,
    Fab,
    BottomBar
}
