package com.verimsolution.tailwind_prod

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

/**
 * Définit l'apparence des ripples Tailwind.
 */
interface TailwindRippleTheme {
    @Composable
    fun defaultColor(): Color

    @Composable
    fun rippleAlpha(): TailwindRippleAlpha

    companion object {
        /**
         * Couleur par défaut pour le ripple Tailwind.
         *
         * @param contentColor Couleur du contenu (texte ou icône).
         * @param lightTheme Si le thème est clair ou sombre.
         */
        fun defaultRippleColor(contentColor: Color, lightTheme: Boolean): Color {
            val contentLuminance = contentColor.luminance()
            return if (!lightTheme && contentLuminance < 0.5) {
                Color.White // Ripple blanc sur surface sombre
            } else {
                contentColor // Utilise la couleur du contenu
            }
        }

        /**
         * Opacité par défaut pour le ripple Tailwind.
         *
         * @param contentColor Couleur du contenu.
         * @param lightTheme Si le thème est clair ou sombre.
         */
        fun defaultRippleAlpha(contentColor: Color, lightTheme: Boolean): TailwindRippleAlpha {
            return when {
                lightTheme -> {
                    if (contentColor.luminance() > 0.5) {
                        LightThemeHighContrastRippleAlpha
                    } else {
                        LightThemeLowContrastRippleAlpha
                    }
                }

                else -> DarkThemeRippleAlpha
            }
        }
    }
}

/**
 * Représente les niveaux d'opacité pour différents états d'interaction dans Tailwind.
 */
@Immutable
data class TailwindRippleAlpha(
    val draggedAlpha: Float,
    val focusedAlpha: Float,
    val hoveredAlpha: Float,
    val pressedAlpha: Float
) {
    override fun toString(): String {
        return "TailwindRippleAlpha(draggedAlpha=$draggedAlpha, focusedAlpha=$focusedAlpha, " +
                "hoveredAlpha=$hoveredAlpha, pressedAlpha=$pressedAlpha)"
    }
}

// Opacités par défaut pour Tailwind, inspirées de Material
private val LightThemeHighContrastRippleAlpha = TailwindRippleAlpha(
    pressedAlpha = 0.24f,
    focusedAlpha = 0.24f,
    draggedAlpha = 0.16f,
    hoveredAlpha = 0.08f
)

private val LightThemeLowContrastRippleAlpha = TailwindRippleAlpha(
    pressedAlpha = 0.12f,
    focusedAlpha = 0.12f,
    draggedAlpha = 0.08f,
    hoveredAlpha = 0.04f
)

private val DarkThemeRippleAlpha = TailwindRippleAlpha(
    pressedAlpha = 0.10f,
    focusedAlpha = 0.12f,
    draggedAlpha = 0.08f,
    hoveredAlpha = 0.04f
)

internal val LocalTailwindRippleTheme: ProvidableCompositionLocal<TailwindRippleTheme> =
    staticCompositionLocalOf { DefaultTailwindRippleTheme }

// Thème Tailwind par défaut pour les ripples
@Immutable
internal object DefaultTailwindRippleTheme : TailwindRippleTheme {
    @Composable
    override fun defaultColor(): Color =
        TailwindRippleTheme.defaultRippleColor(Color.Black, lightTheme = true) // Par défaut clair


    @Composable
    override fun rippleAlpha(): TailwindRippleAlpha =
        TailwindRippleTheme.defaultRippleAlpha(Color.Black, lightTheme = true)

}