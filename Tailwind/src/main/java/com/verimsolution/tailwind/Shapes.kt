package com.verimsolution.tailwind

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.verimsolution.tailwind.tokens.ShapeTokens

// Définition de l'objet Shapes et de ses valeurs par défaut, en se basant sur ShapeTokens
@Immutable
class Shapes(
    val extraSmall: CornerBasedShape = ShapeDefaults.ExtraSmall,
    val small: CornerBasedShape = ShapeDefaults.Small,
    val medium: CornerBasedShape = ShapeDefaults.Medium,
    val large: CornerBasedShape = ShapeDefaults.Large,
    val extraLarge: CornerBasedShape = ShapeDefaults.ExtraLarge,
) {
    /** Retourne une copie de ce Shapes, en permettant de surcharger certaines valeurs. */
    fun copy(
        extraSmall: CornerBasedShape = this.extraSmall,
        small: CornerBasedShape = this.small,
        medium: CornerBasedShape = this.medium,
        large: CornerBasedShape = this.large,
        extraLarge: CornerBasedShape = this.extraLarge,
    ): Shapes =
        Shapes(
            extraSmall = extraSmall,
            small = small,
            medium = medium,
            large = large,
            extraLarge = extraLarge,
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Shapes) return false

        if (extraSmall != other.extraSmall) return false
        if (small != other.small) return false
        if (medium != other.medium) return false
        if (large != other.large) return false
        if (extraLarge != other.extraLarge) return false

        return true
    }

    override fun hashCode(): Int {
        var result = extraSmall.hashCode()
        result = 31 * result + small.hashCode()
        result = 31 * result + medium.hashCode()
        result = 31 * result + large.hashCode()
        result = 31 * result + extraLarge.hashCode()
        return result
    }

    override fun toString(): String {
        return "Shapes(" +
                "extraSmall=$extraSmall, " +
                "small=$small, " +
                "medium=$medium, " +
                "large=$large, " +
                "extraLarge=$extraLarge)"
    }
}

/** Définit les valeurs par défaut utilisées par [Shapes]. */
object ShapeDefaults {
    val ExtraSmall: CornerBasedShape = ShapeTokens.CornerExtraSmall
    val Small: CornerBasedShape = ShapeTokens.CornerSmall
    val Medium: CornerBasedShape = ShapeTokens.CornerMedium
    val Large: CornerBasedShape = ShapeTokens.CornerLarge
    val ExtraLarge: CornerBasedShape = ShapeTokens.CornerExtraLarge
}

/** Renvoie une forme correspondant uniquement aux coins supérieurs d'une forme. */
internal fun CornerBasedShape.top(): CornerBasedShape {
    return this.copy(
        bottomStart = CornerSize(0.0.dp),
        bottomEnd = CornerSize(0.0.dp)
    )
}

/** Renvoie une forme correspondant uniquement aux coins inférieurs d'une forme. */
internal fun CornerBasedShape.bottom(): CornerBasedShape {
    return this.copy(
        topStart = CornerSize(0.0.dp),
        topEnd = CornerSize(0.0.dp)
    )
}

/** Renvoie une forme correspondant uniquement aux coins du côté "start" d'une forme. */
internal fun CornerBasedShape.start(): CornerBasedShape {
    return this.copy(
        topEnd = CornerSize(0.0.dp),
        bottomEnd = CornerSize(0.0.dp)
    )
}

/** Renvoie une forme correspondant uniquement aux coins du côté "end" d'une forme. */
internal fun CornerBasedShape.end(): CornerBasedShape {
    return this.copy(
        topStart = CornerSize(0.0.dp),
        bottomStart = CornerSize(0.0.dp)
    )
}

/**
 * Clés de formes pour mapper aux valeurs du thème.
 */
internal enum class ShapeKeyTokens {
    CornerExtraLarge,
    CornerExtraLargeTop,
    CornerExtraSmall,
    CornerExtraSmallTop,
    CornerFull,
    CornerLarge,
    CornerLargeEnd,
    CornerLargeTop,
    CornerMedium,
    CornerNone,
    CornerSmall
}

/**
 * Fonction d'assistance pour récupérer une forme depuis [Shapes] via une clé.
 */
internal fun Shapes.fromToken(value: ShapeKeyTokens): Shape {
    return when (value) {
        ShapeKeyTokens.CornerExtraLarge -> extraLarge
        ShapeKeyTokens.CornerExtraLargeTop -> extraLarge.top()
        ShapeKeyTokens.CornerExtraSmall -> extraSmall
        ShapeKeyTokens.CornerExtraSmallTop -> extraSmall.top()
        ShapeKeyTokens.CornerFull -> CircleShape
        ShapeKeyTokens.CornerLarge -> large
        ShapeKeyTokens.CornerLargeEnd -> large.end()
        ShapeKeyTokens.CornerLargeTop -> large.top()
        ShapeKeyTokens.CornerMedium -> medium
        ShapeKeyTokens.CornerNone -> RectangleShape
        ShapeKeyTokens.CornerSmall -> small
    }
}

/**
 * Extension pour accéder à une forme via une clé, en se basant sur le thème courant.
 */
internal val ShapeKeyTokens.value: Shape
    @Composable @ReadOnlyComposable get() = TailwindTheme.shapes.fromToken(this)

/**
 * CompositionLocal utilisé pour transmettre les formes par défaut dans la hiérarchie.
 */
internal val LocalShapes = staticCompositionLocalOf { Shapes() }