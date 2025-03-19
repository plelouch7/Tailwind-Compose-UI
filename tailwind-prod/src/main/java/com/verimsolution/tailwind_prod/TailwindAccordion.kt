package com.verimsolution.tailwind_prod

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Tokens par défaut inspirés de Tailwind CSS et Flowbite.
 */
object TailwindDefaults {
    val headerBg = Color(0xFFF3F4F6) // bg-gray-100
    val headerText = Color(0xFF1F2937) // text-gray-800
    val contentBg = Color.White // bg-white
    val contentText = Color(0xFF4B5563) // text-gray-600
    val borderColor = Color(0xFFE5E7EB) // border-gray-200
    val padding = 16.dp // p-4
    val rounded = RoundedCornerShape(4.dp) // rounded-md
    val roundedLg = RoundedCornerShape(8.dp) // rounded-lg
    val roundedNone = RoundedCornerShape(0.dp) // rounded-none
    val textBase = TextStyle(fontSize = 16.sp) // text-base
    val iconSize = 20.dp // Taille d'icône standard

    // Couleurs pour variantes spécifiques
    val darkHeaderBg = Color(0xFF1F2937) // bg-gray-800
    val darkHeaderText = Color.White // text-white
    val darkContentBg = Color(0xFF374151) // bg-gray-700
    val darkContentText = Color(0xFFD1D5DB) // text-gray-300
}

/**
 * Modèle de données pour un item d'accordéon.
 */
data class TailwindAccordionItem(
    val title: String,
    val initiallyExpanded: Boolean = false,
    val content: @Composable () -> Unit
)

/**
 * Variantes Flowbite pour l'accordéon.
 */
enum class AccordionVariant {
    Default,    // Fond gris clair, bordures subtiles
    Flush,      // Sans bordures extérieures ni fond sur l’en-tête fermé
    Bordered,   // Bordures accentuées sur chaque item
    Dark,       // Style sombre (bg-gray-800)
    RoundedLg,  // Coins plus arrondis (rounded-lg)
    RoundedNone // Sans arrondis (rounded-none)
}

/**
 * Structure pour gérer les styles résolus.
 */
data class AccordionStyle(
    val headerBg: Color,
    val headerText: Color,
    val contentBg: Color,
    val contentText: Color,
    val shape: Shape
)

/**
 * Composable principal pour un accordéon Tailwind, inspiré de Flowbite avec toutes les variantes.
 */
