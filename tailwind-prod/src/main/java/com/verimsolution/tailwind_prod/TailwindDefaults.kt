package com.verimsolution.tailwind_prod

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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

///**
// * Tokens par défaut inspirés de Tailwind CSS et Flowbite.
// */
//object TailwindDefaults {
//    val headerBg = Color(0xFFF3F4F6) // bg-gray-100
//    val headerText = Color(0xFF1F2937) // text-gray-800
//    val contentBg = Color.White // bg-white
//    val contentText = Color(0xFF4B5563) // text-gray-600 (plus doux pour le contenu)
//    val borderColor = Color(0xFFE5E7EB) // border-gray-200
//    val padding = 16.dp // p-4
//    val rounded = RoundedCornerShape(4.dp) // rounded-md
//    val textBase = TextStyle(fontSize = 16.sp) // text-base
//    val iconSize = 20.dp // Taille d'icône standard
//}
//
///**
// * Modèle de données pour un item d'accordéon.
// */
//data class TailwindAccordionItem(
//    val title: String,
//    val initiallyExpanded: Boolean = false,
//    val content: @Composable () -> Unit
//)
//
///**
// * Variants possibles pour l'accordéon, inspirés de Flowbite.
// */
//enum class AccordionVariant {
//    Default, // Avec bordures et fond
//    Flush,   // Sans bordures extérieures, minimaliste
//    Bordered // Bordures accentuées
//}
//
///**
// * Composable principal pour un accordéon Tailwind, inspiré de Flowbite.
// *
// * @param items Liste des items de l'accordéon.
// * @param allowMultiple Permet d'ouvrir plusieurs items simultanément.
// * @param variant Style de l'accordéon (Default, Flush, Bordered).
// * @param modifier Modificateur global.
// * @param headerBgColor Couleur de fond de l'en-tête.
// * @param headerTextColor Couleur du texte de l'en-tête.
// * @param headerPadding Padding de l'en-tête.
// * @param headerShape Forme de l'en-tête.
// * @param contentBgColor Couleur de fond du contenu.
// * @param contentTextColor Couleur du texte du contenu.
// * @param contentPadding Padding du contenu.
// * @param borderColor Couleur des bordures (si applicable).
// * @param borderWidth Largeur des bordures (si applicable).
// */
//@Composable
//fun TailwindAccordion(
//    items: List<TailwindAccordionItem>,
//    allowMultiple: Boolean = false,
//    variant: AccordionVariant = AccordionVariant.Default,
//    modifier: Modifier = Modifier,
//    headerBgColor: Color = TailwindDefaults.headerBg,
//    headerTextColor: Color = TailwindDefaults.headerText,
//    headerPadding: Dp = TailwindDefaults.padding,
//    headerShape: Shape = TailwindDefaults.rounded,
//    contentBgColor: Color = TailwindDefaults.contentBg,
//    contentTextColor: Color = TailwindDefaults.contentText,
//    contentPadding: Dp = TailwindDefaults.padding,
//    borderColor: Color = TailwindDefaults.borderColor,
//    borderWidth: Dp = 1.dp
//) {
//    val baseModifier = when (variant) {
//        AccordionVariant.Default -> modifier
//            .background(contentBgColor, headerShape)
//            .border(borderWidth, borderColor, headerShape)
//
//        AccordionVariant.Flush -> modifier
//        AccordionVariant.Bordered -> modifier
//            .border(borderWidth, borderColor, headerShape)
//    }
//
//    Column(modifier = baseModifier.fillMaxWidth()) {
//        if (allowMultiple) {
//            val expandedStates = remember {
//                mutableStateListOf<Boolean>().apply { addAll(items.map { it.initiallyExpanded }) }
//            }
//            items.forEachIndexed { index, item ->
//                TailwindAccordionItemView(
//                    item = item,
//                    expanded = expandedStates[index],
//                    onToggle = { expandedStates[index] = !expandedStates[index] },
//                    variant = variant,
//                    headerBgColor = headerBgColor,
//                    headerTextColor = headerTextColor,
//                    headerPadding = headerPadding,
//                    headerShape = headerShape,
//                    contentBgColor = contentBgColor,
//                    contentTextColor = contentTextColor,
//                    contentPadding = contentPadding,
//                    borderColor = borderColor,
//                    borderWidth = borderWidth
//                )
//            }
//        } else {
//            var expandedIndex by remember {
//                mutableStateOf(items.indexOfFirst { it.initiallyExpanded }.takeIf { it >= 0 })
//            }
//            items.forEachIndexed { index, item ->
//                TailwindAccordionItemView(
//                    item = item,
//                    expanded = expandedIndex == index,
//                    onToggle = {
//                        expandedIndex = if (expandedIndex == index) null else index
//                    },
//                    variant = variant,
//                    headerBgColor = headerBgColor,
//                    headerTextColor = headerTextColor,
//                    headerPadding = headerPadding,
//                    headerShape = headerShape,
//                    contentBgColor = contentBgColor,
//                    contentTextColor = contentTextColor,
//                    contentPadding = contentPadding,
//                    borderColor = borderColor,
//                    borderWidth = borderWidth
//                )
//            }
//        }
//    }
//}
//
///**
// * Composable pour un item individuel d'accordéon, avec animations et styles Flowbite.
// */
//@Composable
//fun TailwindAccordionItemView(
//    item: TailwindAccordionItem,
//    expanded: Boolean,
//    onToggle: () -> Unit,
//    variant: AccordionVariant,
//    headerBgColor: Color,
//    headerTextColor: Color,
//    headerPadding: Dp,
//    headerShape: Shape,
//    contentBgColor: Color,
//    contentTextColor: Color,
//    contentPadding: Dp,
//    borderColor: Color,
//    borderWidth: Dp,
//    modifier: Modifier = Modifier
//) {
//    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)
//
//    Column(modifier = modifier.fillMaxWidth()) {
//        // En-tête
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(
//                    color = if (variant == AccordionVariant.Flush && !expanded) Color.Transparent else headerBgColor,
//                    shape = headerShape
//                )
//                .then(
//                    if (variant != AccordionVariant.Flush) Modifier.border(
//                        borderWidth,
//                        borderColor,
//                        headerShape
//                    )
//                    else Modifier
//                )
//                .clickable { onToggle() }
//                .padding(headerPadding),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            TailwindText(
//                text = item.title,
//                style = TailwindDefaults.textBase,
//                color = headerTextColor,
//                modifier = Modifier.weight(1f)
//            )
//            TailwindIcon(
//                painter = painterResource(id = R.drawable.ic_chevron_down),
//                contentDescription = if (expanded) "Réduire" else "Déployer",
//                tint = headerTextColor,
//                modifier = Modifier
//                    .size(TailwindDefaults.iconSize)
//                    .rotate(rotation)
//            )
//        }
//
//        // Contenu
//        AnimatedVisibility(
//            visible = expanded,
//            enter = expandVertically(),
//            exit = shrinkVertically()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(
//                        color = contentBgColor,
//                        shape = if (variant != AccordionVariant.Flush) headerShape else RoundedCornerShape(
//                            0.dp
//                        )
//                    )
//                    .then(
//                        if (variant == AccordionVariant.Bordered) Modifier.border(
//                            borderWidth,
//                            borderColor,
//                            headerShape
//                        )
//                        else Modifier
//                    )
//                    .padding(contentPadding)
//            ) {
//                CompositionLocalProvider(LocalContentColor provides contentTextColor) {
//                    item.content
//                }
//            }
//        }
//    }
//}