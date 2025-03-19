package com.verimsolution.tailwind_prod

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Tokens par défaut inspirés de Tailwind CSS et Flowbite pour les alertes.
 */
object TailwindAlertDefaults {
    val containerBg = Color(0xFFFFFFFF) // bg-white
    val borderColor = Color(0xFFE5E7EB) // border-gray-200
    val titleTextColor = Color(0xFF1F2937) // text-gray-800
    val messageTextColor = Color(0xFF4B5563) // text-gray-600
    val padding = 16.dp // p-4
    val rounded = RoundedCornerShape(8.dp) // rounded-lg

    // Variantes Flowbite
    val successBg = Color(0xFFD1FAE5) // bg-green-100
    val successBorder = Color(0xFF10B981) // border-green-500
    val successText = Color(0xFF065F46) // text-green-800

    val dangerBg = Color(0xFFFEE2E2) // bg-red-100
    val dangerBorder = Color(0xFFEF4444) // border-red-500
    val dangerText = Color(0xFF991B1B) // text-red-800

    val warningBg = Color(0xFFFFF3CD) // bg-yellow-100
    val warningBorder = Color(0xFFFBBF24) // border-yellow-500
    val warningText = Color(0xFF92400E) // text-yellow-800

    val infoBg = Color(0xFFDBEAFE) // bg-blue-100
    val infoBorder = Color(0xFF3B82F6) // border-blue-500
    val infoText = Color(0xFF1E40AF) // text-blue-800

    val darkBg = Color(0xFF1F2937) // bg-gray-800
    val darkBorder = Color(0xFF4B5563) // border-gray-600
    val darkText = Color(0xFFD1D5DB) // text-gray-300
}

/**
 * Variantes Flowbite pour les alertes.
 */
enum class AlertVariant {
    Default,  // Style par défaut (neutre)
    Success,  // Alerte de succès (vert)
    Danger,   // Alerte d’erreur (rouge)
    Warning,  // Alerte d’avertissement (jaune)
    Info,     // Alerte d’information (bleu)
    Dark      // Style sombre (gris foncé)
}

/**
 * Structure pour gérer les styles résolus de l’alerte.
 */
data class AlertStyle(
    val containerColor: Color,
    val borderColor: Color,
    val titleTextColor: Color,
    val messageTextColor: Color
)

/**
 * Une alerte intégrée en pur Tailwind CSS, inspirée de Flowbite.
 *
 * @param title Titre de l’alerte (optionnel).
 * @param message Message descriptif (optionnel).
 * @param icon Icône optionnelle à gauche du contenu.
 * @param actions Actions ou boutons à droite (optionnel).
 * @param variant Variante Flowbite (Default, Success, Danger, etc.).
 * @param modifier Modificateur appliqué au conteneur de l’alerte.
 * @param containerColor Couleur de fond du conteneur.
 * @param borderColor Couleur de la bordure.
 * @param titleTextColor Couleur du texte du titre.
 * @param messageTextColor Couleur du texte du message.
 * @param shape Forme du conteneur (arrondis).
 * @param padding Padding interne.
 */
@Composable
fun TailwindAlert(
    title: @Composable (() -> Unit)? = null,
    message: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    variant: AlertVariant = AlertVariant.Default,
    modifier: Modifier = Modifier,
    containerColor: Color = TailwindAlertDefaults.containerBg,
    borderColor: Color = TailwindAlertDefaults.borderColor,
    titleTextColor: Color = TailwindAlertDefaults.titleTextColor,
    messageTextColor: Color = TailwindAlertDefaults.messageTextColor,
    shape: Shape = TailwindAlertDefaults.rounded,
    padding: Dp = TailwindAlertDefaults.padding
) {
    // Résoudre les styles selon la variante
    val style = when (variant) {
        AlertVariant.Default -> AlertStyle(
            containerColor, borderColor, titleTextColor, messageTextColor
        )
        AlertVariant.Success -> AlertStyle(
            TailwindAlertDefaults.successBg, TailwindAlertDefaults.successBorder,
            TailwindAlertDefaults.successText, TailwindAlertDefaults.successText
        )
        AlertVariant.Danger -> AlertStyle(
            TailwindAlertDefaults.dangerBg, TailwindAlertDefaults.dangerBorder,
            TailwindAlertDefaults.dangerText, TailwindAlertDefaults.dangerText
        )
        AlertVariant.Warning -> AlertStyle(
            TailwindAlertDefaults.warningBg, TailwindAlertDefaults.warningBorder,
            TailwindAlertDefaults.warningText, TailwindAlertDefaults.warningText
        )
        AlertVariant.Info -> AlertStyle(
            TailwindAlertDefaults.infoBg, TailwindAlertDefaults.infoBorder,
            TailwindAlertDefaults.infoText, TailwindAlertDefaults.infoText
        )
        AlertVariant.Dark -> AlertStyle(
            TailwindAlertDefaults.darkBg, TailwindAlertDefaults.darkBorder,
            TailwindAlertDefaults.darkText, TailwindAlertDefaults.darkText
        )
    }

    val (resolvedContainerColor, resolvedBorderColor, resolvedTitleTextColor, resolvedMessageTextColor) = style

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(resolvedContainerColor, shape)
            .border(1.dp, resolvedBorderColor, shape)
            .padding(padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Icône et contenu texte
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f, fill = false)
        ) {
            // Icône (optionnelle)
            icon?.let {
                Box(
                    modifier = Modifier
                        .padding(end = padding / 2)
                        .size(24.dp)
                ) {
                    it()
                }
            }

            // Contenu texte (titre et message)
            Column {
                title?.let {
                    CompositionLocalProvider(LocalContentColor provides resolvedTitleTextColor) {
                        it()
                    }
                }
                message?.let {
                    CompositionLocalProvider(LocalContentColor provides resolvedMessageTextColor) {
                        it()
                    }
                }
            }
        }

        // Actions (optionnelles)
        actions?.let {
            Box(
                modifier = Modifier
                    .padding(start = padding / 2)
                    .align(Alignment.CenterVertically)
            ) {
                CompositionLocalProvider(LocalContentColor provides resolvedMessageTextColor) {
                    it()
                }
            }
        }
    }
}