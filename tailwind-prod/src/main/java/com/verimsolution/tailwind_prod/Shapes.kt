package com.verimsolution.tailwind_prod

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.verimsolution.tailwind_prod.tokens.ShapeKeyTokens
import com.verimsolution.tailwind_prod.tokens.ShapeTokens

/**
 * Classe immuable représentant les formes (bordures arrondies) pour le thème Tailwind CSS.
 * Les valeurs sont basées sur les utilitaires Tailwind (ex. rounded-sm, rounded-t-lg).
 */
@Immutable
class Shapes(
    val none: Shape = ShapeTokens.None,                     // rounded-none (0dp)
    val extraSmall: Shape = ShapeTokens.ExtraSmall,         // rounded-sm (4dp)
    val small: Shape = ShapeTokens.Small,                   // rounded (8dp)
    val medium: Shape = ShapeTokens.Medium,                 // rounded-md (12dp)
    val large: Shape = ShapeTokens.Large,                   // rounded-lg (16dp)
    val extraLarge: Shape = ShapeTokens.ExtraLarge,         // rounded-xl (24dp)
    val full: Shape = ShapeTokens.Full,                     // rounded-full (cercle)
    val extraSmallTop: Shape = ShapeTokens.ExtraSmallTop,   // rounded-t-sm
    val smallTop: Shape = ShapeTokens.SmallTop,             // rounded-t
    val mediumTop: Shape = ShapeTokens.MediumTop,           // rounded-t-md
    val largeTop: Shape = ShapeTokens.LargeTop,             // rounded-t-lg
    val extraLargeTop: Shape = ShapeTokens.ExtraLargeTop,   // rounded-t-xl
    val extraSmallEnd: Shape = ShapeTokens.ExtraSmallEnd,   // rounded-e-sm
    val smallEnd: Shape = ShapeTokens.SmallEnd,             // rounded-e
    val mediumEnd: Shape = ShapeTokens.MediumEnd,           // rounded-e-md
    val largeEnd: Shape = ShapeTokens.LargeEnd,             // rounded-e-lg
    val extraLargeEnd: Shape = ShapeTokens.ExtraLargeEnd,   // rounded-e-xl
    val extraSmallStart: Shape = ShapeTokens.ExtraSmallStart, // rounded-s-sm
    val smallStart: Shape = ShapeTokens.SmallStart,         // rounded-s
    val mediumStart: Shape = ShapeTokens.MediumStart,       // rounded-s-md
    val largeStart: Shape = ShapeTokens.LargeStart,         // rounded-s-lg
    val extraLargeStart: Shape = ShapeTokens.ExtraLargeStart, // rounded-s-xl
    val cornerFull: Shape = ShapeTokens.CornerFull,         // rounded-full
) {
    /**
     * Crée une copie de cette instance avec des valeurs éventuellement remplacées.
     */
    fun copy(
        none: Shape = this.none,
        extraSmall: Shape = this.extraSmall,
        small: Shape = this.small,
        medium: Shape = this.medium,
        large: Shape = this.large,
        extraLarge: Shape = this.extraLarge,
        full: Shape = this.full,
        extraSmallTop: Shape = this.extraSmallTop,
        smallTop: Shape = this.smallTop,
        mediumTop: Shape = this.mediumTop,
        largeTop: Shape = this.largeTop,
        extraLargeTop: Shape = this.extraLargeTop,
        extraSmallEnd: Shape = this.extraSmallEnd,
        smallEnd: Shape = this.smallEnd,
        mediumEnd: Shape = this.mediumEnd,
        largeEnd: Shape = this.largeEnd,
        extraLargeEnd: Shape = this.extraLargeEnd,
        extraSmallStart: Shape = this.extraSmallStart,
        smallStart: Shape = this.smallStart,
        mediumStart: Shape = this.mediumStart,
        largeStart: Shape = this.largeStart,
        extraLargeStart: Shape = this.extraLargeStart
    ): Shapes = Shapes(
        none = none,
        extraSmall = extraSmall,
        small = small,
        medium = medium,
        large = large,
        extraLarge = extraLarge,
        full = full,
        extraSmallTop = extraSmallTop,
        smallTop = smallTop,
        mediumTop = mediumTop,
        largeTop = largeTop,
        extraLargeTop = extraLargeTop,
        extraSmallEnd = extraSmallEnd,
        smallEnd = smallEnd,
        mediumEnd = mediumEnd,
        largeEnd = largeEnd,
        extraLargeEnd = extraLargeEnd,
        extraSmallStart = extraSmallStart,
        smallStart = smallStart,
        mediumStart = mediumStart,
        largeStart = largeStart,
        extraLargeStart = extraLargeStart
    )
}

