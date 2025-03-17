package com.verimsolution.tailwind

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
import com.verimsolution.tailwind.tokens.ColorDarkTokens
import com.verimsolution.tailwind.tokens.ColorLightTokens
import com.verimsolution.tailwind.tokens.ColorSchemeKeyTokens
import kotlin.math.ln

@Immutable
class ColorScheme(
    // Couleurs primaires basées sur Tailwind
    val primary: Color,           // blue-500
    val onPrimary: Color,
    val primaryContainer: Color,    // blue-200
    val onPrimaryContainer: Color,  // blue-800
    val inversePrimary: Color,      // blue-300
    // Couleurs secondaires basées sur Tailwind
    val secondary: Color,           // green-500
    val onSecondary: Color,
    val secondaryContainer: Color,   // green-200
    val onSecondaryContainer: Color, // green-800
    // Couleurs tertiaires (choisies ici comme une nuance de violet)
    val tertiary: Color,            // purple-500
    val onTertiary: Color,
    val tertiaryContainer: Color,     // purple-200
    val onTertiaryContainer: Color,   // purple-800
    // Fond et surface
    val background: Color,          // blanc
    val onBackground: Color,        // gray-800
    val surface: Color,             // gray-50
    val onSurface: Color,           // gray-800
    val surfaceVariant: Color,      // gray-200
    val onSurfaceVariant: Color,    // gray-600
    val surfaceTint: Color,         // identique à primary
    // Inversions pour le mode sombre ou les mises en relief
    val inverseSurface: Color,      // gray-800
    val inverseOnSurface: Color,    // gray-50
    // États d'erreur
    val error: Color,               // red-500
    val onError: Color,
    val errorContainer: Color,      // red-200
    val onErrorContainer: Color,    // red-700
    // Couleurs d'encadrement
    val outline: Color,             // gray-400
    val outlineVariant: Color,      // gray-300
    // Couleur de scrim (utile pour les overlays, semi-transparent)
    val scrim: Color,
    // Nuances pour les surfaces complexes
    val surfaceBright: Color,       // blanc
    val surfaceDim: Color,          // gray-500
    val surfaceContainer: Color,      // gray-100
    val surfaceContainerHigh: Color,  // gray-200
    val surfaceContainerHighest: Color, // gray-300
    val surfaceContainerLow: Color,   // gray-50
    val surfaceContainerLowest: Color  // blanc
) {

    /** Retourne une copie de ce ColorScheme, en pouvant éventuellement remplacer certaines valeurs. */
    fun copy(
        primary: Color = this.primary,
        onPrimary: Color = this.onPrimary,
        primaryContainer: Color = this.primaryContainer,
        onPrimaryContainer: Color = this.onPrimaryContainer,
        inversePrimary: Color = this.inversePrimary,
        secondary: Color = this.secondary,
        onSecondary: Color = this.onSecondary,
        secondaryContainer: Color = this.secondaryContainer,
        onSecondaryContainer: Color = this.onSecondaryContainer,
        tertiary: Color = this.tertiary,
        onTertiary: Color = this.onTertiary,
        tertiaryContainer: Color = this.tertiaryContainer,
        onTertiaryContainer: Color = this.onTertiaryContainer,
        background: Color = this.background,
        onBackground: Color = this.onBackground,
        surface: Color = this.surface,
        onSurface: Color = this.onSurface,
        surfaceVariant: Color = this.surfaceVariant,
        onSurfaceVariant: Color = this.onSurfaceVariant,
        surfaceTint: Color = this.surfaceTint,
        inverseSurface: Color = this.inverseSurface,
        inverseOnSurface: Color = this.inverseOnSurface,
        error: Color = this.error,
        onError: Color = this.onError,
        errorContainer: Color = this.errorContainer,
        onErrorContainer: Color = this.onErrorContainer,
        outline: Color = this.outline,
        outlineVariant: Color = this.outlineVariant,
        scrim: Color = this.scrim,
        surfaceBright: Color = this.surfaceBright,
        surfaceDim: Color = this.surfaceDim,
        surfaceContainer: Color = this.surfaceContainer,
        surfaceContainerHigh: Color = this.surfaceContainerHigh,
        surfaceContainerHighest: Color = this.surfaceContainerHighest,
        surfaceContainerLow: Color = this.surfaceContainerLow,
        surfaceContainerLowest: Color = this.surfaceContainerLowest,
    ): ColorScheme =
        ColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            primaryContainer = primaryContainer,
            onPrimaryContainer = onPrimaryContainer,
            inversePrimary = inversePrimary,
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondaryContainer,
            onSecondaryContainer = onSecondaryContainer,
            tertiary = tertiary,
            onTertiary = onTertiary,
            tertiaryContainer = tertiaryContainer,
            onTertiaryContainer = onTertiaryContainer,
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = onSurfaceVariant,
            surfaceTint = surfaceTint,
            inverseSurface = inverseSurface,
            inverseOnSurface = inverseOnSurface,
            error = error,
            onError = onError,
            errorContainer = errorContainer,
            onErrorContainer = onErrorContainer,
            outline = outline,
            outlineVariant = outlineVariant,
            scrim = scrim,
            surfaceBright = surfaceBright,
            surfaceDim = surfaceDim,
            surfaceContainer = surfaceContainer,
            surfaceContainerHigh = surfaceContainerHigh,
            surfaceContainerHighest = surfaceContainerHighest,
            surfaceContainerLow = surfaceContainerLow,
            surfaceContainerLowest = surfaceContainerLowest,
        )

    override fun toString(): String {
        return "ColorScheme(" +
                "primary=$primary, " +
                "onPrimary=$onPrimary, " +
                "primaryContainer=$primaryContainer, " +
                "onPrimaryContainer=$onPrimaryContainer, " +
                "inversePrimary=$inversePrimary, " +
                "secondary=$secondary, " +
                "onSecondary=$onSecondary, " +
                "secondaryContainer=$secondaryContainer, " +
                "onSecondaryContainer=$onSecondaryContainer, " +
                "tertiary=$tertiary, " +
                "onTertiary=$onTertiary, " +
                "tertiaryContainer=$tertiaryContainer, " +
                "onTertiaryContainer=$onTertiaryContainer, " +
                "background=$background, " +
                "onBackground=$onBackground, " +
                "surface=$surface, " +
                "onSurface=$onSurface, " +
                "surfaceVariant=$surfaceVariant, " +
                "onSurfaceVariant=$onSurfaceVariant, " +
                "surfaceTint=$surfaceTint, " +
                "inverseSurface=$inverseSurface, " +
                "inverseOnSurface=$inverseOnSurface, " +
                "error=$error, " +
                "onError=$onError, " +
                "errorContainer=$errorContainer, " +
                "onErrorContainer=$onErrorContainer, " +
                "outline=$outline, " +
                "outlineVariant=$outlineVariant, " +
                "scrim=$scrim, " +
                "surfaceBright=$surfaceBright, " +
                "surfaceDim=$surfaceDim, " +
                "surfaceContainer=$surfaceContainer, " +
                "surfaceContainerHigh=$surfaceContainerHigh, " +
                "surfaceContainerHighest=$surfaceContainerHighest, " +
                "surfaceContainerLow=$surfaceContainerLow, " +
                "surfaceContainerLowest=$surfaceContainerLowest" +
                ")"
    }

