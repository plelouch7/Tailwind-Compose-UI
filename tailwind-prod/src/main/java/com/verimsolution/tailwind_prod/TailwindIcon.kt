package com.verimsolution.tailwind_prod

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.layout
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp

/**
 * Affiche une icône dans le style Tailwind CSS, avec support pour différents types d'entrée (ImageVector, ImageBitmap, ou Painter).
 *
 * @param imageVector L'ImageVector à dessiner (optionnel, surcharge).
 * @param contentDescription Description pour l'accessibilité (obligatoire pour les icônes non décoratives).
 * @param modifier Modificateur appliqué à l'icône.
 * @param tint Teinte appliquée à l'icône. Si [Color.Unspecified], aucune teinte n'est appliquée.
 */
@Composable
fun TailwindIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalColorScheme.current.onSurface
) {
    TailwindIcon(
        painter = rememberVectorPainter(imageVector),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

/**
 * Affiche une icône dans le style Tailwind CSS à partir d'un bitmap.
 *
 * @param bitmap L'ImageBitmap à dessiner (optionnel, surcharge).
 * @param contentDescription Description pour l'accessibilité.
 * @param modifier Modificateur appliqué à l'icône.
 * @param tint Teinte appliquée à l'icône.
 */
@Composable
fun TailwindIcon(
    bitmap: ImageBitmap,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalColorScheme.current.onSurface
) {
    TailwindIcon(
        painter = BitmapPainter(bitmap),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

/**
 * Affiche une icône dans le style Tailwind CSS à partir d'un Painter.
 *
 * @param painter Le Painter à utiliser pour dessiner l'icône.
 * @param contentDescription Description pour l'accessibilité (obligatoire pour les icônes non décoratives).
 * @param modifier Modificateur appliqué à l'icône.
 * @param tint Teinte appliquée au Painter. Si [Color.Unspecified], aucune teinte n'est appliquée.
 */
@Composable
fun TailwindIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalColorScheme.current.onSurface
) {
    val colorFilter = if (tint == Color.Unspecified) null else ColorFilter.tint(tint)
    val semanticsModifier = if (contentDescription != null) {
        modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        modifier
    }

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = semanticsModifier.defaultSizeFor(painter),
        colorFilter = colorFilter
    )
}

/**
 * Applique une taille par défaut à l'icône si le Painter n'a pas de taille intrinsèque valide.
 */
private fun Modifier.defaultSizeFor(painter: Painter): Modifier = this.then(
    if (painter.intrinsicSize == Size.Unspecified || painter.intrinsicSize.isInfinite()) {
        DefaultIconSizeModifier // Taille par défaut Tailwind
    } else {
        Modifier.layout { measurable, constraints ->
            val width = painter.intrinsicSize.width.toInt()
            val height = painter.intrinsicSize.height.toInt()
            val placeable = measurable.measure(Constraints.fixed(width, height))
            layout(placeable.width, placeable.height) {
                placeable.place(0, 0)
            }
        }
    }
)

/**
 * Vérifie si une taille est infinie.
 */
private fun Size.isInfinite() = width.isInfinite() && height.isInfinite()

/**
 * Modificateur de taille par défaut pour les icônes sans taille intrinsèque, basé sur Tailwind.
 */
private val DefaultIconSizeModifier = Modifier.size(24.dp) // w-6 h-6 en Tailwind