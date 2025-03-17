package com.verimsolution.tailwind

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun TailwindTheme(
    colorScheme: ColorScheme = TailwindTheme.colorScheme,
    shapes: Shapes = TailwindTheme.shapes,
    typography: Typography = TailwindTheme.typography,
    content: @Composable () -> Unit
) {
    // On récupère l'indication ripple ou son fallback pour une rétrocompatibilité
    val rippleIndication = rippleOrFallbackImplementation()
    // On mémorise les couleurs de sélection basées sur le schéma de couleurs
    val selectionColors = rememberTextSelectionColors(colorScheme)

    @Suppress("DEPRECATION_ERROR")
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalIndication provides rippleIndication,
        // TODO: b/304985887 - remove after one stable release
        androidx.compose.material.ripple.LocalRippleTheme provides CompatRippleTheme,
        LocalShapes provides shapes,
        LocalTextSelectionColors provides selectionColors,
        LocalTypography provides typography,
    ) {
        ProvideTextStyle(value = typography.bodyLarge, content = content)
    }
}

/**
 * Objet permettant d'accéder aux valeurs du thème Tailwind dans la hiérarchie Compose.
 */
object TailwindTheme {

    /**
     * Récupère la configuration de couleurs Tailwind à l'endroit d'appel dans la hiérarchie.
     */
    val colorScheme: ColorScheme @Composable @ReadOnlyComposable get() = LocalColorScheme.current

    /**
     * Récupère la typographie configurée (inspirée de Tailwind) à l'endroit d'appel dans la hiérarchie.
     */
    val typography: Typography @Composable @ReadOnlyComposable get() = LocalTypography.current

    /**
     * Récupère les formes (bordures arrondies, etc.) configurées selon Tailwind à l'endroit d'appel dans la hiérarchie.
     */
    val shapes: Shapes @Composable @ReadOnlyComposable get() = LocalShapes.current
}


// Local indiquant si nous utilisons le thème expressive
internal val LocalUsingExpressiveTheme = staticCompositionLocalOf { false }

@Composable
internal fun TailwindExpressiveTheme(
    colorScheme: ColorScheme? = null,
    shapes: Shapes? = null,
    typography: Typography? = null,
    content: @Composable () -> Unit
) {
    if (LocalUsingExpressiveTheme.current) {
        // Si le thème expressive est déjà activé, on réutilise le MaterialTheme existant.
        TailwindTheme(
            colorScheme = colorScheme ?: TailwindTheme.colorScheme,
            typography = typography ?: TailwindTheme.typography,
            shapes = shapes ?: TailwindTheme.shapes,
            content = content
        )
    } else {
        // Sinon, on active le thème expressive en fournissant nos valeurs par défaut
        CompositionLocalProvider(LocalUsingExpressiveTheme provides true) {
            TailwindTheme(
                colorScheme = colorScheme ?: expressiveLightColorScheme(),
                // TODO: remplacer par des appels aux valeurs de forme expressive si disponible
                shapes = shapes ?: Shapes(),
                // TODO: remplacer par des appels aux valeurs de typographie expressive si disponible
                typography = typography ?: Typography(),
                content = content
            )
        }
    }
}

@Composable
@Stable
internal fun rememberTextSelectionColors(colorScheme: ColorScheme): TextSelectionColors {
    val primaryColor = colorScheme.primary
    return remember(primaryColor) {
        TextSelectionColors(
            handleColor = primaryColor,
            backgroundColor = primaryColor.copy(alpha = TextSelectionBackgroundOpacity)
        )
    }
}

internal const val TextSelectionBackgroundOpacity = 0.4f
