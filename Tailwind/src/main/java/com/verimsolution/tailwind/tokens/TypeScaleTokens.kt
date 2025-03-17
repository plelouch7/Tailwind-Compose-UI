package com.verimsolution.tailwind.tokens

import androidx.compose.ui.unit.sp

internal object TypeScaleTokens {
    // --- Body ---
    // Tailwind par défaut sur mobile :
    // text-base = 1rem = 16px, text-sm = 0.875rem = 14px, text-xs = 0.75rem = 12px
    val BodyLargeFont = TypefaceTokens.Plain
    val BodyLargeLineHeight = 24.0.sp     // 1.5 x 16px
    val BodyLargeSize = 16.sp             // text-base
    val BodyLargeTracking = 0.5.sp
    val BodyLargeWeight = TypefaceTokens.WeightRegular

    val BodyMediumFont = TypefaceTokens.Plain
    val BodyMediumLineHeight = 20.0.sp     // environ 1.43 x 14px
    val BodyMediumSize = 14.sp             // text-sm
    val BodyMediumTracking = 0.2.sp
    val BodyMediumWeight = TypefaceTokens.WeightRegular

    val BodySmallFont = TypefaceTokens.Plain
    val BodySmallLineHeight = 16.0.sp      // 1.33 x 12px (pour harmoniser)
    val BodySmallSize = 12.sp             // text-xs
    val BodySmallTracking = 0.4.sp
    val BodySmallWeight = TypefaceTokens.WeightRegular

    // --- Display ---
    // Pour les titres très importants, souvent utilisés sur mobile pour les accroches
    val DisplayLargeFont = TypefaceTokens.Brand
    val DisplayLargeLineHeight = 64.0.sp   // par exemple, pour un grand display
    val DisplayLargeSize = 57.sp           // taille inspirée de Tailwind (ex: text-5xl)
    val DisplayLargeTracking = -0.2.sp
    val DisplayLargeWeight = TypefaceTokens.WeightRegular

    val DisplayMediumFont = TypefaceTokens.Brand
    val DisplayMediumLineHeight = 52.0.sp   // text-4xl
    val DisplayMediumSize = 45.sp
    val DisplayMediumTracking = 0.0.sp
    val DisplayMediumWeight = TypefaceTokens.WeightRegular

    val DisplaySmallFont = TypefaceTokens.Brand
    val DisplaySmallLineHeight = 44.0.sp   // text-3xl
    val DisplaySmallSize = 36.sp
    val DisplaySmallTracking = 0.0.sp
    val DisplaySmallWeight = TypefaceTokens.WeightRegular

    // --- Headline ---
    // Pour des titres moins imposants, souvent utilisés pour les intertitres
    val HeadlineLargeFont = TypefaceTokens.Brand
    val HeadlineLargeLineHeight = 40.0.sp   // text-2xl
    val HeadlineLargeSize = 32.sp
    val HeadlineLargeTracking = 0.0.sp
    val HeadlineLargeWeight = TypefaceTokens.WeightRegular

    val HeadlineMediumFont = TypefaceTokens.Brand
    val HeadlineMediumLineHeight = 36.0.sp   // text-xl
    val HeadlineMediumSize = 28.sp
    val HeadlineMediumTracking = 0.0.sp
    val HeadlineMediumWeight = TypefaceTokens.WeightRegular

    val HeadlineSmallFont = TypefaceTokens.Brand
    val HeadlineSmallLineHeight = 32.0.sp   // text-lg
    val HeadlineSmallSize = 24.sp
    val HeadlineSmallTracking = 0.0.sp
    val HeadlineSmallWeight = TypefaceTokens.WeightRegular

    // --- Label ---
    // Pour des textes d’accompagnement courts ou des labels
    val LabelLargeFont = TypefaceTokens.Plain
    val LabelLargeLineHeight = 20.0.sp   // text-sm
    val LabelLargeSize = 14.sp
    val LabelLargeTracking = 0.1.sp
    val LabelLargeWeight = TypefaceTokens.WeightMedium

    val LabelMediumFont = TypefaceTokens.Plain
    val LabelMediumLineHeight = 16.0.sp   // text-xs
    val LabelMediumSize = 12.sp
    val LabelMediumTracking = 0.5.sp
    val LabelMediumWeight = TypefaceTokens.WeightMedium

    val LabelSmallFont = TypefaceTokens.Plain
    val LabelSmallLineHeight = 16.0.sp   // une valeur ajustée pour le mobile
    val LabelSmallSize = 11.sp           // légèrement inférieur pour une lisibilité sur petit écran
    val LabelSmallTracking = 0.5.sp
    val LabelSmallWeight = TypefaceTokens.WeightMedium

    // --- Title ---
    // Pour des titres de sections ou de cartes
    val TitleLargeFont = TypefaceTokens.Brand
    val TitleLargeLineHeight = 28.0.sp   // par exemple, text-base ou text-lg ajusté
    val TitleLargeSize = 22.sp
    val TitleLargeTracking = 0.0.sp
    val TitleLargeWeight = TypefaceTokens.WeightRegular

    val TitleMediumFont = TypefaceTokens.Plain
    val TitleMediumLineHeight = 24.0.sp   // text-base
    val TitleMediumSize = 16.sp
    val TitleMediumTracking = 0.2.sp
    val TitleMediumWeight = TypefaceTokens.WeightMedium

    val TitleSmallFont = TypefaceTokens.Plain
    val TitleSmallLineHeight = 20.0.sp   // text-sm
    val TitleSmallSize = 14.sp
    val TitleSmallTracking = 0.1.sp
    val TitleSmallWeight = TypefaceTokens.WeightMedium
}
