package com.verimsolution.tailwind_prod

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.verimsolution.tailwind_prod.tokens.ColorDarkTokens
import com.verimsolution.tailwind_prod.tokens.ColorLightTokens
import com.verimsolution.tailwind_prod.tokens.ColorSchemeKeyTokens
import kotlin.math.ln

@Immutable
class ColorScheme(
    // Couleurs primaires
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val inversePrimary: Color,
    val primaryLight: Color,
    val primaryDark: Color,

    // Couleurs secondaires
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val secondaryLight: Color,
    val secondaryDark: Color,

    // Couleurs tertiaires
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val tertiaryLight: Color,
    val tertiaryDark: Color,

    // États spécifiques
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val info: Color,
    val onInfo: Color,
    val infoContainer: Color,
    val onInfoContainer: Color,
    val warning: Color,
    val onWarning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,

    // Fond et surfaces
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val surfaceTint: Color,
    val inverseSurface: Color,
    val inverseOnSurface: Color,
    val surfaceBright: Color,
    val surfaceDim: Color,
    val surfaceContainer: Color,
    val surfaceContainerHigh: Color,
    val surfaceContainerHighest: Color,
    val surfaceContainerLow: Color,
    val surfaceContainerLowest: Color,

    // Contours et utilitaires
    val outline: Color,
    val outlineLight: Color,
    val outlineDark: Color,
    val outlineVariant: Color,
    val scrim: Color,
    val shadow: Color,
    val text: Color,
    val textSecondary: Color
) {

    /** Retourne une copie de ce ColorScheme avec des valeurs éventuellement remplacées. */
    fun copy(
        primary: Color = this.primary,
        onPrimary: Color = this.onPrimary,
        primaryContainer: Color = this.primaryContainer,
        onPrimaryContainer: Color = this.onPrimaryContainer,
        inversePrimary: Color = this.inversePrimary,
        primaryLight: Color = this.primaryLight,
        primaryDark: Color = this.primaryDark,
        secondary: Color = this.secondary,
        onSecondary: Color = this.onSecondary,
        secondaryContainer: Color = this.secondaryContainer,
        onSecondaryContainer: Color = this.onSecondaryContainer,
        secondaryLight: Color = this.secondaryLight,
        secondaryDark: Color = this.secondaryDark,
        tertiary: Color = this.tertiary,
        onTertiary: Color = this.onTertiary,
        tertiaryContainer: Color = this.tertiaryContainer,
        onTertiaryContainer: Color = this.onTertiaryContainer,
        tertiaryLight: Color = this.tertiaryLight,
        tertiaryDark: Color = this.tertiaryDark,
        error: Color = this.error,
        onError: Color = this.onError,
        errorContainer: Color = this.errorContainer,
        onErrorContainer: Color = this.onErrorContainer,
        success: Color = this.success,
        onSuccess: Color = this.onSuccess,
        successContainer: Color = this.successContainer,
        onSuccessContainer: Color = this.onSuccessContainer,
        info: Color = this.info,
        onInfo: Color = this.onInfo,
        infoContainer: Color = this.infoContainer,
        onInfoContainer: Color = this.onInfoContainer,
        warning: Color = this.warning,
        onWarning: Color = this.onWarning,
        warningContainer: Color = this.warningContainer,
        onWarningContainer: Color = this.onWarningContainer,
        background: Color = this.background,
        onBackground: Color = this.onBackground,
        surface: Color = this.surface,
        onSurface: Color = this.onSurface,
        surfaceVariant: Color = this.surfaceVariant,
        onSurfaceVariant: Color = this.onSurfaceVariant,
        surfaceTint: Color = this.surfaceTint,
        inverseSurface: Color = this.inverseSurface,
        inverseOnSurface: Color = this.inverseOnSurface,
        surfaceBright: Color = this.surfaceBright,
        surfaceDim: Color = this.surfaceDim,
        surfaceContainer: Color = this.surfaceContainer,
        surfaceContainerHigh: Color = this.surfaceContainerHigh,
        surfaceContainerHighest: Color = this.surfaceContainerHighest,
        surfaceContainerLow: Color = this.surfaceContainerLow,
        surfaceContainerLowest: Color = this.surfaceContainerLowest,
        outline: Color = this.outline,
        outlineLight: Color = this.outlineLight,
        outlineDark: Color = this.outlineDark,
        outlineVariant: Color = this.outlineVariant,
        scrim: Color = this.scrim,
        shadow: Color = this.shadow,
        text: Color = this.text,
        textSecondary: Color = this.textSecondary
    ): ColorScheme = ColorScheme(
        primary,
        onPrimary,
        primaryContainer,
        onPrimaryContainer,
        inversePrimary,
        primaryLight,
        primaryDark,
        secondary,
        onSecondary,
        secondaryContainer,
        onSecondaryContainer,
        secondaryLight,
        secondaryDark,
        tertiary,
        onTertiary,
        tertiaryContainer,
        onTertiaryContainer,
        tertiaryLight,
        tertiaryDark,
        error,
        onError,
        errorContainer,
        onErrorContainer,
        success,
        onSuccess,
        successContainer,
        onSuccessContainer,
        info,
        onInfo,
        infoContainer,
        onInfoContainer,
        warning,
        onWarning,
        warningContainer,
        onWarningContainer,
        background,
        onBackground,
        surface,
        onSurface,
        surfaceVariant,
        onSurfaceVariant,
        surfaceTint,
        inverseSurface,
        inverseOnSurface,
        surfaceBright,
        surfaceDim,
        surfaceContainer,
        surfaceContainerHigh,
        surfaceContainerHighest,
        surfaceContainerLow,
        surfaceContainerLowest,
        outline,
        outlineLight,
        outlineDark,
        outlineVariant,
        scrim,
        shadow,
        text,
        textSecondary
    )

    override fun toString(): String = "ColorScheme(" + // Simplifié pour lisibilité
            "primary=$primary, onPrimary=$onPrimary, background=$background, onBackground=$onBackground, " +
            "surface=$surface, onSurface=$onSurface, error=$error, onError=$onError, ...)"
}

