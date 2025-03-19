package com.verimsolution.tailwind_prod.tokens

internal object ColorDarkTokens {
    // Fonds et surfaces
    val Background = PaletteTokens.Neutral.c900   // Fond principal sombre (neutral-900)
    val Surface = PaletteTokens.Neutral.c800      // Surface principale (neutral-800)
    val SurfaceBright = PaletteTokens.Neutral.c700 // Surface plus lumineuse (neutral-700)
    val SurfaceContainer = PaletteTokens.Neutral.c600 // Conteneur léger (neutral-600)
    val SurfaceContainerHigh = PaletteTokens.Neutral.c500 // Conteneur plus marqué (neutral-500)
    val SurfaceContainerHighest = PaletteTokens.Neutral.c400 // Conteneur fort (neutral-400)
    val SurfaceContainerLow = PaletteTokens.Neutral.c700 // Conteneur subtil (neutral-700)
    val SurfaceContainerLowest = PaletteTokens.Neutral.c800 // Conteneur minimal (neutral-800)
    val SurfaceDim = PaletteTokens.Neutral.c900   // Surface atténuée (neutral-900)
    val SurfaceVariant = PaletteTokens.Slate.c800 // Variante pour contraste (slate-800)
    val InverseSurface = PaletteTokens.Gray.c50   // Surface inversée (gray-50)
    val Scrim = PaletteTokens.Neutral.c900.copy(alpha = 0.4f) // Overlay semi-transparent

    // Texte sur fond/surface
    val OnBackground = PaletteTokens.Neutral.c100  // Texte sur fond (neutral-100)
    val OnSurface = PaletteTokens.Neutral.c100     // Texte sur surface (neutral-100)
    val OnSurfaceVariant = PaletteTokens.Slate.c300 // Variante texte (slate-300)
    val InverseOnSurface = PaletteTokens.Gray.c900  // Texte sur surface inversée (gray-900)

    // Couleurs primaires (basées sur Blue)
    val Primary = PaletteTokens.Blue.c400          // Bleu principal (blue-400, plus clair pour un thème sombre)
    val PrimaryLight = PaletteTokens.Blue.c300     // Bleu clair (blue-300)
    val PrimaryDark = PaletteTokens.Blue.c600      // Bleu sombre (blue-600)
    val OnPrimary = PaletteTokens.Neutral.c900     // Contraste sur Primary (neutral-900)
    val PrimaryContainer = PaletteTokens.Blue.c800 // Fond sombre (blue-800)
    val OnPrimaryContainer = PaletteTokens.Blue.c100 // Texte sur conteneur (blue-100)
    val InversePrimary = PaletteTokens.Blue.c200   // Primaire inversé (blue-200)

    // Couleurs secondaires (basées sur Emerald)
    val Secondary = PaletteTokens.Emerald.c400     // Vert émeraude principal (emerald-400)
    val SecondaryLight = PaletteTokens.Emerald.c300 // Vert clair (emerald-300)
    val SecondaryDark = PaletteTokens.Emerald.c600 // Vert sombre (emerald-600)
    val OnSecondary = PaletteTokens.Neutral.c900   // Contraste sur Secondary (neutral-900)
    val SecondaryContainer = PaletteTokens.Emerald.c800 // Fond sombre (emerald-800)
    val OnSecondaryContainer = PaletteTokens.Emerald.c100 // Texte sur conteneur (emerald-100)

    // Couleurs tertiaires (basées sur Purple)
    val Tertiary = PaletteTokens.Purple.c400       // Violet principal (purple-400)
    val TertiaryLight = PaletteTokens.Purple.c300  // Violet clair (purple-300)
    val TertiaryDark = PaletteTokens.Purple.c600   // Violet sombre (purple-600)
    val OnTertiary = PaletteTokens.Neutral.c900    // Contraste sur Tertiary (neutral-900)
    val TertiaryContainer = PaletteTokens.Purple.c800 // Fond sombre (purple-800)
    val OnTertiaryContainer = PaletteTokens.Purple.c100 // Texte sur conteneur (purple-100)

    // États spécifiques
    val Error = PaletteTokens.Red.c400             // Erreur (red-400)
    val OnError = PaletteTokens.Neutral.c900       // Contraste sur Error (neutral-900)
    val ErrorContainer = PaletteTokens.Red.c800    // Fond erreur (red-800)
    val OnErrorContainer = PaletteTokens.Red.c100  // Texte sur conteneur erreur (red-100)

    val Success = PaletteTokens.Green.c400         // Succès (green-400)
    val OnSuccess = PaletteTokens.Neutral.c900     // Contraste sur Success (neutral-900)
    val SuccessContainer = PaletteTokens.Green.c800 // Fond succès (green-800)
    val OnSuccessContainer = PaletteTokens.Green.c100 // Texte sur conteneur (green-100)

    val Info = PaletteTokens.Blue.c300             // Info (blue-300)
    val OnInfo = PaletteTokens.Neutral.c900        // Contraste sur Info (neutral-900)
    val InfoContainer = PaletteTokens.Blue.c800    // Fond info (blue-800)
    val OnInfoContainer = PaletteTokens.Blue.c100  // Texte sur conteneur (blue-100)

    val Warning = PaletteTokens.Yellow.c400        // Avertissement (yellow-400)
    val OnWarning = PaletteTokens.Neutral.c900     // Contraste sur Warning (neutral-900)
    val WarningContainer = PaletteTokens.Yellow.c800 // Fond avertissement (yellow-800)
    val OnWarningContainer = PaletteTokens.Yellow.c100 // Texte sur conteneur (yellow-100)

    // Contours et utilitaires
    val Outline = PaletteTokens.Slate.c500         // Bordure principale (slate-500)
    val OutlineLight = PaletteTokens.Slate.c400    // Bordure claire (slate-400)
    val OutlineDark = PaletteTokens.Slate.c600     // Bordure sombre (slate-600)
    val OutlineVariant = PaletteTokens.Slate.c300    // NeutralVariant30
    val Shadow = PaletteTokens.Neutral.c900.copy(alpha = 0.3f) // Ombre légère
    val SurfaceTint = Primary                      // Teinte de surface (Primary)
    val Text = PaletteTokens.Neutral.c100          // Texte par défaut (neutral-100)
    val TextSecondary = PaletteTokens.Neutral.c300 // Texte secondaire (neutral-300)

    // Suppression des tokens spécifiques à Material si non nécessaires
    // Exemple : PrimaryFixed, SecondaryFixedDim, etc., sauf si tu en as besoin
}