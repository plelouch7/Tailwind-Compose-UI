package com.verimsolution.tailwind_prod.tokens

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

/**
 * Objet contenant les définitions de formes (coins arrondis) pour le système de design.
 * Les valeurs sont inspirées des classes Tailwind CSS (ex. rounded-sm, rounded-lg) et adaptées à Jetpack Compose.
 */
internal object ShapeTokens {
    // Coins uniformes (tous les côtés)
    val None = RectangleShape  // rounded-none (0dp)
    val ExtraSmall = RoundedCornerShape(4.dp)  // rounded-sm (4dp)
    val Small = RoundedCornerShape(8.dp)       // rounded (8dp)
    val Medium = RoundedCornerShape(12.dp)     // rounded-md (12dp)
    val Large = RoundedCornerShape(16.dp)      // rounded-lg (16dp)
    val ExtraLarge = RoundedCornerShape(24.dp) // rounded-xl (24dp)
    val Full = CircleShape                     // rounded-full (forme circulaire)

    // Coins spécifiques : Haut (Top)
    val ExtraSmallTop = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)   // rounded-t-sm
    val SmallTop = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)        // rounded-t
    val MediumTop = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)     // rounded-t-md
    val LargeTop = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)      // rounded-t-lg
    val ExtraLargeTop = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp) // rounded-t-xl

    // Coins spécifiques : Fin (End, droite en LTR, gauche en RTL)
    val ExtraSmallEnd = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp)   // rounded-e-sm
    val SmallEnd = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)        // rounded-e
    val MediumEnd = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)     // rounded-e-md
    val LargeEnd = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)      // rounded-e-lg
    val ExtraLargeEnd = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp) // rounded-e-xl

    // Coins spécifiques : Début (Start, gauche en LTR, droite en RTL)
    val ExtraSmallStart = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)   // rounded-s-sm
    val SmallStart = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)        // rounded-s
    val MediumStart = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)     // rounded-s-md
    val LargeStart = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)      // rounded-s-lg
    val ExtraLargeStart = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp) // rounded-s-xl
    val CornerFull = RoundedCornerShape(100.dp)

    /**
     * Retourne la forme associée à un [ShapeKeyTokens].
     */
    fun fromToken(token: ShapeKeyTokens): androidx.compose.ui.graphics.Shape = when (token) {
        ShapeKeyTokens.None -> None
        ShapeKeyTokens.ExtraSmall -> ExtraSmall
        ShapeKeyTokens.Small -> Small
        ShapeKeyTokens.Medium -> Medium
        ShapeKeyTokens.Large -> Large
        ShapeKeyTokens.ExtraLarge -> ExtraLarge
        ShapeKeyTokens.Full -> Full
        ShapeKeyTokens.ExtraSmallTop -> ExtraSmallTop
        ShapeKeyTokens.SmallTop -> SmallTop
        ShapeKeyTokens.MediumTop -> MediumTop
        ShapeKeyTokens.LargeTop -> LargeTop
        ShapeKeyTokens.ExtraLargeTop -> ExtraLargeTop
        ShapeKeyTokens.ExtraSmallEnd -> ExtraSmallEnd
        ShapeKeyTokens.SmallEnd -> SmallEnd
        ShapeKeyTokens.MediumEnd -> MediumEnd
        ShapeKeyTokens.LargeEnd -> LargeEnd
        ShapeKeyTokens.ExtraLargeEnd -> ExtraLargeEnd
        ShapeKeyTokens.ExtraSmallStart -> ExtraSmallStart
        ShapeKeyTokens.SmallStart -> SmallStart
        ShapeKeyTokens.MediumStart -> MediumStart
        ShapeKeyTokens.LargeStart -> LargeStart
        ShapeKeyTokens.ExtraLargeStart -> ExtraLargeStart
        ShapeKeyTokens.CornerFull -> CornerFull
    }
}