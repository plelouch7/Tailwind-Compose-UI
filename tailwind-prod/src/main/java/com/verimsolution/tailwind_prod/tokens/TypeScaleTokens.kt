package com.verimsolution.tailwind_prod.tokens

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

internal object TypeScaleTokens {
    // --- Display ---
    val DisplayLarge = TextStyle(
        fontFamily = TypefaceTokens.Display,
        fontWeight = TypefaceTokens.WeightBold,
        fontSize = 96.sp,          // Material H1 mobile
        lineHeight = 112.sp,       // Ratio ~1.17
        letterSpacing = (-1.5).sp  // Compression pour grands titres
    )

    val DisplayMedium = TextStyle(
        fontFamily = TypefaceTokens.Display,
        fontWeight = TypefaceTokens.WeightBold,
        fontSize = 60.sp,          // Material H2 mobile
        lineHeight = 72.sp,        // Ratio ~1.2
        letterSpacing = (-0.5).sp  // Compression légère
    )

    val DisplaySmall = TextStyle(
        fontFamily = TypefaceTokens.Display,
        fontWeight = TypefaceTokens.WeightBold,
        fontSize = 48.sp,          // Material H3 mobile
        lineHeight = 56.sp,        // Ratio ~1.17
        letterSpacing = 0.sp       // Pas de tracking
    )

    // --- Headline ---
    val HeadlineLarge = TextStyle(
        fontFamily = TypefaceTokens.Brand,
        fontWeight = TypefaceTokens.WeightSemiBold,
        fontSize = 34.sp,          // Material H4 mobile
        lineHeight = 40.sp,        // Ratio ~1.18
        letterSpacing = 0.sp       // Pas de tracking
    )

    val HeadlineMedium = TextStyle(
        fontFamily = TypefaceTokens.Brand,
        fontWeight = TypefaceTokens.WeightSemiBold,
        fontSize = 24.sp,          // Material H5 mobile
        lineHeight = 32.sp,        // Ratio ~1.33
        letterSpacing = 0.sp       // Pas de tracking
    )

    val HeadlineSmall = TextStyle(
        fontFamily = TypefaceTokens.Brand,
        fontWeight = TypefaceTokens.WeightSemiBold,
        fontSize = 20.sp,          // Material H6 mobile
        lineHeight = 28.sp,        // Ratio ~1.4
        letterSpacing = 0.sp       // Pas de tracking
    )

    // --- Title ---
    val TitleLarge = TextStyle(
        fontFamily = TypefaceTokens.Brand,
        fontWeight = TypefaceTokens.WeightBold,
        fontSize = 16.sp,          // Material Subtitle 1
        lineHeight = 24.sp,        // Ratio ~1.5
        letterSpacing = 0.15.sp    // Tracking léger
    )

    val TitleMedium = TextStyle(
        fontFamily = TypefaceTokens.Body,
        fontWeight = TypefaceTokens.WeightMedium,
        fontSize = 14.sp,          // Material Subtitle 2
        lineHeight = 20.sp,        // Ratio ~1.43
        letterSpacing = 0.1.sp     // Tracking subtil
    )

    val TitleSmall = TextStyle(
        fontFamily = TypefaceTokens.Body,
        fontWeight = TypefaceTokens.WeightMedium,
        fontSize = 12.sp,          // Ajusté pour mobile
        lineHeight = 16.sp,        // Ratio ~1.33
        letterSpacing = 0.1.sp     // Tracking subtil
    )

    // --- Body ---
    val BodyLarge = TextStyle(
        fontFamily = TypefaceTokens.Body,
        fontWeight = TypefaceTokens.WeightRegular,
        fontSize = 16.sp,          // Material Body 1
        lineHeight = 24.sp,        // Ratio ~1.5
        letterSpacing = 0.5.sp     // Tracking pour lisibilité
    )

    val BodyMedium = TextStyle(
        fontFamily = TypefaceTokens.Body,
        fontWeight = TypefaceTokens.WeightRegular,
        fontSize = 14.sp,          // Material Body 2
        lineHeight = 20.sp,        // Ratio ~1.43
        letterSpacing = 0.25.sp    // Tracking ajusté
    )

    val BodySmall = TextStyle(
        fontFamily = TypefaceTokens.Body,
        fontWeight = TypefaceTokens.WeightRegular,
        fontSize = 12.sp,          // Ajusté pour mobile
        lineHeight = 16.sp,        // Ratio ~1.33
        letterSpacing = 0.4.sp     // Tracking pour petits textes
    )

    // --- Label ---
    val LabelLarge = TextStyle(
        fontFamily = TypefaceTokens.Body,
        fontWeight = TypefaceTokens.WeightMedium,
        fontSize = 14.sp,          // Material Button
        lineHeight = 20.sp,        // Ratio ~1.43
        letterSpacing = 0.1.sp     // Tracking subtil
    )

    val LabelMedium = TextStyle(
        fontFamily = TypefaceTokens.Body,
        fontWeight = TypefaceTokens.WeightMedium,
        fontSize = 12.sp,          // Ajusté pour mobile
        lineHeight = 16.sp,        // Ratio ~1.33
        letterSpacing = 0.5.sp     // Tracking pour lisibilité
    )

    val LabelSmall = TextStyle(
        fontFamily = TypefaceTokens.Body,
        fontWeight = TypefaceTokens.WeightMedium,
        fontSize = 11.sp,          // Ajusté pour petits éléments
        lineHeight = 16.sp,        // Ratio ~1.45
        letterSpacing = 0.5.sp     // Tracking pour petits textes
    )

    // --- Caption & Overline ---
    val Caption = TextStyle(
        fontFamily = TypefaceTokens.Body,
        fontWeight = TypefaceTokens.WeightRegular,
        fontSize = 12.sp,          // Material Caption
        lineHeight = 16.sp,        // Ratio ~1.33
        letterSpacing = 0.4.sp     // Tracking pour lisibilité
    )

    val Overline = TextStyle(
        fontFamily = TypefaceTokens.Body,
        fontWeight = TypefaceTokens.WeightMedium,
        fontSize = 10.sp,          // Material Overline
        lineHeight = 14.sp,        // Ratio ~1.4
        letterSpacing = 1.5.sp     // Tracking large pour effet "overline"
    )

    fun fromToken(token: TypographyKeyTokens): TextStyle = when (token) {
        TypographyKeyTokens.DisplayLarge -> DisplayLarge
        TypographyKeyTokens.DisplayMedium -> DisplayMedium
        TypographyKeyTokens.DisplaySmall -> DisplaySmall
        TypographyKeyTokens.HeadlineLarge -> HeadlineLarge
        TypographyKeyTokens.HeadlineMedium -> HeadlineMedium
        TypographyKeyTokens.HeadlineSmall -> HeadlineSmall
        TypographyKeyTokens.TitleLarge -> TitleLarge
        TypographyKeyTokens.TitleMedium -> TitleMedium
        TypographyKeyTokens.TitleSmall -> TitleSmall
        TypographyKeyTokens.BodyLarge -> BodyLarge
        TypographyKeyTokens.BodyMedium -> BodyMedium
        TypographyKeyTokens.BodySmall -> BodySmall
        TypographyKeyTokens.LabelLarge -> LabelLarge
        TypographyKeyTokens.LabelMedium -> LabelMedium
        TypographyKeyTokens.LabelSmall -> LabelSmall
        TypographyKeyTokens.Caption -> Caption
        TypographyKeyTokens.Overline -> Overline
    }
}