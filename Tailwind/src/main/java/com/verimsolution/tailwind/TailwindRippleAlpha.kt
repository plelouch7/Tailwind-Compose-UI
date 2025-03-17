package com.verimsolution.tailwind

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import com.verimsolution.tailwind.TailwindRippleTheme.Companion.defaultRippleAlpha
import com.verimsolution.tailwind.TailwindRippleTheme.Companion.defaultRippleColor
import com.verimsolution.tailwind.tokens.StateTokens

/**
 * Définit l'apparence des ripples pour le thème Tailwind.
 * Vous pouvez définir un nouveau thème et l'appliquer via [LocalTailwindRippleTheme].
 * Consultez [defaultRippleColor] et [defaultRippleAlpha] pour connaître les valeurs par défaut utilisées
 * lors de la création de votre propre [TailwindRippleTheme].
 *
 * @see rememberTailwindRipple
 */
interface TailwindRippleTheme {
    /**
     * @return la couleur par défaut du ripple à la position d'appel dans la hiérarchie.
     * Cette couleur sera utilisée lorsqu'aucune couleur n'est explicitement définie pour le ripple.
     * @see defaultRippleColor
     */
    @Composable
    fun defaultColor(): Color

    /**
     * @return le [TailwindRippleAlpha] utilisé pour calculer l'alpha du ripple en fonction de l'état
     * du composant. Cette valeur sera appliquée soit à la couleur par défaut, soit à la couleur
     * explicitement fournie au ripple.
     * @see defaultRippleAlpha
     */
    @Composable
    fun rippleAlpha(): TailwindRippleAlpha

    companion object {
        /**
         * Renvoie la couleur par défaut pour un ripple Tailwind si aucune couleur n'est explicitement
         * définie sur l'instance du ripple.
         *
         * @param contentColor la couleur du contenu (texte ou iconographie) du composant contenant le ripple.
         * @param lightTheme indique si le thème est clair ou non.
         */
        fun defaultRippleColor(contentColor: Color, lightTheme: Boolean): Color {
            val contentLuminance = contentColor.luminance()
            // Par exemple, sur un fond sombre et pour du contenu peu lumineux, on utilise du blanc.
            return if (!lightTheme && contentLuminance < 0.5f) {
                Color.White
            } else {
                contentColor
            }
        }

        /**
         * Renvoie le [TailwindRippleAlpha] par défaut qui sera utilisé pour indiquer les différents états.
         *
         * @param contentColor la couleur du contenu (texte ou iconographie) du composant contenant le ripple.
         * @param lightTheme indique si le thème est clair ou non.
         */
        fun defaultRippleAlpha(contentColor: Color, lightTheme: Boolean): TailwindRippleAlpha {
            return if (lightTheme) {
                if (contentColor.luminance() > 0.5f) {
                    // Alpha pour un contenu à haut contraste en thème clair
                    TailwindRippleAlpha(
                        pressedAlpha = 0.24f,
                        focusedAlpha = 0.24f,
                        draggedAlpha = 0.16f,
                        hoveredAlpha = 0.08f
                    )
                } else {
                    // Alpha pour un contenu à faible contraste en thème clair
                    TailwindRippleAlpha(
                        pressedAlpha = 0.12f,
                        focusedAlpha = 0.12f,
                        draggedAlpha = 0.08f,
                        hoveredAlpha = 0.04f
                    )
                }
            } else {
                // Valeurs pour un thème sombre
                TailwindRippleAlpha(
                    pressedAlpha = 0.10f,
                    focusedAlpha = 0.12f,
                    draggedAlpha = 0.08f,
                    hoveredAlpha = 0.04f
                )
            }
        }
    }
}

/**
 * Instance CompositionLocal pour fournir [TailwindRippleTheme] dans la hiérarchie.
 */
val LocalTailwindRippleTheme = staticCompositionLocalOf<TailwindRippleTheme> {
    DebugTailwindRippleTheme
}

/**
 * Implémentation de secours pour [TailwindRippleTheme].
 * Utilise par défaut une couleur noire et un thème clair.
 */
@Immutable
private object DebugTailwindRippleTheme : TailwindRippleTheme {
    @Composable
    override fun defaultColor(): Color =
        TailwindRippleTheme.defaultRippleColor(Color.Black, lightTheme = true)

    @Composable
    override fun rippleAlpha(): TailwindRippleAlpha =
        TailwindRippleTheme.defaultRippleAlpha(Color.Black, lightTheme = true)
}

/**
 * Détermine l'alpha du ripple pour les différents états d'interaction dans le thème Tailwind.
 *
 * @property draggedAlpha l'alpha utilisé lorsque le ripple est déplacé.
 * @property focusedAlpha l'alpha utilisé lorsque le ripple est focus.
 * @property hoveredAlpha l'alpha utilisé lorsque le ripple est survolé.
 * @property pressedAlpha l'alpha utilisé lorsque le ripple est pressé.
 */
@Immutable
class TailwindRippleAlpha(
    val draggedAlpha: Float = StateTokens.DraggedStateLayerOpacity,
    val focusedAlpha: Float = StateTokens.FocusStateLayerOpacity,
    val hoveredAlpha: Float = StateTokens.HoverStateLayerOpacity,
    val pressedAlpha: Float = StateTokens.PressedStateLayerOpacity
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TailwindRippleAlpha) return false

        if (draggedAlpha != other.draggedAlpha) return false
        if (focusedAlpha != other.focusedAlpha) return false
        if (hoveredAlpha != other.hoveredAlpha) return false
        if (pressedAlpha != other.pressedAlpha) return false

        return true
    }

    override fun hashCode(): Int {
        var result = draggedAlpha.hashCode()
        result = 31 * result + focusedAlpha.hashCode()
        result = 31 * result + hoveredAlpha.hashCode()
        result = 31 * result + pressedAlpha.hashCode()
        return result
    }

    override fun toString(): String {
        return "TailwindRippleAlpha(draggedAlpha=$draggedAlpha, focusedAlpha=$focusedAlpha, " +
                "hoveredAlpha=$hoveredAlpha, pressedAlpha=$pressedAlpha)"
    }
}
