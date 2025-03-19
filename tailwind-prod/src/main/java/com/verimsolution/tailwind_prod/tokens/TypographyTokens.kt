package com.verimsolution.tailwind_prod.tokens

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import com.verimsolution.tailwind_prod.internal.defaultPlatformTextStyle

internal object TypographyTokens {
    // --- Display ---
    val DisplayLarge = TypeScaleTokens.DisplayLarge.merge(DefaultTextStyle)
    val DisplayMedium = TypeScaleTokens.DisplayMedium.merge(DefaultTextStyle)
    val DisplaySmall = TypeScaleTokens.DisplaySmall.merge(DefaultTextStyle)

    // --- Headline ---
    val HeadlineLarge = TypeScaleTokens.HeadlineLarge.merge(DefaultTextStyle)
    val HeadlineMedium = TypeScaleTokens.HeadlineMedium.merge(DefaultTextStyle)
    val HeadlineSmall = TypeScaleTokens.HeadlineSmall.merge(DefaultTextStyle)

    // --- Title ---
    val TitleLarge = TypeScaleTokens.TitleLarge.merge(DefaultTextStyle)
    val TitleMedium = TypeScaleTokens.TitleMedium.merge(DefaultTextStyle)
    val TitleSmall = TypeScaleTokens.TitleSmall.merge(DefaultTextStyle)

    // --- Body ---
    val BodyLarge = TypeScaleTokens.BodyLarge.merge(DefaultTextStyle)
    val BodyMedium = TypeScaleTokens.BodyMedium.merge(DefaultTextStyle)
    val BodySmall = TypeScaleTokens.BodySmall.merge(DefaultTextStyle)

    // --- Label ---
    val LabelLarge = TypeScaleTokens.LabelLarge.merge(DefaultTextStyle)
    val LabelMedium = TypeScaleTokens.LabelMedium.merge(DefaultTextStyle)
    val LabelSmall = TypeScaleTokens.LabelSmall.merge(DefaultTextStyle)

    // --- Caption & Overline ---
    val Caption = TypeScaleTokens.Caption.merge(DefaultTextStyle)
    val Overline = TypeScaleTokens.Overline.merge(DefaultTextStyle)

    /**
     * Retourne un [TextStyle] à partir d’un [TypographyKeyTokens].
     */
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

internal val DefaultLineHeightStyle = LineHeightStyle(
    alignment = LineHeightStyle.Alignment.Center,
    trim = LineHeightStyle.Trim.None
)

internal val DefaultTextStyle = TextStyle.Default.copy(
    platformStyle = defaultPlatformTextStyle(),
    lineHeightStyle = DefaultLineHeightStyle
)