@Composable
fun TailwindAccordion(
    items: List<TailwindAccordionItem>,
    allowMultiple: Boolean = false,
    variant: AccordionVariant = AccordionVariant.Default,
    modifier: Modifier = Modifier,
    headerBgColor: Color = TailwindDefaults.headerBg,
    headerTextColor: Color = TailwindDefaults.headerText,
    headerPadding: Dp = TailwindDefaults.padding,
    headerShape: Shape = TailwindDefaults.rounded,
    contentBgColor: Color = TailwindDefaults.contentBg,
    contentTextColor: Color = TailwindDefaults.contentText,
    contentPadding: Dp = TailwindDefaults.padding,
    borderColor: Color = TailwindDefaults.borderColor,
    borderWidth: Dp = 1.dp
) {
    // Appliquer les presets selon la variante

    // Résoudre les styles selon la variante
    val (resolvedHeaderBg, resolvedHeaderText, resolvedContentBg, resolvedContentText, resolvedShape) = when (variant) {
        AccordionVariant.Default -> AccordionStyle(
            headerBgColor, headerTextColor, contentBgColor, contentTextColor, headerShape
        )

        AccordionVariant.Flush -> AccordionStyle(
            headerBgColor, headerTextColor, contentBgColor, contentTextColor, headerShape
        )

        AccordionVariant.Bordered -> AccordionStyle(
            headerBgColor, headerTextColor, contentBgColor, contentTextColor, headerShape
        )

        AccordionVariant.Dark -> AccordionStyle(
            TailwindDefaults.darkHeaderBg, TailwindDefaults.darkHeaderText,
            TailwindDefaults.darkContentBg, TailwindDefaults.darkContentText, headerShape
        )

        AccordionVariant.RoundedLg -> AccordionStyle(
            headerBgColor,
            headerTextColor,
            contentBgColor,
            contentTextColor,
            TailwindDefaults.roundedLg
        )

        AccordionVariant.RoundedNone -> AccordionStyle(
            headerBgColor,
            headerTextColor,
            contentBgColor,
            contentTextColor,
            TailwindDefaults.roundedNone
        )
    }

    val baseModifier = when (variant) {
        AccordionVariant.Default -> modifier
            .background(resolvedContentBg, resolvedShape)
            .border(borderWidth, borderColor, resolvedShape)

        AccordionVariant.Flush -> modifier
        AccordionVariant.Bordered -> modifier
            .border(borderWidth, borderColor, resolvedShape)

        AccordionVariant.Dark -> modifier
            .background(resolvedContentBg, resolvedShape)
            .border(borderWidth, borderColor, resolvedShape)

        AccordionVariant.RoundedLg, AccordionVariant.RoundedNone -> modifier
            .background(resolvedContentBg, resolvedShape)
            .border(borderWidth, borderColor, resolvedShape)
    }

    Column(modifier = baseModifier.fillMaxWidth()) {
        if (allowMultiple) {
            val expandedStates = remember {
                mutableStateListOf<Boolean>().apply { addAll(items.map { it.initiallyExpanded }) }
            }
            items.forEachIndexed { index, item ->
                TailwindAccordionItemView(
                    item = item,
                    expanded = expandedStates[index],
                    onToggle = { expandedStates[index] = !expandedStates[index] },
                    variant = variant,
                    headerBgColor = resolvedHeaderBg,
                    headerTextColor = resolvedHeaderText,
                    headerPadding = headerPadding,
                    headerShape = resolvedShape,
                    contentBgColor = resolvedContentBg,
                    contentTextColor = resolvedContentText,
                    contentPadding = contentPadding,
                    borderColor = borderColor,
                    borderWidth = borderWidth
                )
            }
        } else {
            var expandedIndex by remember {
                mutableStateOf(items.indexOfFirst { it.initiallyExpanded }.takeIf { it >= 0 })
            }
            items.forEachIndexed { index, item ->
                TailwindAccordionItemView(
                    item = item,
                    expanded = expandedIndex == index,
                    onToggle = {
                        expandedIndex = if (expandedIndex == index) null else index
                    },
                    variant = variant,
                    headerBgColor = resolvedHeaderBg,
                    headerTextColor = resolvedHeaderText,
                    headerPadding = headerPadding,
                    headerShape = resolvedShape,
                    contentBgColor = resolvedContentBg,
                    contentTextColor = resolvedContentText,
                    contentPadding = contentPadding,
                    borderColor = borderColor,
                    borderWidth = borderWidth
                )
            }
        }
    }
}

/**
 * Composable pour un item individuel d'accordéon, avec animations et styles Flowbite.
 */
@Composable
fun TailwindAccordionItemView(
    item: TailwindAccordionItem,
    expanded: Boolean,
    onToggle: () -> Unit,
    variant: AccordionVariant,
    headerBgColor: Color,
    headerTextColor: Color,
    headerPadding: Dp,
    headerShape: Shape,
    contentBgColor: Color,
    contentTextColor: Color,
    contentPadding: Dp,
    borderColor: Color,
    borderWidth: Dp,
    modifier: Modifier = Modifier
) {
    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Column(modifier = modifier.fillMaxWidth()) {
        // En-tête
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (variant == AccordionVariant.Flush && !expanded) Color.Transparent else headerBgColor,
                    shape = headerShape
                )
                .then(
                    if (variant != AccordionVariant.Flush) Modifier.border(
                        borderWidth,
                        borderColor,
                        headerShape
                    )
                    else Modifier
                )
                .clickable { onToggle() }
                .padding(headerPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TailwindText(
                text = item.title,
                style = TailwindDefaults.textBase,
                color = headerTextColor,
                modifier = Modifier.weight(1f)
            )
            TailwindIcon(
                painter = painterResource(id = R.drawable.ic_chevron_down),
                contentDescription = if (expanded) "Réduire" else "Déployer",
                tint = headerTextColor,
                modifier = Modifier
                    .size(TailwindDefaults.iconSize)
                    .rotate(rotation)
            )
        }

        // Contenu
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = contentBgColor,
                        shape = if (variant != AccordionVariant.Flush) headerShape else TailwindDefaults.roundedNone
                    )
                    .then(
                        if (variant == AccordionVariant.Bordered) Modifier.border(
                            borderWidth,
                            borderColor,
                            headerShape
                        )
                        else Modifier
                    )
                    .padding(contentPadding)
            ) {
                CompositionLocalProvider(LocalContentColor provides contentTextColor) {
                    item.content()
                }
            }
        }
    }
}