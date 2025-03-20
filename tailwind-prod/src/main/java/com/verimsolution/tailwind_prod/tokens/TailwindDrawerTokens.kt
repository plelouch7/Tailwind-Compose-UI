package com.verimsolution.tailwind_prod.tokens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Tokens pour le TailwindDrawer, inspirés de Tailwind CSS/Flowbite.
 * Ces tokens définissent les styles par défaut pour les couleurs, tailles, formes, etc.
 */
object TailwindDrawerTokens {
    // Couleurs pour les icônes dans différents états
    val ActiveFocusIconColor = Color(0xFF4B5563) // text-gray-600
    val ActiveHoverIconColor = Color(0xFF374151) // text-gray-700
    val ActiveIconColor = Color(0xFF6B7280) // text-gray-500
    val ActivePressedIconColor = Color(0xFF1F2937) // text-gray-800
    val InactiveFocusIconColor = Color(0xFF9CA3AF) // text-gray-400
    val InactiveHoverIconColor = Color(0xFF6B7280) // text-gray-500
    val InactiveIconColor = Color(0xFFD1D5DB) // text-gray-300
    val InactivePressedIconColor = Color(0xFF4B5563) // text-gray-600

    // Couleurs pour les textes des étiquettes dans différents états
    val ActiveFocusLabelTextColor = Color(0xFF4B5563) // text-gray-600
    val ActiveHoverLabelTextColor = Color(0xFF374151) // text-gray-700
    val ActiveLabelTextColor = Color(0xFF6B7280) // text-gray-500
    val ActivePressedLabelTextColor = Color(0xFF1F2937) // text-gray-800
    val InactiveFocusLabelTextColor = Color(0xFF9CA3AF) // text-gray-400
    val InactiveHoverLabelTextColor = Color(0xFF6B7280) // text-gray-500
    val InactiveLabelTextColor = Color(0xFFD1D5DB) // text-gray-300
    val InactivePressedLabelTextColor = Color(0xFF4B5563) // text-gray-600
    val CloseButtonColor = Color(0xFF9CA3AF) // text-gray-400

    // Couleur et taille de l'indicateur actif (similaire au "highlight" de Flowbite)
    val ActiveIndicatorColor = Color(0xFFE5E7EB) // bg-gray-200
    val ActiveIndicatorHeight: Dp = 56.dp // h-14
    val ActiveIndicatorWidth: Dp = 336.dp // w-84 (ajusté pour un tiroir large)
    val ActiveIndicatorShape = RoundedCornerShape(9999.dp) // rounded-full

    // Couleur et élévation du conteneur
    val ContainerColor = Color(0xFFFFFFFF) // bg-white
    val ContainerElevation: Dp = 4.dp // shadow-md
    val ContainerWidth: Dp = 300.dp // w-72 (équivalent Tailwind ajusté)
    val ContainerShape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp) // rounded-xl
    val ContainerHeightPercent = 100f // h-full
    val ContainerHeight: Dp = 200.dp // Ajout pour Top/Bottom

    // Couleur du scrim (overlay)
    val ScrimColor = Color(0x80000000) // bg-black/50

    // Taille des icônes
    val IconSize: Dp = 24.dp // w-6 h-6

    // Couleur et taille du badge (optionnel pour des notifications ou indicateurs)
    val BadgeLabelColor = Color(0xFF6B7280) // text-gray-500
    val BadgeSize: Dp = 16.dp // w-4 h-4

    // Couleur du titre (headline)
    val HeadlineColor = Color(0xFF1F2937) // text-gray-800
}