//    internal var defaultButtonColorsCached: ButtonColors? = null
//    internal var defaultElevatedButtonColorsCached: ButtonColors? = null
//    internal var defaultFilledTonalButtonColorsCached: ButtonColors? = null
//    internal var defaultOutlinedButtonColorsCached: ButtonColors? = null
//    internal var defaultTextButtonColorsCached: ButtonColors? = null
//
//    internal var defaultCardColorsCached: CardColors? = null
//    internal var defaultElevatedCardColorsCached: CardColors? = null
//    internal var defaultOutlinedCardColorsCached: CardColors? = null
//
//    internal var defaultAssistChipColorsCached: ChipColors? = null
//    internal var defaultElevatedAssistChipColorsCached: ChipColors? = null
//    internal var defaultSuggestionChipColorsCached: ChipColors? = null
//    internal var defaultElevatedSuggestionChipColorsCached: ChipColors? = null
//    internal var defaultFilterChipColorsCached: SelectableChipColors? = null
//    internal var defaultElevatedFilterChipColorsCached: SelectableChipColors? = null
//    internal var defaultInputChipColorsCached: SelectableChipColors? = null
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    internal var defaultTopAppBarColorsCached: TopAppBarColors? = null
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    internal var defaultCenterAlignedTopAppBarColorsCached: TopAppBarColors? = null
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    internal var defaultMediumTopAppBarColorsCached: TopAppBarColors? = null
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    internal var defaultLargeTopAppBarColorsCached: TopAppBarColors? = null
//
//    internal var defaultCheckboxColorsCached: CheckboxColors? = null
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    internal var defaultDatePickerColorsCached: DatePickerColors? = null
//
//    internal var defaultIconButtonColorsCached: IconButtonColors? = null
//    internal var defaultIconToggleButtonColorsCached: IconToggleButtonColors? = null
//    internal var defaultFilledIconButtonColorsCached: IconButtonColors? = null
//    internal var defaultFilledIconToggleButtonColorsCached: IconToggleButtonColors? = null
//    internal var defaultFilledTonalIconButtonColorsCached: IconButtonColors? = null
//    internal var defaultFilledTonalIconToggleButtonColorsCached: IconToggleButtonColors? = null
//    internal var defaultOutlinedIconButtonColorsCached: IconButtonColors? = null
//    internal var defaultOutlinedIconToggleButtonColorsCached: IconToggleButtonColors? = null
//
//    internal var defaultMenuItemColorsCached: MenuItemColors? = null
//
//    internal var defaultNavigationBarItemColorsCached: NavigationBarItemColors? = null
//
//    internal var defaultNavigationRailItemColorsCached: NavigationRailItemColors? = null
//
//    internal var defaultExpressiveNavigationBarItemColorsCached: NavigationItemColors? = null
//
//    internal var defaultRadioButtonColorsCached: RadioButtonColors? = null
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    internal var defaultSegmentedButtonColorsCached: SegmentedButtonColors? = null
//
//    internal var defaultSliderColorsCached: SliderColors? = null
//
//    internal var defaultSwitchColorsCached: SwitchColors? = null
//
//    internal var defaultOutlinedTextFieldColorsCached: TextFieldColors? = null
//    internal var defaultTextFieldColorsCached: TextFieldColors? = null
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    internal var defaultTimePickerColorsCached: TimePickerColors? = null
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    internal var defaultRichTooltipColorsCached: RichTooltipColors? = null
}

