package com.verimsolution.tailwind.tokens

import androidx.compose.ui.graphics.Color

internal object ColorLightTokens {
    // Fond et surface
    val Background = PaletteTokens.Gray50
    val Surface = PaletteTokens.Gray50
    val SurfaceBright = PaletteTokens.Gray100
    val SurfaceContainer = PaletteTokens.Gray200
    val SurfaceContainerHigh = PaletteTokens.Gray300
    val SurfaceContainerHighest = PaletteTokens.Gray400
    val SurfaceContainerLow = PaletteTokens.Gray100
    val SurfaceContainerLowest = PaletteTokens.Gray50
    val SurfaceDim = PaletteTokens.Gray50
    val Scrim = Color(0xFF000000).copy(alpha = 0.5f)

    // Texte sur fond/surface
    val OnBackground = PaletteTokens.Gray900
    val OnSurface = PaletteTokens.Gray900
    val InverseOnSurface = PaletteTokens.Gray100

    // Couleurs primaires (basées sur Blue)
    val Primary = PaletteTokens.Blue500
    val OnPrimary = PaletteTokens.Blue50
    val PrimaryContainer = PaletteTokens.Blue100
    val OnPrimaryContainer = PaletteTokens.Blue900
    val InversePrimary = PaletteTokens.Blue200
    val OnPrimaryFixed = PaletteTokens.Blue100
    val OnPrimaryFixedVariant = PaletteTokens.Blue300

    // Couleurs secondaires (basées sur Emerald)
    val Secondary = PaletteTokens.Emerald500
    val OnSecondary = PaletteTokens.Emerald50
    val SecondaryContainer = PaletteTokens.Emerald100
    val OnSecondaryContainer = PaletteTokens.Emerald900
    val OnSecondaryFixed = PaletteTokens.Emerald100
    val OnSecondaryFixedVariant = PaletteTokens.Emerald300

    // Couleurs tertiaires (basées sur Purple)
    val Tertiary = PaletteTokens.Purple500
    val OnTertiary = PaletteTokens.Purple50
    val TertiaryContainer = PaletteTokens.Purple100
    val OnTertiaryContainer = PaletteTokens.Purple900
    val OnTertiaryFixed = PaletteTokens.Purple100
    val OnTertiaryFixedVariant = PaletteTokens.Purple300

    // États d'erreur (basées sur Red)
    val Error = PaletteTokens.Red500
    val OnError = PaletteTokens.Red50
    val ErrorContainer = PaletteTokens.Red100
    val OnErrorContainer = PaletteTokens.Red900

    // Variantes neutres pour contours et éléments secondaires
    val Outline = PaletteTokens.Slate300
    val OutlineVariant = PaletteTokens.Slate100
    val OnSurfaceVariant = PaletteTokens.Slate600

    // Pour SurfaceTint, on utilise la couleur primaire
    val SurfaceTint = Primary

    // Nouveaux tokens pour le thème light
    val SurfaceVariant = PaletteTokens.Slate300  // Par exemple, une nuance claire de Slate
    val InverseSurface = PaletteTokens.Gray900    // Pour inverser, on utilise une nuance foncée

}
