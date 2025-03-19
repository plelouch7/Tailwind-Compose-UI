package com.verimsolution.tailwind_prod

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Tokens par défaut inspirés de Tailwind CSS et Flowbite pour la barre de navigation inférieure.
 */
object TailwindBottomNavigationDefaults {
    val backgroundColor = Color(0xFFFFFFFF) // bg-white
    val borderColor = Color(0xFFE5E7EB) // border-gray-200
    val activeColor = Color(0xFF1D4ED8) // text-blue-700
    val inactiveColor = Color(0xFF6B7280) // text-gray-500
    val padding = 8.dp // p-2
    val iconSize = 24.dp // w-6 h-6
    val fontSize = 12.sp // text-xs
    val elevation = 4.dp // shadow-md
    val shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp) // Arrondi en haut uniquement
    val iconTextSpacing = 4.dp // Espacement par défaut entre icône et texte

    // Variantes Flowbite
    val darkBg = Color(0xFF1F2937) // bg-gray-800
    val darkBorder = Color(0xFF374151) // border-gray-700
    val darkActiveColor = Color(0xFF60A5FA) // text-blue-400
    val darkInactiveColor = Color(0xFF9CA3AF) // text-gray-400
}

/**
 * Variantes Flowbite pour la barre de navigation.
 */
enum class BottomNavigationVariant {
    Default,  // Style clair
    Dark      // Style sombre
}

/**
 * Élément de la barre de navigation.
 * @param label Texte optionnel (null si uniquement icône).
 * @param icon Icône optionnelle (null si uniquement texte).
 * @param onClick Action déclenchée au clic.
 * @param iconTextSpacing Espacement entre l’icône et le texte (si les deux sont présents).
 */
data class NavigationItem(
    val label: String? = null,
    val icon: @Composable ((tint: Color?) -> Unit)? = null, // Icône prend la couleur en paramètre, nullable
    val iconTextSpacing: Dp = TailwindBottomNavigationDefaults.iconTextSpacing,
    val onClick: () -> Unit
)

/**
 * Une barre de navigation inférieure en pur Tailwind CSS, inspirée de Flowbite, avec éléments prédéfinis.
 *
 * @param items Liste des éléments de navigation.
 * @param selectedIndex Index de l’élément actuellement sélectionné.
 * @param onItemSelected Callback appelé lorsqu’un élément est sélectionné.
 * @param variant Variante Flowbite (Default ou Dark).
 * @param modifier Modificateur appliqué à la barre.
 * @param backgroundColor Couleur de fond personnalisée.
 * @param borderColor Couleur de bordure personnalisée.
 * @param activeColor Couleur des éléments actifs personnalisée.
 * @param inactiveColor Couleur des éléments inactifs personnalisée.
 * @param shape Forme personnalisée.
 * @param padding Padding interne personnalisé.
 * @param elevation Élévation de l’ombre personnalisée.
 */
