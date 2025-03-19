package com.verimsolution.tailwind_prod

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Tokens par défaut inspirés de Tailwind CSS et Flowbite pour les cartes.
 */
object TailwindCardDefaults {
    val backgroundColor = Color(0xFFFFFFFF) // bg-white
    val borderColor = Color(0xFFE5E7EB) // border-gray-200
    val textColor = Color(0xFF1F2937) // text-gray-800
    val secondaryTextColor = Color(0xFF6B7280) // text-gray-500
    val padding = 16.dp // p-4
    val rounded = RoundedCornerShape(8.dp) // rounded-lg
    val shadowElevation = 4.dp // shadow-md (approximation)
    val shadowColor = Color(0xFF000000).copy(alpha = 0.1f) // shadow-gray-100

    // Variantes Flowbite
    val darkBg = Color(0xFF1F2937) // bg-gray-800
    val darkBorder = Color(0xFF374151) // border-gray-700
    val darkText = Color(0xFFD1D5DB) // text-gray-300
    val darkSecondaryText = Color(0xFF9CA3AF) // text-gray-400
}

/**
 * Variantes Flowbite pour les cartes.
 */
enum class CardVariant {
    Default,  // Carte blanche avec bordure légère
    Dark      // Carte sombre
}

/**
 * Une carte en pur Tailwind CSS, inspirée de Flowbite.
 *
 * @param modifier Modificateur appliqué au conteneur de la carte.
 * @param variant Variante Flowbite (Default ou Dark).
 * @param backgroundColor Couleur de fond personnalisée.
 * @param borderColor Couleur de bordure personnalisée.
 * @param textColor Couleur du texte principal personnalisée.
 * @param secondaryTextColor Couleur du texte secondaire personnalisée.
 * @param shape Forme personnalisée (arrondis).
 * @param padding Padding interne personnalisé.
 * @param elevation Élévation de l’ombre personnalisée.
 * @param shadowColor Couleur de l’ombre personnalisée.
 * @param header En-tête optionnel (ex. titre ou image).
 * @param content Contenu principal de la carte.
 * @param footer Pied de page optionnel (ex. boutons ou actions).
 */
@Composable
fun TailwindCard(
    modifier: Modifier = Modifier,
    variant: CardVariant = CardVariant.Default,
    backgroundColor: Color = TailwindCardDefaults.backgroundColor,
    borderColor: Color = TailwindCardDefaults.borderColor,
    textColor: Color = TailwindCardDefaults.textColor,
    secondaryTextColor: Color = TailwindCardDefaults.secondaryTextColor,
    shape: Shape = TailwindCardDefaults.rounded,
    padding: Dp = TailwindCardDefaults.padding,
    elevation: Dp = TailwindCardDefaults.shadowElevation,
    shadowColor: Color = TailwindCardDefaults.shadowColor,
    header: @Composable (() -> Unit)? = null,
    footer: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    // Résoudre les styles selon la variante
    val resolvedBgColor = when (variant) {
        CardVariant.Default -> backgroundColor
        CardVariant.Dark -> TailwindCardDefaults.darkBg
    }
    val resolvedBorderColor = when (variant) {
        CardVariant.Default -> borderColor
        CardVariant.Dark -> TailwindCardDefaults.darkBorder
    }
    val resolvedTextColor = when (variant) {
        CardVariant.Default -> textColor
        CardVariant.Dark -> TailwindCardDefaults.darkText
    }
    val resolvedSecondaryTextColor = when (variant) {
        CardVariant.Default -> secondaryTextColor
        CardVariant.Dark -> TailwindCardDefaults.darkSecondaryText
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation, shape, ambientColor = shadowColor, spotColor = shadowColor)
            .background(resolvedBgColor, shape)
            .border(1.dp, resolvedBorderColor, shape)
            .padding(padding)
    ) {
        // En-tête (optionnel)
        header?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = padding / 2)
            ) {
                CompositionLocalProvider(LocalContentColor provides resolvedTextColor) {
                    it()
                }
            }
        }

        // Contenu principal
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = false)
                .padding(bottom = padding / 2)
        ) {
            CompositionLocalProvider(LocalContentColor provides resolvedTextColor) {
                content()
            }
        }

        // Pied de page (optionnel)
        footer?.let {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = padding / 2),
                horizontalArrangement = Arrangement.End
            ) {
                CompositionLocalProvider(LocalContentColor provides resolvedSecondaryTextColor) {
                    it()
                }
            }
        }
    }
}