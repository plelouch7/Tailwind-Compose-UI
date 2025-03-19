package com.verimsolution.tailwind_prod.tokens

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle

/**
 * Objet contenant les tokens pour les familles de polices et leurs variations dans le système de design.
 * Les valeurs sont inspirées des conventions Tailwind CSS et adaptées à Jetpack Compose.
 */
internal object TypefaceTokens {
    // Familles de polices
    val Brand = FontFamily.SansSerif  // Police principale pour l’identité de marque (ex. personnalisée ou sans-serif par défaut)
    val Body = FontFamily.SansSerif   // Police pour le corps du texte (ex. sans-serif par défaut)
    val Display = FontFamily.Serif    // Police pour les titres ou éléments décoratifs (ex. serif par défaut)

    // Poids des polices (FontWeight)
    val WeightThin = FontWeight.Thin       // 100, équivalent Tailwind font-thin
    val WeightExtraLight = FontWeight.ExtraLight // 200, équivalent Tailwind font-extralight
    val WeightLight = FontWeight.Light     // 300, équivalent Tailwind font-light
    val WeightRegular = FontWeight.Normal  // 400, équivalent Tailwind font-normal
    val WeightMedium = FontWeight.Medium   // 500, équivalent Tailwind font-medium
    val WeightSemiBold = FontWeight.SemiBold // 600, équivalent Tailwind font-semibold
    val WeightBold = FontWeight.Bold       // 700, équivalent Tailwind font-bold
    val WeightExtraBold = FontWeight.ExtraBold // 800, équivalent Tailwind font-extrabold
    val WeightBlack = FontWeight.Black     // 900, équivalent Tailwind font-black

    // Styles de polices (FontStyle)
    val StyleNormal = FontStyle.Normal    // Style normal (non italique)
    val StyleItalic = FontStyle.Italic    // Style italique

    /**
     * Retourne une configuration de police à partir d’un poids et d’un style.
     */
    fun toTextStyle(
        family: FontFamily = Body,
        weight: FontWeight = WeightRegular,
        style: FontStyle = StyleNormal
    ): TextStyle = TextStyle(
        fontFamily = family,
        fontWeight = weight,
        fontStyle = style
    )
}