fun lightColorScheme(
    primary: Color = ColorLightTokens.Primary,
    onPrimary: Color = ColorLightTokens.OnPrimary,
    primaryContainer: Color = ColorLightTokens.PrimaryContainer,
    onPrimaryContainer: Color = ColorLightTokens.OnPrimaryContainer,
    inversePrimary: Color = ColorLightTokens.InversePrimary,
    secondary: Color = ColorLightTokens.Secondary,
    onSecondary: Color = ColorLightTokens.OnSecondary,
    secondaryContainer: Color = ColorLightTokens.SecondaryContainer,
    onSecondaryContainer: Color = ColorLightTokens.OnSecondaryContainer,
    tertiary: Color = ColorLightTokens.Tertiary,
    onTertiary: Color = ColorLightTokens.OnTertiary,
    tertiaryContainer: Color = ColorLightTokens.TertiaryContainer,
    onTertiaryContainer: Color = ColorLightTokens.OnTertiaryContainer,
    background: Color = ColorLightTokens.Background,
    onBackground: Color = ColorLightTokens.OnBackground,
    surface: Color = ColorLightTokens.Surface,
    onSurface: Color = ColorLightTokens.OnSurface,
    surfaceVariant: Color = ColorLightTokens.SurfaceVariant,
    onSurfaceVariant: Color = ColorLightTokens.OnSurfaceVariant,
    surfaceTint: Color = primary,
    inverseSurface: Color = ColorLightTokens.InverseSurface,
    inverseOnSurface: Color = ColorLightTokens.InverseOnSurface,
    error: Color = ColorLightTokens.Error,
    onError: Color = ColorLightTokens.OnError,
    errorContainer: Color = ColorLightTokens.ErrorContainer,
    onErrorContainer: Color = ColorLightTokens.OnErrorContainer,
    outline: Color = ColorLightTokens.Outline,
    outlineVariant: Color = ColorLightTokens.OutlineVariant,
    scrim: Color = ColorLightTokens.Scrim,
    surfaceBright: Color = ColorLightTokens.SurfaceBright,
    surfaceContainer: Color = ColorLightTokens.SurfaceContainer,
    surfaceContainerHigh: Color = ColorLightTokens.SurfaceContainerHigh,
    surfaceContainerHighest: Color = ColorLightTokens.SurfaceContainerHighest,
    surfaceContainerLow: Color = ColorLightTokens.SurfaceContainerLow,
    surfaceContainerLowest: Color = ColorLightTokens.SurfaceContainerLowest,
    surfaceDim: Color = ColorLightTokens.SurfaceDim,
): ColorScheme =
    ColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        inversePrimary = inversePrimary,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        surfaceTint = surfaceTint,
        inverseSurface = inverseSurface,
        inverseOnSurface = inverseOnSurface,
        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        outline = outline,
        outlineVariant = outlineVariant,
        scrim = scrim,
        surfaceBright = surfaceBright,
        surfaceContainer = surfaceContainer,
        surfaceContainerHigh = surfaceContainerHigh,
        surfaceContainerHighest = surfaceContainerHighest,
        surfaceContainerLow = surfaceContainerLow,
        surfaceContainerLowest = surfaceContainerLowest,
        surfaceDim = surfaceDim,
    )

