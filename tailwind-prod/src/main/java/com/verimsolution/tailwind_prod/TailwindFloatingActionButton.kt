package com.verimsolution.tailwind_prod

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Tokens par défaut inspirés de Tailwind CSS/Flowbite pour le Floating Action Button (Speed Dial).
 */
object TailwindFloatingActionButtonDefaults {
    val containerColor = Color(0xFF3B82F6) // bg-blue-500
    val contentColor = Color.White // text-white
    val size = 56.dp // Taille standard du FAB
    val elevation = 6.dp // shadow-md
    val secondaryButtonSize = 52.dp // Taille des boutons secondaires
    val spacing = 8.dp // Espacement entre les boutons
    val shape: Shape = CircleShape // Forme par défaut
    val labelColor = Color(0xFF6B7280) // text-gray-500
    val labelPadding = 8.dp // Espacement entre le bouton et le label
    val labelStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = labelColor
    ) // Style par défaut des étiquettes
    val fadeAnimationSpec: FiniteAnimationSpec<Float> = tween(durationMillis = 200) // Animation pour fade
    val slideAnimationSpec: FiniteAnimationSpec<IntOffset> = tween(durationMillis = 200) // Animation pour slide
}

/**
 * Direction d’affichage des actions secondaires dans le Speed Dial.
 */
enum class SpeedDialDirection {
    Up,    // Actions au-dessus du bouton principal
    Down,  // Actions en dessous
    Left,  // Actions à gauche
    Right  // Actions à droite
}

/**
 * Floating Action Button inspiré du Speed Dial de Flowbite, hautement personnalisable.
 *
 * @param onClick Callback appelé lorsque le bouton principal est cliqué.
 * @param modifier Modificateur appliqué au FAB principal.
 * @param containerColor Couleur de fond du FAB principal.
 * @param contentColor Couleur du contenu du FAB principal.
 * @param elevation Élévation (ombre) du FAB principal.
 * @param size Taille du bouton principal.
 * @param shape Forme du bouton principal.
 * @param expanded État d’expansion des actions secondaires (true pour afficher, false pour cacher).
 * @param direction Direction des actions secondaires (Up, Down, Left, Right).
 * @param spacing Espacement entre les actions secondaires.
 * @param fadeAnimationSpec Spécification de l’animation pour fade (opacité).
 * @param slideAnimationSpec Spécification de l’animation pour slide (déplacement).
 * @param content Contenu du FAB principal (ex. icône ou texte).
 * @param secondaryActions Contenu des actions secondaires (liste de boutons avec étiquettes).
 */
@Composable
fun TailwindFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = TailwindFloatingActionButtonDefaults.containerColor,
    contentColor: Color = TailwindFloatingActionButtonDefaults.contentColor,
    elevation: Dp = TailwindFloatingActionButtonDefaults.elevation,
    size: Dp = TailwindFloatingActionButtonDefaults.size,
    shape: Shape = TailwindFloatingActionButtonDefaults.shape,
    expanded: Boolean = false,
    direction: SpeedDialDirection = SpeedDialDirection.Up,
    spacing: Dp = TailwindFloatingActionButtonDefaults.spacing,
    fadeAnimationSpec: FiniteAnimationSpec<Float> = TailwindFloatingActionButtonDefaults.fadeAnimationSpec,
    slideAnimationSpec: FiniteAnimationSpec<IntOffset> = TailwindFloatingActionButtonDefaults.slideAnimationSpec,
    content: @Composable () -> Unit,
    secondaryActions: @Composable () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isExpanded by remember { mutableStateOf(expanded) }

    Box(
        modifier = modifier,
        contentAlignment = when (direction) {
            SpeedDialDirection.Up -> Alignment.BottomEnd
            SpeedDialDirection.Down -> Alignment.TopEnd
            SpeedDialDirection.Left -> Alignment.CenterEnd
            SpeedDialDirection.Right -> Alignment.CenterStart
        }
    ) {
        // Bouton principal (rendu en premier)
        Box(
            modifier = Modifier
                .size(size)
                .shadow(elevation, shape)
                .background(containerColor, shape)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        isExpanded = !isExpanded // Basculer l’état d’expansion
                        onClick()
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            CompositionLocalProvider(LocalContentColor provides contentColor) {
                content()
            }
        }

        // Actions secondaires (Speed Dial, rendu après le bouton principal)
        AnimatedVisibility(
            visible = isExpanded,
            enter = when (direction) {
                SpeedDialDirection.Up -> slideInVertically(initialOffsetY = { it }, animationSpec = slideAnimationSpec) + fadeIn(animationSpec = fadeAnimationSpec)
                SpeedDialDirection.Down -> slideInVertically(initialOffsetY = { -it }, animationSpec = slideAnimationSpec) + fadeIn(animationSpec = fadeAnimationSpec)
                SpeedDialDirection.Left -> slideInHorizontally(initialOffsetX = { it }, animationSpec = slideAnimationSpec) + fadeIn(animationSpec = fadeAnimationSpec)
                SpeedDialDirection.Right -> slideInHorizontally(initialOffsetX = { -it }, animationSpec = slideAnimationSpec) + fadeIn(animationSpec = fadeAnimationSpec)
            },
            exit = when (direction) {
                SpeedDialDirection.Up -> slideOutVertically(targetOffsetY = { it }, animationSpec = slideAnimationSpec) + fadeOut(animationSpec = fadeAnimationSpec)
                SpeedDialDirection.Down -> slideOutVertically(targetOffsetY = { -it }, animationSpec = slideAnimationSpec) + fadeOut(animationSpec = fadeAnimationSpec)
                SpeedDialDirection.Left -> slideOutHorizontally(targetOffsetX = { it }, animationSpec = slideAnimationSpec) + fadeOut(animationSpec = fadeAnimationSpec)
                SpeedDialDirection.Right -> slideOutHorizontally(targetOffsetX = { -it }, animationSpec = slideAnimationSpec) + fadeOut(animationSpec = fadeAnimationSpec)
            }
        ) {
            when (direction) {
                SpeedDialDirection.Up -> {
                    Column(
                        modifier = Modifier.offset(y = -(size + spacing)),
                        verticalArrangement = Arrangement.spacedBy(spacing),
                        horizontalAlignment = Alignment.End
                    ) {
                        secondaryActions()
                    }
                }
                SpeedDialDirection.Down -> {
                    Column(
                        modifier = Modifier.offset(y = size + spacing),
                        verticalArrangement = Arrangement.spacedBy(spacing),
                        horizontalAlignment = Alignment.End
                    ) {
                        secondaryActions()
                    }
                }
                SpeedDialDirection.Left -> {
                    Row(
                        modifier = Modifier.offset(x = -(size + spacing)),
                        horizontalArrangement = Arrangement.spacedBy(spacing),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        secondaryActions()
                    }
                }
                SpeedDialDirection.Right -> {
                    Row(
                        modifier = Modifier.offset(x = size + spacing),
                        horizontalArrangement = Arrangement.spacedBy(spacing),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        secondaryActions()
                    }
                }
            }
        }
    }
}

