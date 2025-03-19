package com.verimsolution.tailwind_prod

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Tokens par défaut inspirés de Tailwind CSS et Flowbite pour les boutons.
 */
object TailwindButtonDefaults {
    val paddingSmall = 8.dp // px-2.5 py-1.5
    val paddingMedium = 12.dp // px-4 py-2
    val paddingLarge = 16.dp // px-5 py-2.5
    val fontSizeSmall = 12.sp // text-xs
    val fontSizeMedium = 14.sp // text-sm
    val fontSizeLarge = 16.sp // text-base
    val rounded = RoundedCornerShape(4.dp) // rounded-md
    val roundedLg = RoundedCornerShape(8.dp) // rounded-lg
    val pillShape = CircleShape // rounded-full

    // Couleurs par défaut
    val defaultBg = Color(0xFF1D4ED8) // bg-blue-700
    val defaultText = Color(0xFFFFFFFF) // text-white
    val defaultHover = Color(0xFF1E40AF) // hover:bg-blue-800
    val defaultBorder = Color(0xFF1D4ED8) // border-blue-700

    // Variantes Flowbite
    val alternativeBg = Color(0xFFFFFFFF) // bg-white
    val alternativeText = Color(0xFF111827) // text-gray-900
    val alternativeBorder = Color(0xFFD1D5DB) // border-gray-200
    val alternativeHover = Color(0xFFF3F4F6) // hover:bg-gray-100

    val darkBg = Color(0xFF1F2937) // bg-gray-800
    val darkText = Color(0xFFFFFFFF) // text-white
    val darkHover = Color(0xFF111827) // hover:bg-gray-900
    val darkBorder = Color(0xFF1F2937) // border-gray-800

    val lightBg = Color(0xFFFFFFFF) // bg-white
    val lightText = Color(0xFF6B7280) // text-gray-500
    val lightBorder = Color(0xFFD1D5DB) // border-gray-300
    val lightHover = Color(0xFFF3F4F6) // hover:bg-gray-100

    val greenBg = Color(0xFF15803D) // bg-green-700
    val greenText = Color(0xFFFFFFFF) // text-white
    val greenHover = Color(0xFF166534) // hover:bg-green-800
    val greenBorder = Color(0xFF15803D) // border-green-700

    val redBg = Color(0xFFB91C1C) // bg-red-700
    val redText = Color(0xFFFFFFFF) // text-white
    val redHover = Color(0xFF991B1B) // hover:bg-red-800
    val redBorder = Color(0xFFB91C1C) // border-red-700

    val yellowBg = Color(0xFFD97706) // bg-yellow-700
    val yellowText = Color(0xFFFFFFFF) // text-white
    val yellowHover = Color(0xFFB45309) // hover:bg-yellow-800
    val yellowBorder = Color(0xFFD97706) // border-yellow-700

    val purpleBg = Color(0xFF6D28D9) // bg-purple-700
    val purpleText = Color(0xFFFFFFFF) // text-white
    val purpleHover = Color(0xFF5B21B6) // hover:bg-purple-800
    val purpleBorder = Color(0xFF6D28D9) // border-purple-700

    val disabledBg = Color(0xFFD1D5DB) // bg-gray-300
    val disabledText = Color(0xFF6B7280) // text-gray-500

    val textBg = Color.Transparent
    val textText = Color(0xFF1D4ED8) // text-blue-700
    val textHover = Color(0xFFEFF6FF) // hover:bg-blue-100
}

/**
 * Variantes Flowbite pour les boutons.
 */
enum class ButtonVariant {
    Default,      // Bleu par défaut
    Alternative,  // Bordure grise, fond blanc
    Dark,         // Gris foncé
    Light,        // Bordure grise claire
    Green,        // Vert
    Red,          // Rouge
    Yellow,       // Jaune
    Purple,       // Violet
    Text          // Bouton texte
}

/**
 * Tailles des boutons Flowbite.
 */
enum class ButtonSize {
    Small,   // text-xs, px-2.5 py-1.5
    Medium,  // text-sm, px-4 py-2
    Large    // text-base, px-5 py-2.5
}

/**
 * Structure pour gérer les styles résolus du bouton.
 */
data class ButtonStyle(
    val backgroundColor: Color,
    val textColor: Color,
    val borderColor: Color,
    val hoverColor: Color
)

/**
 * Composable pour un spinner de chargement simple.
 */
@Composable
fun Loader(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1000))
    )
    TailwindIcon(
        painter = painterResource(R.drawable.ic_loader_circle), // Icône de chargement (ajoutez-la dans res/drawable)
        contentDescription = "Chargement",
        modifier = modifier
            .size(16.dp)
            .rotate(rotation),
        tint = Color.White
    )
}

/**
 * Composable TailwindButton avec toutes les variantes Flowbite.
 *
 * @param text Texte affiché dans le bouton (optionnel pour Icon Button).
 * @param onClick Action déclenchée au clic.
 * @param variant Variante Flowbite (Default, Alternative, etc.).
 * @param size Taille du bouton (Small, Medium, Large).
 * @param isPill Si vrai, applique une forme arrondie complète (rounded-full).
 * @param isOutline Si vrai, applique un style bordure uniquement.
 * @param isDisabled Si vrai, désactive le bouton.
 * @param isLoading Si vrai, affiche un loader au lieu du texte/icône.
 * @param modifier Modificateur appliqué au bouton.
 * @param icon Icône optionnelle (pour Buttons with Icon ou Icon Buttons).
 * @param backgroundColor Couleur de fond personnalisée.
 * @param textColor Couleur de texte personnalisée.
 * @param borderColor Couleur de bordure personnalisée.
 * @param hoverColor Couleur au survol personnalisée.
 * @param shape Forme personnalisée.
 * @param padding Padding personnalisé.
 */
