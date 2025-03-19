package com.verimsolution.tailwind_prod

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

/**
 * Fournit un thème Tailwind CSS pur pour Jetpack Compose, inspiré de la structure Material 3.
 * @param colorScheme Schéma de couleurs basé sur Tailwind CSS (par défaut : clair).
 * @param shapes Formes basées sur Tailwind CSS (bordures arrondies).
 * @param typography Typographie inspirée de Tailwind CSS.
 * @param content Contenu à rendre dans le thème.
 */
@Composable
fun TailwindTheme(
    colorScheme: ColorScheme = TailwindTheme.colorScheme,
    shapes: Shapes = TailwindTheme.shapes,
    typography: Typography = TailwindTheme.typography,
    content: @Composable () -> Unit
) {
    // Source d'interaction générique pour les indications (sans Material ripple)
    val interactionSource = remember { MutableInteractionSource() }
    val rippleIndication = ripple(
        bounded = true, radius = Dp.Unspecified, color = colorScheme.primary
    )
    val selectionColors = rememberTextSelectionColors(colorScheme)

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalInteractionSource provides interactionSource,
        LocalIndication provides rippleIndication,
        LocalShapes provides shapes,
        LocalTextSelectionColors provides selectionColors,
        LocalTypography provides typography,
        LocalTailwindRippleTheme provides DefaultTailwindRippleTheme
    ) {
        // Applique le style de texte par défaut (bodyLarge) au contenu
        CompositionLocalProvider(
            LocalTextStyle provides typography.bodyLarge
        ) {
            content()
        }
    }
}

/**
 * Objet permettant d'accéder aux valeurs du thème Tailwind dans la hiérarchie Compose.
 */
object TailwindTheme {
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val interactionSource: MutableInteractionSource
        @Composable
        @ReadOnlyComposable
        get() = LocalInteractionSource.current
}

/**
 * Fournit un thème Tailwind expressif avec des couleurs plus vives.
 * @param colorScheme Schéma de couleurs (par défaut : expressif clair).
 * @param shapes Formes (par défaut : Tailwind Shapes).
 * @param typography Typographie (par défaut : Tailwind Typography).
 * @param content Contenu à rendre.
 */
@Composable
internal fun TailwindExpressiveTheme(
    colorScheme: ColorScheme? = null,
    shapes: Shapes? = null,
    typography: Typography? = null,
    content: @Composable () -> Unit
) {
    if (LocalUsingExpressiveTheme.current) {
        TailwindTheme(
            colorScheme = colorScheme ?: TailwindTheme.colorScheme,
            shapes = shapes ?: TailwindTheme.shapes,
            typography = typography ?: TailwindTheme.typography,
            content = content
        )
    } else {
        CompositionLocalProvider(LocalUsingExpressiveTheme provides true) {
            TailwindTheme(
                colorScheme = colorScheme ?: expressiveLightColorScheme(),
                shapes = shapes ?: Shapes(),
                typography = typography ?: Typography(),
                content = content
            )
        }
    }
}

/**
 * Calcule les couleurs de sélection de texte basées sur le schéma de couleurs Tailwind.
 */
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

// Constantes
internal const val TextSelectionBackgroundOpacity = 0.4f

// Locals pour le thème
internal val LocalUsingExpressiveTheme = staticCompositionLocalOf { false }