/**
 * Bouton secondaire pour le Speed Dial avec une étiquette textuelle personnalisable.
 *
 * @param onClick Callback appelé lorsque le bouton est cliqué.
 * @param label Texte de l’étiquette à afficher à côté du bouton.
 * @param modifier Modificateur appliqué au bouton secondaire.
 * @param containerColor Couleur de fond du bouton.
 * @param contentColor Couleur du contenu du bouton.
 * @param size Taille du bouton secondaire.
 * @param elevation Élévation (ombre) du bouton secondaire.
 * @param shape Forme du bouton secondaire.
 * @param labelColor Couleur de l’étiquette.
 * @param labelStyle Style typographique de l’étiquette.
 * @param labelPadding Espacement entre le bouton et l’étiquette.
 * @param content Contenu du bouton secondaire (ex. icône ou texte).
 */
@Composable
fun TailwindSecondaryActionButton(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White, // bg-white (Flowbite)
    contentColor: Color = Color(0xFF6B7280), // text-gray-500
    size: Dp = TailwindFloatingActionButtonDefaults.secondaryButtonSize,
    elevation: Dp = TailwindFloatingActionButtonDefaults.elevation,
    shape: Shape = TailwindFloatingActionButtonDefaults.shape,
    labelColor: Color = TailwindFloatingActionButtonDefaults.labelColor,
    labelStyle: TextStyle = TailwindFloatingActionButtonDefaults.labelStyle,
    labelPadding: Dp = TailwindFloatingActionButtonDefaults.labelPadding,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        // Étiquette à gauche
        TailwindText(
            text = label,
            style = labelStyle.copy(color = labelColor),
            modifier = Modifier.padding(end = labelPadding)
        )
        // Bouton secondaire
        Box(
            modifier = Modifier
                .size(size)
                .shadow(elevation, shape)
                .background(containerColor, shape)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            CompositionLocalProvider(LocalContentColor provides contentColor) {
                content()
            }
        }
    }
}

/**
 * Animations personnalisées pour les actions secondaires.
 */
private fun slideInHorizontally(
    initialOffsetX: (Int) -> Int,
    animationSpec: FiniteAnimationSpec<IntOffset>
) = androidx.compose.animation.slideInHorizontally(
    initialOffsetX = initialOffsetX,
    animationSpec = animationSpec
)

private fun slideOutHorizontally(
    targetOffsetX: (Int) -> Int,
    animationSpec: FiniteAnimationSpec<IntOffset>
) = androidx.compose.animation.slideOutHorizontally(
    targetOffsetX = targetOffsetX,
    animationSpec = animationSpec
)