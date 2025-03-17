package com.verimsolution.tailwind

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
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
import com.verimsolution.tailwind.TailwindTheme.colorScheme
import com.verimsolution.tailwind.tokens.IconButtonTokens


/**
 * TailwindIcon composable qui affiche une icône basée sur un ImageVector.
 *
 * @param imageVector L'ImageVector à dessiner.
 * @param contentDescription Description pour l'accessibilité.
 * @param modifier Modificateur appliqué à l'icône.
 * @param tint La teinte à appliquer à l'icône. Si Color.Unspecified est fourni, aucune teinte n'est appliquée.
 */
@Composable
fun TailwindIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    TailwindIcon(
        painter = rememberVectorPainter(imageVector),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

/**
 * TailwindIcon composable qui affiche une icône basée sur un ImageBitmap.
 *
 * @param bitmap L'ImageBitmap à dessiner.
 * @param contentDescription Description pour l'accessibilité.
 * @param modifier Modificateur appliqué à l'icône.
 * @param tint La teinte à appliquer à l'icône.
 */
@Composable
fun TailwindIcon(
    bitmap: ImageBitmap,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    TailwindIcon(
        painter = BitmapPainter(bitmap),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

/**
 * TailwindIcon composable qui affiche une icône basée sur un Painter.
 *
 * @param painter Le Painter à utiliser pour dessiner l'icône.
 * @param contentDescription Description pour l'accessibilité. À fournir pour des icônes non décoratives.
 * @param modifier Modificateur appliqué à l'icône.
 * @param tint La teinte à appliquer au Painter. Si Color.Unspecified, aucune teinte n'est appliquée.
 */
@Composable
fun TailwindIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    // Définir le colorFilter en fonction du tint
    val colorFilter = if (tint == Color.Unspecified) null else ColorFilter.tint(tint)
    // Ajouter des informations de sémantique si contentDescription est fourni
    val semanticsModifier = if (contentDescription != null) {
        modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else modifier
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = semanticsModifier,
        colorFilter = colorFilter
    )
}

private fun Modifier.defaultSizeForColorProducer(painter: Painter) =
    this.then(
        if (painter.intrinsicSize == Size.Unspecified || painter.intrinsicSize.isInfinite()) {
            DefaultIconSizeModifier
        } else {
            val intrinsicSize = painter.intrinsicSize
            val srcWidth = intrinsicSize.width

            val srcHeight = intrinsicSize.height

            Modifier.layout { measurable, _ ->
                val placeable =
                    measurable.measure(Constraints.fixed(srcWidth.toInt(), srcHeight.toInt()))
                layout(placeable.width, placeable.height) { placeable.place(0, 0) }
            }
        }
    )

private fun Modifier.defaultSizeFor(painter: Painter) =
    this.then(
        if (painter.intrinsicSize == Size.Unspecified || painter.intrinsicSize.isInfinite()) {
            DefaultIconSizeModifier
        } else {
            Modifier
        }
    )

private fun Size.isInfinite() = width.isInfinite() && height.isInfinite()

// Default icon size, for icons with no intrinsic size information
private val DefaultIconSizeModifier = Modifier.size(IconButtonTokens.IconSize)