/**
 * Fonction d'assistance pour récupérer une forme depuis [Shapes] via une clé Tailwind.
 */
fun Shapes.fromToken(value: ShapeKeyTokens): Shape {
    return when (value) {
        ShapeKeyTokens.None -> none
        ShapeKeyTokens.ExtraSmall -> extraSmall
        ShapeKeyTokens.ExtraSmallTop -> extraSmallTop
        ShapeKeyTokens.ExtraSmallEnd -> extraSmallEnd
        ShapeKeyTokens.ExtraSmallStart -> extraSmallStart
        ShapeKeyTokens.Small -> small
        ShapeKeyTokens.SmallTop -> smallTop
        ShapeKeyTokens.SmallEnd -> smallEnd
        ShapeKeyTokens.SmallStart -> smallStart
        ShapeKeyTokens.Medium -> medium
        ShapeKeyTokens.MediumTop -> mediumTop
        ShapeKeyTokens.MediumEnd -> mediumEnd
        ShapeKeyTokens.MediumStart -> mediumStart
        ShapeKeyTokens.Large -> large
        ShapeKeyTokens.LargeTop -> largeTop
        ShapeKeyTokens.LargeEnd -> largeEnd
        ShapeKeyTokens.LargeStart -> largeStart
        ShapeKeyTokens.ExtraLarge -> extraLarge
        ShapeKeyTokens.ExtraLargeTop -> extraLargeTop
        ShapeKeyTokens.ExtraLargeEnd -> extraLargeEnd
        ShapeKeyTokens.ExtraLargeStart -> extraLargeStart
        ShapeKeyTokens.Full -> full
        ShapeKeyTokens.CornerFull ->  cornerFull
    }
}

/**
 * Extension pour accéder à une forme via une clé, en se basant sur le thème Tailwind courant.
 */
val ShapeKeyTokens.value: Shape
    @Composable
    @ReadOnlyComposable
    get() = TailwindTheme.shapes.fromToken(this)

/**
 * Extensions pour manipuler les coins des formes (inspiré de Tailwind rounded-t-*, rounded-e-*).
 */
internal fun Shape.top(): Shape {
    val shape = this as? RoundedCornerShape
    return RoundedCornerShape(
        topStart = shape?.topStart ?: CornerSize(0.dp),
        topEnd = shape?.topEnd ?: CornerSize(0.dp),
        bottomStart = CornerSize(0.dp),
        bottomEnd = CornerSize(0.dp)
    )
}

internal fun Shape.bottom(): Shape {
    val shape = this as? RoundedCornerShape
    return RoundedCornerShape(
        topStart = CornerSize(0.dp),
        topEnd = CornerSize(0.dp),
        bottomStart = shape?.bottomStart ?: CornerSize(0.dp),
        bottomEnd = shape?.bottomEnd ?: CornerSize(0.dp)
    )
}

internal fun Shape.start(): Shape {
    val shape = this as? RoundedCornerShape
    return RoundedCornerShape(
        topStart = shape?.topStart ?: CornerSize(0.dp),
        topEnd = CornerSize(0.dp),
        bottomStart = shape?.bottomStart ?: CornerSize(0.dp),
        bottomEnd = CornerSize(0.dp)
    )
}

internal fun Shape.end(): Shape {
    val shape = this as? RoundedCornerShape
    return RoundedCornerShape(
        topStart = CornerSize(0.dp),
        topEnd = shape?.topEnd ?: CornerSize(0.dp),
        bottomStart = CornerSize(0.dp),
        bottomEnd = shape?.bottomEnd ?: CornerSize(0.dp)
    )
}

/**
 * CompositionLocal utilisé pour transmettre les formes par défaut dans la hiérarchie.
 */
internal val LocalShapes = staticCompositionLocalOf { Shapes() }