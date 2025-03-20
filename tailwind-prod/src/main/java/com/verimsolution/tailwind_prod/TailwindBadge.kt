package com.verimsolution.tailwind_prod

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Tokens par défaut pour les badges inspirés de Flowbite/Tailwind CSS.
 */
object TailwindBadgeDefaults {
    val backgroundColor = Color(0xFFE5E7EB) // bg-gray-200
    val textColor = Color(0xFF1F2937) // text-gray-800
    val borderColor = Color(0xFFD1D5DB) // border-gray-300
    val shape = RoundedCornerShape(4.dp) // rounded
    val pillShape = RoundedCornerShape(50) // rounded-full pour pills
    val padding = PaddingValues(horizontal = 8.dp, vertical = 4.dp) // px-2 py-1
    val largePadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp) // px-3 py-1.5
    val iconSize = 16.dp
    val notificationSize = 20.dp
}

/**
 * Variantes de couleur pour les badges, inspirées de Tailwind CSS/Flowbite.
 */
enum class BadgeVariant {
    Default,    // Gris clair
    Primary,    // Bleu
    Secondary,  // Gris foncé
    Success,    // Vert
    Warning,    // Jaune
    Danger,     // Rouge
    Info,       // Cyan
    Dark,       // Noir
    Light       // Blanc
}

/**
 * Types de badges inspirés de Flowbite.
 */
enum class BadgeType {
    Default,        // Badge standard
    Large,          // Badge large
    Bordered,       // Badge avec bordure
    Pill,           // Badge en forme de pilule
    Link,           // Badge cliquable comme lien
    WithIcon,       // Badge avec icône et texte
    Notification,   // Badge de notification
    IconOnly,       // Badge avec icône uniquement
    Chip            // Badge supprimable (chip)
}

/**
 * Badge inspiré de Flowbite/Tailwind CSS avec personnalisation des couleurs et tailles.
 *
 * @param text Texte à afficher dans le badge (optionnel).
 * @param modifier Modificateur appliqué au badge.
 * @param type Type de badge (Default, Large, Bordered, etc.).
 * @param variant Variante de couleur (Default, Primary, etc.).
 * @param backgroundColor Couleur de fond personnalisée (remplace la variante si spécifiée).
 * @param textColor Couleur du texte personnalisée (remplace la variante si spécifiée).
 * @param icon Icône optionnelle (pour WithIcon, IconOnly, Notification).
 * @param iconSize Taille de l’icône (par défaut TailwindBadgeDefaults.iconSize).
 * @param textSize Taille du texte (par défaut 12.sp, 14.sp pour Large si non spécifié).
 * @param onClick Callback pour les badges cliquables (Link, Chip).
 * @param onDismiss Callback pour supprimer un chip.
 */
@Composable
fun TailwindBadge(
    modifier: Modifier = Modifier,
    text: String? = null,
    type: BadgeType = BadgeType.Default,
    variant: BadgeVariant = BadgeVariant.Default,
    backgroundColor: Color? = null,  // Personnalisation optionnelle
    textColor: Color? = null,        // Personnalisation optionnelle
    icon: Painter? = null,
    iconSize: Dp = TailwindBadgeDefaults.iconSize,  // Taille personnalisable
    textSize: TextUnit? = null,                     // Taille personnalisable
    onDismiss: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    // Couleurs par défaut selon la variante
    val defaultBgColor = when (variant) {
        BadgeVariant.Default -> TailwindBadgeDefaults.backgroundColor
        BadgeVariant.Primary -> Color(0xFF3B82F6) // bg-blue-500
        BadgeVariant.Secondary -> Color(0xFF6B7280) // bg-gray-500
        BadgeVariant.Success -> Color(0xFF10B981) // bg-green-500
        BadgeVariant.Warning -> Color(0xFFF59E0B) // bg-yellow-500
        BadgeVariant.Danger -> Color(0xFFEF4444) // bg-red-500
        BadgeVariant.Info -> Color(0xFF06B6D4) // bg-cyan-500
        BadgeVariant.Dark -> Color(0xFF1F2937) // bg-gray-800
        BadgeVariant.Light -> Color(0xFFF3F4F6) // bg-gray-100
    }
    val defaultTextColor = when (variant) {
        BadgeVariant.Default -> TailwindBadgeDefaults.textColor
        BadgeVariant.Light -> Color(0xFF1F2937) // text-gray-800 pour fond clair
        else -> Color.White // text-white pour les autres variantes
    }

    // Utiliser les couleurs personnalisées si fournies, sinon celles de la variante
    val resolvedBgColor = backgroundColor ?: defaultBgColor
    val resolvedTextColor = textColor ?: defaultTextColor
    val resolvedShape = when (type) {
        BadgeType.Pill, BadgeType.Notification -> TailwindBadgeDefaults.pillShape
        else -> TailwindBadgeDefaults.shape
    }
    val padding = if (type == BadgeType.Large) TailwindBadgeDefaults.largePadding else TailwindBadgeDefaults.padding
    // Taille du texte par défaut si non spécifiée
    val resolvedTextSize = textSize ?: if (type == BadgeType.Large) 14.sp else 12.sp

    // Style de base du badge
    val baseModifier = when (type) {
        BadgeType.Bordered -> modifier
            .border(1.dp, TailwindBadgeDefaults.borderColor, resolvedShape)
            .padding(padding)
        BadgeType.Notification -> modifier
            .size(TailwindBadgeDefaults.notificationSize)
            .background(resolvedBgColor, CircleShape)
        else -> modifier
            .background(resolvedBgColor, resolvedShape)
            .padding(padding)
    }.let {
        if (onClick != null) it.clickable { onClick() } else it
    }

    Box(
        modifier = baseModifier,
        contentAlignment = Alignment.Center
    ) {
        when (type) {
            BadgeType.WithIcon -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    icon?.let {
                        TailwindIcon(
                            painter = it,
                            contentDescription = null,
                            modifier = Modifier.size(iconSize), // Taille personnalisée
                            tint = resolvedTextColor
                        )
                    }
                    text?.let {
                        TailwindText(
                            text = it,
                            style = TextStyle(
                                fontSize = resolvedTextSize, // Taille personnalisée
                                color = resolvedTextColor
                            )
                        )
                    }
                }
            }
            BadgeType.IconOnly -> {
                icon?.let {
                    TailwindIcon(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier.size(iconSize), // Taille personnalisée
                        tint = resolvedTextColor
                    )
                }
            }
            BadgeType.Chip -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    text?.let {
                        TailwindText(
                            text = it,
                            style = TextStyle(
                                fontSize = resolvedTextSize, // Taille personnalisée
                                color = resolvedTextColor
                            )
                        )
                    }
                    TailwindIcon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Dismiss",
                        modifier = Modifier
                            .size(iconSize) // Taille personnalisée
                            .clickable { onDismiss?.invoke() },
                        tint = resolvedTextColor
                    )
                }
            }
            else -> {
                text?.let {
                    TailwindText(
                        text = it,
                        style = TextStyle(
                            fontSize = resolvedTextSize, // Taille personnalisée
                            color = resolvedTextColor
                        )
                    )
                }
            }
        }
    }
}