// Schéma clair basé sur ColorLightTokens
fun lightColorScheme(
    primary: Color = ColorLightTokens.Primary,
    onPrimary: Color = ColorLightTokens.OnPrimary,
    primaryContainer: Color = ColorLightTokens.PrimaryContainer,
    onPrimaryContainer: Color = ColorLightTokens.OnPrimaryContainer,
    inversePrimary: Color = ColorLightTokens.InversePrimary,
    primaryLight: Color = ColorLightTokens.PrimaryLight,
    primaryDark: Color = ColorLightTokens.PrimaryDark,
    secondary: Color = ColorLightTokens.Secondary,
    onSecondary: Color = ColorLightTokens.OnSecondary,
    secondaryContainer: Color = ColorLightTokens.SecondaryContainer,
    onSecondaryContainer: Color = ColorLightTokens.OnSecondaryContainer,
    secondaryLight: Color = ColorLightTokens.SecondaryLight,
    secondaryDark: Color = ColorLightTokens.SecondaryDark,
    tertiary: Color = ColorLightTokens.Tertiary,
    onTertiary: Color = ColorLightTokens.OnTertiary,
    tertiaryContainer: Color = ColorLightTokens.TertiaryContainer,
    onTertiaryContainer: Color = ColorLightTokens.OnTertiaryContainer,
    tertiaryLight: Color = ColorLightTokens.TertiaryLight,
    tertiaryDark: Color = ColorLightTokens.TertiaryDark,
    error: Color = ColorLightTokens.Error,
    onError: Color = ColorLightTokens.OnError,
    errorContainer: Color = ColorLightTokens.ErrorContainer,
    onErrorContainer: Color = ColorLightTokens.OnErrorContainer,
    success: Color = ColorLightTokens.Success,
    onSuccess: Color = ColorLightTokens.OnSuccess,
    successContainer: Color = ColorLightTokens.SuccessContainer,
    onSuccessContainer: Color = ColorLightTokens.OnSuccessContainer,
    info: Color = ColorLightTokens.Info,
    onInfo: Color = ColorLightTokens.OnInfo,
    infoContainer: Color = ColorLightTokens.InfoContainer,
    onInfoContainer: Color = ColorLightTokens.OnInfoContainer,
    warning: Color = ColorLightTokens.Warning,
    onWarning: Color = ColorLightTokens.OnWarning,
    warningContainer: Color = ColorLightTokens.WarningContainer,
    onWarningContainer: Color = ColorLightTokens.OnWarningContainer,
    background: Color = ColorLightTokens.Background,
    onBackground: Color = ColorLightTokens.OnBackground,
    surface: Color = ColorLightTokens.Surface,
    onSurface: Color = ColorLightTokens.OnSurface,
    surfaceVariant: Color = ColorLightTokens.SurfaceVariant,
    onSurfaceVariant: Color = ColorLightTokens.OnSurfaceVariant,
    surfaceTint: Color = primary,
    inverseSurface: Color = ColorLightTokens.InverseSurface,
    inverseOnSurface: Color = ColorLightTokens.InverseOnSurface,
    surfaceBright: Color = ColorLightTokens.SurfaceBright,
    surfaceDim: Color = ColorLightTokens.SurfaceDim,
    surfaceContainer: Color = ColorLightTokens.SurfaceContainer,
    surfaceContainerHigh: Color = ColorLightTokens.SurfaceContainerHigh,
    surfaceContainerHighest: Color = ColorLightTokens.SurfaceContainerHighest,
    surfaceContainerLow: Color = ColorLightTokens.SurfaceContainerLow,
    surfaceContainerLowest: Color = ColorLightTokens.SurfaceContainerLowest,
    outline: Color = ColorLightTokens.Outline,
    outlineLight: Color = ColorLightTokens.OutlineLight,
    outlineDark: Color = ColorLightTokens.OutlineDark,
    outlineVariant: Color = ColorLightTokens.OutlineVariant,
    scrim: Color = ColorLightTokens.Scrim,
    shadow: Color = ColorLightTokens.Shadow,
    text: Color = ColorLightTokens.Text,
    textSecondary: Color = ColorLightTokens.TextSecondary
): ColorScheme = ColorScheme(
    primary,
    onPrimary,
    primaryContainer,
    onPrimaryContainer,
    inversePrimary,
    primaryLight,
    primaryDark,
    secondary,
    onSecondary,
    secondaryContainer,
    onSecondaryContainer,
    secondaryLight,
    secondaryDark,
    tertiary,
    onTertiary,
    tertiaryContainer,
    onTertiaryContainer,
    tertiaryLight,
    tertiaryDark,
    error,
    onError,
    errorContainer,
    onErrorContainer,
    success,
    onSuccess,
    successContainer,
    onSuccessContainer,
    info,
    onInfo,
    infoContainer,
    onInfoContainer,
    warning,
    onWarning,
    warningContainer,
    onWarningContainer,
    background,
    onBackground,
    surface,
    onSurface,
    surfaceVariant,
    onSurfaceVariant,
    surfaceTint,
    inverseSurface,
    inverseOnSurface,
    surfaceBright,
    surfaceDim,
    surfaceContainer,
    surfaceContainerHigh,
    surfaceContainerHighest,
    surfaceContainerLow,
    surfaceContainerLowest,
    outline,
    outlineLight,
    outlineDark,
    outlineVariant,
    scrim,
    shadow,
    text,
    textSecondary
)

