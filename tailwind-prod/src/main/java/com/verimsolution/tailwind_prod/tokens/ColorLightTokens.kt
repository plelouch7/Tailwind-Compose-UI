package com.verimsolution.tailwind_prod.tokens

import androidx.compose.ui.graphics.Color

internal object ColorLightTokens {
    // Fonds et surfaces
    val Background = PaletteTokens.Gray.c50      // Fond principal clair (gray-50)
    val Surface = Color.White            // Surface principale (blanc)
    val SurfaceBright = PaletteTokens.Gray.c100  // Surface plus lumineuse (gray-100)
    val SurfaceContainer = PaletteTokens.Gray.c200 // Conteneur léger (gray-200)
    val SurfaceContainerHigh = PaletteTokens.Gray.c300 // Conteneur plus marqué (gray-300)
    val SurfaceContainerHighest = PaletteTokens.Gray.c400 // Conteneur fort (gray-400)
    val SurfaceContainerLow = PaletteTokens.Gray.c100 // Conteneur subtil (gray-100)
    val SurfaceContainerLowest = PaletteTokens.Gray.c50 // Conteneur minimal (gray-50)
    val SurfaceDim = PaletteTokens.Gray.c200     // Surface atténuée (gray-200)
    val SurfaceVariant = PaletteTokens.Slate.c200 // Variante pour contraste (slate-200)
    val InverseSurface = PaletteTokens.Gray.c900  // Surface inversée (gray-900)
    val Scrim = PaletteTokens.Gray.c900.copy(alpha = 0.4f) // Overlay semi-transparent

    // Texte sur fond/surface
    val OnBackground = PaletteTokens.Gray.c800   // Texte sur fond (gray-800)
    val OnSurface = PaletteTokens.Gray.c800      // Texte sur surface (gray-800)
    val OnSurfaceVariant = PaletteTokens.Slate.c600 // Variante texte (slate-600)
    val InverseOnSurface = PaletteTokens.Gray.c100 // Texte sur surface inversée (gray-100)

    // Couleurs primaires (basées sur Blue)
    val Primary = PaletteTokens.Blue.c500        // Bleu principal (blue-500)
    val PrimaryLight = PaletteTokens.Blue.c300   // Bleu clair (blue-300)
    val PrimaryDark = PaletteTokens.Blue.c700    // Bleu sombre (blue-700)
    val OnPrimary = Color.White          // Contraste sur Primary (blanc)
    val PrimaryContainer = PaletteTokens.Blue.c100 // Fond léger (blue-100)
    val OnPrimaryContainer = PaletteTokens.Blue.c900 // Texte sur conteneur (blue-900)
    val InversePrimary = PaletteTokens.Blue.c200 // Primaire inversé (blue-200)

    // Couleurs secondaires (basées sur Emerald)
    val Secondary = PaletteTokens.Emerald.c500   // Vert émeraude principal (emerald-500)
    val SecondaryLight = PaletteTokens.Emerald.c300 // Vert clair (emerald-300)
    val SecondaryDark = PaletteTokens.Emerald.c700 // Vert sombre (emerald-700)
    val OnSecondary = Color.White        // Contraste sur Secondary (blanc)
    val SecondaryContainer = PaletteTokens.Emerald.c100 // Fond léger (emerald-100)
    val OnSecondaryContainer = PaletteTokens.Emerald.c900 // Texte sur conteneur (emerald-900)

    // Couleurs tertiaires (basées sur Purple)
    val Tertiary = PaletteTokens.Purple.c500     // Violet principal (purple-500)
    val TertiaryLight = PaletteTokens.Purple.c300 // Violet clair (purple-300)
    val TertiaryDark = PaletteTokens.Purple.c700 // Violet sombre (purple-700)
    val OnTertiary = Color.White         // Contraste sur Tertiary (blanc)
    val TertiaryContainer = PaletteTokens.Purple.c100 // Fond léger (purple-100)
    val OnTertiaryContainer = PaletteTokens.Purple.c900 // Texte sur conteneur (purple-900)

    // États spécifiques
    val Error = PaletteTokens.Red.c500           // Erreur (red-500)
    val OnError = Color.White            // Contraste sur Error (blanc)
    val ErrorContainer = PaletteTokens.Red.c100  // Fond erreur (red-100)
    val OnErrorContainer = PaletteTokens.Red.c900 // Texte sur conteneur erreur (red-900)

    val Success = PaletteTokens.Green.c500       // Succès (green-500)
    val OnSuccess = Color.White          // Contraste sur Success (blanc)
    val SuccessContainer = PaletteTokens.Green.c100 // Fond succès (green-100)
    val OnSuccessContainer = PaletteTokens.Green.c900 // Texte sur conteneur (green-900)

    val Info = PaletteTokens.Blue.c400           // Info (blue-400)
    val OnInfo = Color.White             // Contraste sur Info (blanc)
    val InfoContainer = PaletteTokens.Blue.c50   // Fond info (blue-50)
    val OnInfoContainer = PaletteTokens.Blue.c900 // Texte sur conteneur (blue-900)

    val Warning = PaletteTokens.Yellow.c500      // Avertissement (yellow-500)
    val OnWarning = PaletteTokens.Gray.c900      // Contraste sur Warning (gray-900)
    val WarningContainer = PaletteTokens.Yellow.c100 // Fond avertissement (yellow-100)
    val OnWarningContainer = PaletteTokens.Yellow.c900 // Texte sur conteneur (yellow-900)

    // Contours et utilitaires
    val Outline = PaletteTokens.Slate.c400       // Bordure principale (slate-400)
    val OutlineLight = PaletteTokens.Slate.c200  // Bordure claire (slate-200)
    val OutlineDark = PaletteTokens.Slate.c600   // Bordure sombre (slate-600)
    val OutlineVariant = PaletteTokens.Slate.c100    // Slate-100
    val Shadow = PaletteTokens.Gray.c500.copy(alpha = 0.2f) // Ombre légère
    val SurfaceTint = Primary                    // Teinte de surface (Primary)
    val Text = PaletteTokens.Gray.c800           // Texte par défaut (gray-800)
    val TextSecondary = PaletteTokens.Gray.c500  // Texte secondaire (gray-500)
}