@Composable
fun TailwindBottomNavigationBar(
    items: List<NavigationItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    variant: BottomNavigationVariant = BottomNavigationVariant.Default,
    modifier: Modifier = Modifier,
    backgroundColor: Color = TailwindBottomNavigationDefaults.backgroundColor,
    borderColor: Color = TailwindBottomNavigationDefaults.borderColor,
    activeColor: Color = TailwindBottomNavigationDefaults.activeColor,
    inactiveColor: Color = TailwindBottomNavigationDefaults.inactiveColor,
    shape: Shape = TailwindBottomNavigationDefaults.shape,
    padding: Dp = TailwindBottomNavigationDefaults.padding,
    elevation: Dp = TailwindBottomNavigationDefaults.elevation
) {
    // Résoudre les styles selon la variante
    val resolvedBgColor = when (variant) {
        BottomNavigationVariant.Default -> backgroundColor
        BottomNavigationVariant.Dark -> TailwindBottomNavigationDefaults.darkBg
    }
    val resolvedBorderColor = when (variant) {
        BottomNavigationVariant.Default -> borderColor
        BottomNavigationVariant.Dark -> TailwindBottomNavigationDefaults.darkBorder
    }
    val resolvedActiveColor = when (variant) {
        BottomNavigationVariant.Default -> activeColor
        BottomNavigationVariant.Dark -> TailwindBottomNavigationDefaults.darkActiveColor
    }
    val resolvedInactiveColor = when (variant) {
        BottomNavigationVariant.Default -> inactiveColor
        BottomNavigationVariant.Dark -> TailwindBottomNavigationDefaults.darkInactiveColor
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation, shape)
            .background(resolvedBgColor, shape)
            .border(1.dp, resolvedBorderColor, shape)
            .padding(padding),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = index == selectedIndex
            val itemColor = if (isSelected) resolvedActiveColor else resolvedInactiveColor

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onItemSelected(index) }
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Icône (optionnelle)
                item.icon?.let {
                    Box(
                        modifier = Modifier
                            .size(TailwindBottomNavigationDefaults.iconSize)
                    ) {
                        it(itemColor) // Passe explicitement la couleur à l’icône
                    }
                }

                // Espacement entre icône et texte (si les deux sont présents)
                if (item.icon != null && item.label != null) {
                    Box(modifier = Modifier.padding(bottom = item.iconTextSpacing))
                }

                // Texte (optionnel)
                item.label?.let {
                    TailwindText(
                        text = it,
                        style = TextStyle(fontSize = TailwindBottomNavigationDefaults.fontSize),
                        color = itemColor
                    )
                }
            }
        }
    }
}

/**
 * Une barre de navigation inférieure en pur Tailwind CSS, inspirée de Flowbite, avec contenu totalement flexible.
 *
 * @param modifier Modificateur appliqué à la barre.
 * @param variant Variante Flowbite (Default ou Dark).
 * @param backgroundColor Couleur de fond personnalisée.
 * @param borderColor Couleur de bordure personnalisée.
 * @param activeColor Couleur des éléments actifs personnalisée (passée au contenu).
 * @param inactiveColor Couleur des éléments inactifs personnalisée (passée au contenu).
 * @param shape Forme personnalisée.
 * @param padding Padding interne personnalisé.
 * @param elevation Élévation de l’ombre personnalisée.
 * @param content Contenu personnalisé de la barre de navigation.
 */
@Composable
fun TailwindBottomNavigationBarFlexible(
    modifier: Modifier = Modifier,
    variant: BottomNavigationVariant = BottomNavigationVariant.Default,
    backgroundColor: Color = TailwindBottomNavigationDefaults.backgroundColor,
    borderColor: Color = TailwindBottomNavigationDefaults.borderColor,
    activeColor: Color = TailwindBottomNavigationDefaults.activeColor,
    inactiveColor: Color = TailwindBottomNavigationDefaults.inactiveColor,
    shape: Shape = TailwindBottomNavigationDefaults.shape,
    padding: Dp = TailwindBottomNavigationDefaults.padding,
    elevation: Dp = TailwindBottomNavigationDefaults.elevation,
    content: @Composable (activeColor: Color, inactiveColor: Color) -> Unit
) {
    // Résoudre les styles selon la variante
    val resolvedBgColor = when (variant) {
        BottomNavigationVariant.Default -> backgroundColor
        BottomNavigationVariant.Dark -> TailwindBottomNavigationDefaults.darkBg
    }
    val resolvedBorderColor = when (variant) {
        BottomNavigationVariant.Default -> borderColor
        BottomNavigationVariant.Dark -> TailwindBottomNavigationDefaults.darkBorder
    }
    val resolvedActiveColor = when (variant) {
        BottomNavigationVariant.Default -> activeColor
        BottomNavigationVariant.Dark -> TailwindBottomNavigationDefaults.darkActiveColor
    }
    val resolvedInactiveColor = when (variant) {
        BottomNavigationVariant.Default -> inactiveColor
        BottomNavigationVariant.Dark -> TailwindBottomNavigationDefaults.darkInactiveColor
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation, shape)
            .background(resolvedBgColor, shape)
            .border(1.dp, resolvedBorderColor, shape)
            .padding(padding)
    ) {
        content(resolvedActiveColor, resolvedInactiveColor)
    }
}