fun darkColorScheme(
    primary: Color = ColorDarkTokens.Primary,
    onPrimary: Color = ColorDarkTokens.OnPrimary,
    primaryContainer: Color = ColorDarkTokens.PrimaryContainer,
    onPrimaryContainer: Color = ColorDarkTokens.OnPrimaryContainer,
    inversePrimary: Color = ColorDarkTokens.InversePrimary,
    secondary: Color = ColorDarkTokens.Secondary,
    onSecondary: Color = ColorDarkTokens.OnSecondary,
    secondaryContainer: Color = ColorDarkTokens.SecondaryContainer,
    onSecondaryContainer: Color = ColorDarkTokens.OnSecondaryContainer,
    tertiary: Color = ColorDarkTokens.Tertiary,
    onTertiary: Color = ColorDarkTokens.OnTertiary,
    tertiaryContainer: Color = ColorDarkTokens.TertiaryContainer,
    onTertiaryContainer: Color = ColorDarkTokens.OnTertiaryContainer,
    background: Color = ColorDarkTokens.Background,
    onBackground: Color = ColorDarkTokens.OnBackground,
    surface: Color = ColorDarkTokens.Surface,
    onSurface: Color = ColorDarkTokens.OnSurface,
    surfaceVariant: Color = ColorDarkTokens.SurfaceVariant,
    onSurfaceVariant: Color = ColorDarkTokens.OnSurfaceVariant,
    surfaceTint: Color = primary,
    inverseSurface: Color = ColorDarkTokens.InverseSurface,
    inverseOnSurface: Color = ColorDarkTokens.InverseOnSurface,
    error: Color = ColorDarkTokens.Error,
    onError: Color = ColorDarkTokens.OnError,
    errorContainer: Color = ColorDarkTokens.ErrorContainer,
    onErrorContainer: Color = ColorDarkTokens.OnErrorContainer,
    outline: Color = ColorDarkTokens.Outline,
    outlineVariant: Color = ColorDarkTokens.OutlineVariant,
    scrim: Color = ColorDarkTokens.Scrim,
    surfaceBright: Color = ColorDarkTokens.SurfaceBright,
    surfaceContainer: Color = ColorDarkTokens.SurfaceContainer,
    surfaceContainerHigh: Color = ColorDarkTokens.SurfaceContainerHigh,
    surfaceContainerHighest: Color = ColorDarkTokens.SurfaceContainerHighest,
    surfaceContainerLow: Color = ColorDarkTokens.SurfaceContainerLow,
    surfaceContainerLowest: Color = ColorDarkTokens.SurfaceContainerLowest,
    surfaceDim: Color = ColorDarkTokens.SurfaceDim,
): ColorScheme =
    ColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        inversePrimary = inversePrimary,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        surfaceTint = surfaceTint,
        inverseSurface = inverseSurface,
        inverseOnSurface = inverseOnSurface,
        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        outline = outline,
        outlineVariant = outlineVariant,
        scrim = scrim,
        surfaceBright = surfaceBright,
        surfaceContainer = surfaceContainer,
        surfaceContainerHigh = surfaceContainerHigh,
        surfaceContainerHighest = surfaceContainerHighest,
        surfaceContainerLow = surfaceContainerLow,
        surfaceContainerLowest = surfaceContainerLowest,
        surfaceDim = surfaceDim,
    )