// Schéma sombre basé sur ColorDarkTokens
fun darkColorScheme(
    primary: Color = ColorDarkTokens.Primary,
    onPrimary: Color = ColorDarkTokens.OnPrimary,
    primaryContainer: Color = ColorDarkTokens.PrimaryContainer,
    onPrimaryContainer: Color = ColorDarkTokens.OnPrimaryContainer,
    inversePrimary: Color = ColorDarkTokens.InversePrimary,
    primaryLight: Color = ColorDarkTokens.PrimaryLight,
    primaryDark: Color = ColorDarkTokens.PrimaryDark,
    secondary: Color = ColorDarkTokens.Secondary,
    onSecondary: Color = ColorDarkTokens.OnSecondary,
    secondaryContainer: Color = ColorDarkTokens.SecondaryContainer,
    onSecondaryContainer: Color = ColorDarkTokens.OnSecondaryContainer,
    secondaryLight: Color = ColorDarkTokens.SecondaryLight,
    secondaryDark: Color = ColorDarkTokens.SecondaryDark,
    tertiary: Color = ColorDarkTokens.Tertiary,
    onTertiary: Color = ColorDarkTokens.OnTertiary,
    tertiaryContainer: Color = ColorDarkTokens.TertiaryContainer,
    onTertiaryContainer: Color = ColorDarkTokens.OnTertiaryContainer,
    tertiaryLight: Color = ColorDarkTokens.TertiaryLight,
    tertiaryDark: Color = ColorDarkTokens.TertiaryDark,
    error: Color = ColorDarkTokens.Error,
    onError: Color = ColorDarkTokens.OnError,
    errorContainer: Color = ColorDarkTokens.ErrorContainer,
    onErrorContainer: Color = ColorDarkTokens.OnErrorContainer,
    success: Color = ColorDarkTokens.Success,
    onSuccess: Color = ColorDarkTokens.OnSuccess,
    successContainer: Color = ColorDarkTokens.SuccessContainer,
    onSuccessContainer: Color = ColorDarkTokens.OnSuccessContainer,
    info: Color = ColorDarkTokens.Info,
    onInfo: Color = ColorDarkTokens.OnInfo,
    infoContainer: Color = ColorDarkTokens.InfoContainer,
    onInfoContainer: Color = ColorDarkTokens.OnInfoContainer,
    warning: Color = ColorDarkTokens.Warning,
    onWarning: Color = ColorDarkTokens.OnWarning,
    warningContainer: Color = ColorDarkTokens.WarningContainer,
    onWarningContainer: Color = ColorDarkTokens.OnWarningContainer,
    background: Color = ColorDarkTokens.Background,
    onBackground: Color = ColorDarkTokens.OnBackground,
    surface: Color = ColorDarkTokens.Surface,
    onSurface: Color = ColorDarkTokens.OnSurface,
    surfaceVariant: Color = ColorDarkTokens.SurfaceVariant,
    onSurfaceVariant: Color = ColorDarkTokens.OnSurfaceVariant,
    surfaceTint: Color = primary,
    inverseSurface: Color = ColorDarkTokens.InverseSurface,
    inverseOnSurface: Color = ColorDarkTokens.InverseOnSurface,
    surfaceBright: Color = ColorDarkTokens.SurfaceBright,
    surfaceDim: Color = ColorDarkTokens.SurfaceDim,
    surfaceContainer: Color = ColorDarkTokens.SurfaceContainer,
    surfaceContainerHigh: Color = ColorDarkTokens.SurfaceContainerHigh,
    surfaceContainerHighest: Color = ColorDarkTokens.SurfaceContainerHighest,
    surfaceContainerLow: Color = ColorDarkTokens.SurfaceContainerLow,
    surfaceContainerLowest: Color = ColorDarkTokens.SurfaceContainerLowest,
    outline: Color = ColorDarkTokens.Outline,
    outlineLight: Color = ColorDarkTokens.OutlineLight,
    outlineDark: Color = ColorDarkTokens.OutlineDark,
    outlineVariant: Color = ColorDarkTokens.OutlineVariant,
    scrim: Color = ColorDarkTokens.Scrim,
    shadow: Color = ColorDarkTokens.Shadow,
    text: Color = ColorDarkTokens.Text,
    textSecondary: Color = ColorDarkTokens.TextSecondary
): ColorScheme = ColorScheme(
    primary,
    onPrimary,
    primaryContainer,
    onPrimaryContainer,
    inversePrimary,
    primaryLight,
    primaryDark,
    secondary,
    onSecondary,
    secondaryContainer,
    onSecondaryContainer,
    secondaryLight,
    secondaryDark,
    tertiary,
    onTertiary,
    tertiaryContainer,
    onTertiaryContainer,
    tertiaryLight,
    tertiaryDark,
    error,
    onError,
    errorContainer,
    onErrorContainer,
    success,
    onSuccess,
    successContainer,
    onSuccessContainer,
    info,
    onInfo,
    infoContainer,
    onInfoContainer,
    warning,
    onWarning,
    warningContainer,
    onWarningContainer,
    background,
    onBackground,
    surface,
    onSurface,
    surfaceVariant,
    onSurfaceVariant,
    surfaceTint,
    inverseSurface,
    inverseOnSurface,
    surfaceBright,
    surfaceDim,
    surfaceContainer,
    surfaceContainerHigh,
    surfaceContainerHighest,
    surfaceContainerLow,
    surfaceContainerLowest,
    outline,
    outlineLight,
    outlineDark,
    outlineVariant,
    scrim,
    shadow,
    text,
    textSecondary
)