@Composable
fun TailwindButton(
    text: String? = null,
    onClick: () -> Unit,
    variant: ButtonVariant = ButtonVariant.Default,
    size: ButtonSize = ButtonSize.Medium,
    isPill: Boolean = false,
    isOutline: Boolean = false,
    isDisabled: Boolean = false,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
    backgroundColor: Color? = null,
    textColor: Color? = null,
    borderColor: Color? = null,
    hoverColor: Color? = null,
    shape: Shape? = null,
    padding: Dp? = null
) {
    // Résoudre les styles selon la variante
    val defaultStyle = when (variant) {
        ButtonVariant.Default -> ButtonStyle(
            TailwindButtonDefaults.defaultBg,
            TailwindButtonDefaults.defaultText,
            TailwindButtonDefaults.defaultBorder,
            TailwindButtonDefaults.defaultHover
        )

        ButtonVariant.Alternative -> ButtonStyle(
            TailwindButtonDefaults.alternativeBg,
            TailwindButtonDefaults.alternativeText,
            TailwindButtonDefaults.alternativeBorder,
            TailwindButtonDefaults.alternativeHover
        )

        ButtonVariant.Dark -> ButtonStyle(
            TailwindButtonDefaults.darkBg,
            TailwindButtonDefaults.darkText,
            TailwindButtonDefaults.darkBorder,
            TailwindButtonDefaults.darkHover
        )

        ButtonVariant.Light -> ButtonStyle(
            TailwindButtonDefaults.lightBg,
            TailwindButtonDefaults.lightText,
            TailwindButtonDefaults.lightBorder,
            TailwindButtonDefaults.lightHover
        )

        ButtonVariant.Green -> ButtonStyle(
            TailwindButtonDefaults.greenBg,
            TailwindButtonDefaults.greenText,
            TailwindButtonDefaults.greenBorder,
            TailwindButtonDefaults.greenHover
        )

        ButtonVariant.Red -> ButtonStyle(
            TailwindButtonDefaults.redBg,
            TailwindButtonDefaults.redText,
            TailwindButtonDefaults.redBorder,
            TailwindButtonDefaults.redHover
        )

        ButtonVariant.Yellow -> ButtonStyle(
            TailwindButtonDefaults.yellowBg,
            TailwindButtonDefaults.yellowText,
            TailwindButtonDefaults.yellowBorder,
            TailwindButtonDefaults.yellowHover
        )

        ButtonVariant.Purple -> ButtonStyle(
            TailwindButtonDefaults.purpleBg,
            TailwindButtonDefaults.purpleText,
            TailwindButtonDefaults.purpleBorder,
            TailwindButtonDefaults.purpleHover
        )

        ButtonVariant.Text -> ButtonStyle(
            TailwindButtonDefaults.textBg,
            TailwindButtonDefaults.textText,
            TailwindButtonDefaults.textBg,
            TailwindButtonDefaults.textHover
        )
    }

    val style = when {
        isDisabled -> ButtonStyle(
            TailwindButtonDefaults.disabledBg,
            TailwindButtonDefaults.disabledText,
            TailwindButtonDefaults.disabledBg,
            TailwindButtonDefaults.disabledBg
        )

        isLoading -> ButtonStyle(
            defaultStyle.backgroundColor,
            defaultStyle.textColor,
            defaultStyle.borderColor,
            defaultStyle.hoverColor
        )

        else -> ButtonStyle(
            backgroundColor ?: if (isOutline) Color.Transparent else defaultStyle.backgroundColor,
            textColor ?: defaultStyle.textColor,
            borderColor ?: defaultStyle.borderColor,
            hoverColor ?: defaultStyle.hoverColor
        )
    }

    val resolvedShape = when {
        shape != null -> shape
        isPill -> TailwindButtonDefaults.pillShape
        else -> TailwindButtonDefaults.roundedLg
    }

    val resolvedPadding = padding ?: when (size) {
        ButtonSize.Small -> TailwindButtonDefaults.paddingSmall
        ButtonSize.Medium -> TailwindButtonDefaults.paddingMedium
        ButtonSize.Large -> TailwindButtonDefaults.paddingLarge
    }

    val resolvedFontSize = when (size) {
        ButtonSize.Small -> TailwindButtonDefaults.fontSizeSmall
        ButtonSize.Medium -> TailwindButtonDefaults.fontSizeMedium
        ButtonSize.Large -> TailwindButtonDefaults.fontSizeLarge
    }

    Box(
        modifier = modifier
            .clip(resolvedShape)
            .background(if (isDisabled || isLoading) style.backgroundColor else style.backgroundColor)
            .then(
                if (isOutline || isDisabled || variant == ButtonVariant.Text) Modifier.border(
                    1.dp,
                    style.borderColor,
                    resolvedShape
                ) else Modifier
            )
            .clickable(enabled = !isDisabled && !isLoading, onClick = onClick)
            .padding(resolvedPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            when {
                isLoading -> Loader()
                icon != null && text != null -> { // Button with Icon
                    Box(modifier = Modifier.padding(end = 4.dp)) {
                        icon()
                    }
                    TailwindText(
                        text = text,
                        style = TextStyle(fontSize = resolvedFontSize),
                        color = style.textColor
                    )
                }

                icon != null && text == null -> { // Icon Button
                    icon()
                }

                text != null -> { // Text Button ou bouton standard
                    TailwindText(
                        text = text,
                        style = TextStyle(fontSize = resolvedFontSize),
                        color = style.textColor
                    )
                }
            }
        }
    }
}