@Stable
fun ColorScheme.contentColorFor(backgroundColor: Color): Color =
    when (backgroundColor) {
        primary -> onPrimary
        secondary -> onSecondary
        tertiary -> onTertiary
        background -> onBackground
        error -> onError
        primaryContainer -> onPrimaryContainer
        secondaryContainer -> onSecondaryContainer
        tertiaryContainer -> onTertiaryContainer
        errorContainer -> onErrorContainer
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

/**
 * Returns the content color corresponding to the provided background color.
 *
 * If the background color is not found in the ColorScheme, the current [LocalContentColor] is returned.
 */
@Composable
@ReadOnlyComposable
fun contentColorFor(backgroundColor: Color): Color =
    TailwindTheme.colorScheme.contentColorFor(backgroundColor).takeOrElse {
        LocalContentColor.current
    }

/**
 * Returns [ColorScheme.surfaceColorAtElevation] with the provided elevation if
 * [LocalTonalElevationEnabled] is set to true and if the provided background color matches
 * [ColorScheme.surface]. Otherwise, the provided background color is returned unchanged.
 *
 * @param backgroundColor The background color to compare to [ColorScheme.surface].
 * @param elevation The elevation to apply if tonal elevation is enabled.
 */
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

/**
 * Computes the surface tonal color at a given elevation.
 *
 * The algorithm computes an alpha value based on the elevation and applies it on top of [surfaceTint]
 * composited over [surface].
 *
 * @param elevation Elevation value used to compute the alpha of the overlay.
 * @return The resulting color after applying the tonal elevation.
 */
@Stable
fun ColorScheme.surfaceColorAtElevation(elevation: Dp): Color {
    if (elevation == 0.dp) return surface
    val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
    return surfaceTint.copy(alpha = alpha).compositeOver(surface)
}

/**
 * Returns a light Material color scheme.
 *
 * This function utilise les valeurs définies dans [ColorLightTokens] pour construire
 * le schéma de couleurs light.
 */
internal fun expressiveLightColorScheme() =
    lightColorScheme(
        // Vous pouvez personnaliser ici certains tokens si besoin
        onPrimaryContainer = ColorLightTokens.PrimaryContainer,
        onSecondaryContainer = ColorLightTokens.SecondaryContainer,
        onTertiaryContainer = ColorLightTokens.TertiaryContainer,
    )

/**
 * Helper function for component color tokens.
 *
 * Returns the color corresponding to the given [ColorSchemeKeyTokens] in the current ColorScheme.
 */
@Stable
internal fun ColorScheme.fromToken(value: ColorSchemeKeyTokens): Color {
    return when (value) {
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
        ColorSchemeKeyTokens.SurfaceTint -> surfaceTint
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
        ColorSchemeKeyTokens.Tertiary -> tertiary
        ColorSchemeKeyTokens.TertiaryContainer -> tertiaryContainer
        else -> Color.Unspecified
    }
}

/**
 * CompositionLocal used to pass [ColorScheme] down the tree.
 *
 * Typically, this value is set as part of your MaterialTheme or TailwindTheme.
 */
internal val LocalColorScheme = staticCompositionLocalOf { lightColorScheme() }

/**
 * CompositionLocal to control whether tonal elevation is applied.
 *
 * Setting this value to false will disable the application of tonal elevation.
 */
val LocalTonalElevationEnabled = staticCompositionLocalOf { true }

/**
 * A low level alpha value used to represent disabled components.
 */
internal const val DisabledAlpha = 0.38f

/**
 * Extension property to retrieve the color associated with a [ColorSchemeKeyTokens] from the current theme.
 */
internal val ColorSchemeKeyTokens.value: Color @Composable @ReadOnlyComposable get() = TailwindTheme.colorScheme.fromToken(this)