@Stable
fun ColorScheme.contentColorFor(backgroundColor: Color): Color = when (backgroundColor) {
    primary -> onPrimary
    secondary -> onSecondary
    tertiary -> onTertiary
    background -> onBackground
    error -> onError
    success -> onSuccess
    info -> onInfo
    warning -> onWarning
    primaryContainer -> onPrimaryContainer
    secondaryContainer -> onSecondaryContainer
    tertiaryContainer -> onTertiaryContainer
    errorContainer -> onErrorContainer
    successContainer -> onSuccessContainer
    infoContainer -> onInfoContainer
    warningContainer -> onWarningContainer
    inverseSurface -> inverseOnSurface
    surface -> onSurface
    surfaceVariant -> onSurfaceVariant
    surfaceBright -> onSurface
    surfaceContainer -> onSurface
    surfaceContainerHigh -> onSurface
    surfaceContainerHighest -> onSurface
    surfaceContainerLow -> onSurface
    surfaceContainerLowest -> onSurface
    else -> Color.Unspecified
}

@Composable
@ReadOnlyComposable
fun contentColorFor(backgroundColor: Color): Color =
    TailwindTheme.colorScheme.contentColorFor(backgroundColor).takeOrElse {
        LocalContentColor.current
    }

@Composable
@ReadOnlyComposable
internal fun ColorScheme.applyTonalElevation(backgroundColor: Color, elevation: Dp): Color {
    val tonalElevationEnabled = LocalTonalElevationEnabled.current
    return if (backgroundColor == surface && tonalElevationEnabled) {
        surfaceColorAtElevation(elevation)
    } else {
        backgroundColor
    }
}

