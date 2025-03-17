package com.verimsolution.tailwind.tokens

import androidx.compose.ui.graphics.Color

internal object ColorDarkTokens {
    // Fond et surface
    val Background = PaletteTokens.Neutral600
    val Surface = PaletteTokens.Neutral600
    val SurfaceBright = PaletteTokens.Neutral500
    val SurfaceContainer = PaletteTokens.Neutral400
    val SurfaceContainerHigh = PaletteTokens.Neutral300
    val SurfaceContainerHighest = PaletteTokens.Neutral200
    val SurfaceContainerLow = PaletteTokens.Neutral700
    val SurfaceContainerLowest = PaletteTokens.Neutral800
    val SurfaceDim = PaletteTokens.Neutral600
    val Scrim = Color(0xFF000000)

    // Texte et contenus sur fond/surface
    val OnBackground = PaletteTokens.Neutral50
    val OnSurface = PaletteTokens.Neutral50
    val InverseOnSurface = PaletteTokens.Neutral200

    // Couleurs primaires (basées sur Blue)
    val Primary = PaletteTokens.Blue800          // équivalent à Primary80
    val PrimaryContainer = PaletteTokens.Blue300   // équivalent à Primary30
    val PrimaryFixed = PaletteTokens.Blue900       // équivalent à Primary90
    val PrimaryFixedDim = PaletteTokens.Blue800
    val OnPrimary = PaletteTokens.Blue200          // équivalent à Primary20
    val OnPrimaryContainer = PaletteTokens.Blue900   // équivalent à Primary90
    val OnPrimaryFixed = PaletteTokens.Blue100       // équivalent à Primary10
    val OnPrimaryFixedVariant = PaletteTokens.Blue300  // équivalent à Primary30
    val InversePrimary = PaletteTokens.Blue400       // équivalent à Primary40

    // Couleurs secondaires (basées sur Emerald)
    val Secondary = PaletteTokens.Emerald800         // Secondary80
    val SecondaryContainer = PaletteTokens.Emerald300  // Secondary30
    val SecondaryFixed = PaletteTokens.Emerald900      // Secondary90
    val SecondaryFixedDim = PaletteTokens.Emerald800
    val OnSecondary = PaletteTokens.Emerald200         // Secondary20
    val OnSecondaryContainer = PaletteTokens.Emerald900  // Secondary90
    val OnSecondaryFixed = PaletteTokens.Emerald100      // Secondary10
    val OnSecondaryFixedVariant = PaletteTokens.Emerald300 // Secondary30

    // Couleurs tertiaires (basées sur Purple)
    val Tertiary = PaletteTokens.Purple800          // Tertiary80
    val TertiaryContainer = PaletteTokens.Purple300   // Tertiary30
    val TertiaryFixed = PaletteTokens.Purple900       // Tertiary90
    val TertiaryFixedDim = PaletteTokens.Purple800
    val OnTertiary = PaletteTokens.Purple200          // Tertiary20
    val OnTertiaryContainer = PaletteTokens.Purple900   // Tertiary90
    val OnTertiaryFixed = PaletteTokens.Purple100       // Tertiary10
    val OnTertiaryFixedVariant = PaletteTokens.Purple300  // Tertiary30

    // États d'erreur (basés sur Red)
    val Error = PaletteTokens.Red800         // Error80
    val ErrorContainer = PaletteTokens.Red300  // Error30
    val OnError = PaletteTokens.Red200         // Error20
    val OnErrorContainer = PaletteTokens.Red900  // Error90

    // Variantes pour les contours et autres éléments
    // Ici, nous utilisons la palette Slate pour les variantes neutres
    val Outline = PaletteTokens.Slate600         // NeutralVariant60
    val OutlineVariant = PaletteTokens.Slate300    // NeutralVariant30
    val OnSurfaceVariant = PaletteTokens.Slate800  // NeutralVariant80

    // Pour SurfaceTint, nous utilisons la couleur primaire
    val SurfaceTint = Primary

    // Nouveaux tokens pour le thème dark
    val SurfaceVariant = PaletteTokens.Slate700  // Par exemple, une nuance plus foncée de Slate pour le dark
    val InverseSurface = PaletteTokens.Gray50     // L'inverse d'une surface sombre est une couleur claire

}