@Stable
fun ColorScheme.surfaceColorAtElevation(elevation: Dp): Color {
    if (elevation == 0.dp) return surface
    val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
    return surfaceTint.copy(alpha = alpha).compositeOver(surface)
}

internal fun expressiveLightColorScheme() = lightColorScheme()

@Stable
internal fun ColorScheme.fromToken(value: ColorSchemeKeyTokens): Color = when (value) {
    ColorSchemeKeyTokens.Background -> background
    ColorSchemeKeyTokens.Error -> error
    ColorSchemeKeyTokens.ErrorContainer -> errorContainer
    ColorSchemeKeyTokens.InverseOnSurface -> inverseOnSurface
    ColorSchemeKeyTokens.InversePrimary -> inversePrimary
    ColorSchemeKeyTokens.InverseSurface -> inverseSurface
    ColorSchemeKeyTokens.OnBackground -> onBackground
    ColorSchemeKeyTokens.OnError -> onError
    ColorSchemeKeyTokens.OnErrorContainer -> onErrorContainer
    ColorSchemeKeyTokens.OnPrimary -> onPrimary
    ColorSchemeKeyTokens.OnPrimaryContainer -> onPrimaryContainer
    ColorSchemeKeyTokens.OnSecondary -> onSecondary
    ColorSchemeKeyTokens.OnSecondaryContainer -> onSecondaryContainer
    ColorSchemeKeyTokens.OnSurface -> onSurface
    ColorSchemeKeyTokens.OnSurfaceVariant -> onSurfaceVariant
    ColorSchemeKeyTokens.OnTertiary -> onTertiary
    ColorSchemeKeyTokens.OnTertiaryContainer -> onTertiaryContainer
    ColorSchemeKeyTokens.Outline -> outline
    ColorSchemeKeyTokens.OutlineVariant -> outlineVariant
    ColorSchemeKeyTokens.Primary -> primary
    ColorSchemeKeyTokens.PrimaryContainer -> primaryContainer
    ColorSchemeKeyTokens.Scrim -> scrim
    ColorSchemeKeyTokens.Secondary -> secondary
    ColorSchemeKeyTokens.SecondaryContainer -> secondaryContainer
    ColorSchemeKeyTokens.Surface -> surface
    ColorSchemeKeyTokens.SurfaceVariant -> surfaceVariant
    ColorSchemeKeyTokens.SurfaceBright -> surfaceBright
    ColorSchemeKeyTokens.SurfaceContainer -> surfaceContainer
    ColorSchemeKeyTokens.SurfaceContainerHigh -> surfaceContainerHigh
    ColorSchemeKeyTokens.SurfaceContainerHighest -> surfaceContainerHighest
    ColorSchemeKeyTokens.SurfaceContainerLow -> surfaceContainerLow
    ColorSchemeKeyTokens.SurfaceContainerLowest -> surfaceContainerLowest
    ColorSchemeKeyTokens.SurfaceDim -> surfaceDim
    ColorSchemeKeyTokens.SurfaceTint -> surfaceTint
    ColorSchemeKeyTokens.Tertiary -> tertiary
    ColorSchemeKeyTokens.TertiaryContainer -> tertiaryContainer
    ColorSchemeKeyTokens.Success -> success
    ColorSchemeKeyTokens.SuccessContainer -> successContainer
    ColorSchemeKeyTokens.OnSuccess -> onSuccess
    ColorSchemeKeyTokens.OnSuccessContainer -> onSuccessContainer
    ColorSchemeKeyTokens.Info -> info
    ColorSchemeKeyTokens.InfoContainer -> infoContainer
    ColorSchemeKeyTokens.OnInfo -> onInfo
    ColorSchemeKeyTokens.OnInfoContainer -> onInfoContainer
    ColorSchemeKeyTokens.Warning -> warning
    ColorSchemeKeyTokens.WarningContainer -> warningContainer
    ColorSchemeKeyTokens.OnWarning -> onWarning
    ColorSchemeKeyTokens.OnWarningContainer -> onWarningContainer
    ColorSchemeKeyTokens.PrimaryLight -> primaryLight
    ColorSchemeKeyTokens.PrimaryDark -> primaryDark
    ColorSchemeKeyTokens.SecondaryLight -> secondaryLight
    ColorSchemeKeyTokens.SecondaryDark -> secondaryDark
    ColorSchemeKeyTokens.TertiaryLight -> tertiaryLight
    ColorSchemeKeyTokens.TertiaryDark -> tertiaryDark
    ColorSchemeKeyTokens.OutlineLight -> outlineLight
    ColorSchemeKeyTokens.OutlineDark -> outlineDark
    ColorSchemeKeyTokens.Shadow -> shadow
    ColorSchemeKeyTokens.Text -> text
    ColorSchemeKeyTokens.TextSecondary -> textSecondary
    else -> Color.Unspecified
}

internal val LocalColorScheme = staticCompositionLocalOf { lightColorScheme() }
val LocalTonalElevationEnabled = staticCompositionLocalOf { true }
internal const val DisabledAlpha = 0.38f

internal val ColorSchemeKeyTokens.value: Color
    @Composable
    @ReadOnlyComposable
    get() = TailwindTheme.colorScheme.fromToken(this)