package com.verimsolution.tailwind

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
 * Objet contenant des valeurs par défaut pour les couleurs si l'utilisateur ne les personnalise pas.
 */
//object TailwindDefaults {
//    val headerBackground: Color = Color(0xFFF3F4F6) // Une nuance claire, par exemple
//    val headerContent: Color = Color(0xFF1F2937)    // Une nuance foncée
//    val contentBackground: Color = Color.White
//    val contentContent: Color = Color(0xFF1F2937)    // Pareil que headerContent
//}
object TailwindDefaults {
    val headerBg = Color(0xFFF3F4F6) // bg-gray-100
    val headerText = Color(0xFF1F2937) // text-gray-800
    val contentBg = Color.White // bg-white
    val contentText = Color(0xFF1F2937) // text-gray-800
    val padding = 16.dp // p-4
    val rounded = RoundedCornerShape(4.dp) // rounded
    val textBase = TextStyle(fontSize = 16.sp) // text-base
}

/**
 * Modèle de données pour un item d'accordéon.
 */
data class TailwindAccordionItem(
    val title: String,
    val initiallyExpanded: Boolean = false,
    val content: @Composable ColumnScope.() -> Unit
)

//@Composable
//fun TailwindAccordion(
//    items: List<TailwindAccordionItem>,
//    allowMultiple: Boolean = false,
//    modifier: Modifier = Modifier,
//    headerShape: Shape = TailwindTheme.shapes.medium,
//    headerBackgroundColor: Color = TailwindDefaults.headerBackground,
//    headerContentColor: Color = TailwindDefaults.headerContent,
//    headerPadding: Dp = 20.dp,
//    contentBackgroundColor: Color = TailwindDefaults.contentBackground,
//    contentContentColor: Color = TailwindDefaults.contentContent,
//    contentPadding: Dp = 20.dp,
//) {
//    if (allowMultiple) {
//        // Plusieurs items ouverts simultanément.
//        val expandedStates =
//            remember { mutableStateListOf<Boolean>().apply { addAll(items.map { it.initiallyExpanded }) } }
//        Column(modifier = modifier.fillMaxWidth()) {
//            items.forEachIndexed { index, item ->
//                TailwindAccordionItemView(
//                    title = item.title,
//                    expanded = expandedStates[index],
//                    onToggle = { expandedStates[index] = !expandedStates[index] },
//                    headerShape = headerShape,
//                    headerBackgroundColor = headerBackgroundColor,
//                    headerContentColor = headerContentColor,
//                    headerPadding = headerPadding,
//                    contentBackgroundColor = contentBackgroundColor,
//                    contentContentColor = contentContentColor,
//                    contentPadding = contentPadding,
//                    content = item.content
//                )
//            }
//        }
//    } else {
//        // Un seul item ouvert à la fois.
//        var expandedIndex by remember {
//            mutableStateOf(items.indexOfFirst { it.initiallyExpanded }.takeIf { it >= 0 })
//        }
//        Column(modifier = modifier.fillMaxWidth()) {
//            items.forEachIndexed { index, item ->
//                TailwindAccordionItemView(
//                    title = item.title,
//                    expanded = expandedIndex == index,
//                    onToggle = {
//                        expandedIndex = if (expandedIndex == index) null else index
//                    },
//                    headerShape = headerShape,
//                    headerBackgroundColor = headerBackgroundColor,
//                    headerContentColor = headerContentColor,
//                    headerPadding = headerPadding,
//                    contentBackgroundColor = contentBackgroundColor,
//                    contentContentColor = contentContentColor,
//                    contentPadding = contentPadding,
//                    content = item.content
//                )
//            }
//        }
//    }
//}
@Composable
fun TailwindAccordion(
    items: List<TailwindAccordionItem>,
    allowMultiple: Boolean = false,
    modifier: Modifier = Modifier,
    headerBgColor: Color = TailwindDefaults.headerBg,
    headerTextColor: Color = TailwindDefaults.headerText,
    headerPadding: Dp = TailwindDefaults.padding,
    headerShape: Shape = TailwindDefaults.rounded,
    contentBgColor: Color = TailwindDefaults.contentBg,
    contentTextColor: Color = TailwindDefaults.contentText,
    contentPadding: Dp = TailwindDefaults.padding
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (allowMultiple) {
            // Mode multi-ouvertures
            val expandedStates = remember {
                mutableStateListOf<Boolean>().apply { addAll(items.map { it.initiallyExpanded }) }
            }
            items.forEachIndexed { index, item ->
                TailwindAccordionItemView(
                    item = item,
                    expanded = expandedStates[index],
                    onToggle = { expandedStates[index] = !expandedStates[index] },
                    headerBgColor = headerBgColor,
                    headerTextColor = headerTextColor,
                    headerPadding = headerPadding,
                    headerShape = headerShape,
                    contentBgColor = contentBgColor,
                    contentTextColor = contentTextColor,
                    contentPadding = contentPadding
                )
            }
        } else {
            // Mode une seule ouverture
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
                    headerBgColor = headerBgColor,
                    headerTextColor = headerTextColor,
                    headerPadding = headerPadding,
                    headerShape = headerShape,
                    contentBgColor = contentBgColor,
                    contentTextColor = contentTextColor,
                    contentPadding = contentPadding
                )
            }
        }
    }
}

/**
 * Composable représentant un item individuel d'accordéon.
 *
 * L'en-tête comporte un titre et une icône animée qui pivote pour indiquer l'état (déployé/replié).
 * Le contenu est animé lors de son apparition/disparition.
 */
//@Composable
//fun TailwindAccordionItemView(
//    title: String,
//    expanded: Boolean,
//    onToggle: () -> Unit,
//    headerShape: Shape,
//    headerBackgroundColor: Color,
//    headerContentColor: Color,
//    headerPadding: Dp,
//    contentBackgroundColor: Color,
//    contentContentColor: Color,
//    contentPadding: Dp,
//    content: @Composable ColumnScope.() -> Unit,
//    modifier: Modifier = Modifier
//) {
//    // Animation de la rotation de l'icône
//    val rotation by animateFloatAsState(targetValue = if (expanded) 0f else 180f)
//
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .animateContentSize() // Anime les changements de taille
//    ) {
//        // En-tête cliquable
//        Surface(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable { onToggle() },
//            shape = headerShape,
//            color = headerBackgroundColor,
//            contentColor = headerContentColor,
//            tonalElevation = 0.dp
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(headerPadding),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                TailwindText(
//                    text = title,
//                    style = TailwindTheme.typography.headlineSmall,
//                    color = headerContentColor,
//                    modifier = Modifier.weight(1f)
//                )
//                TailwindIcon(
//                    painter = painterResource(
//                        id = if (expanded) R.drawable.ic_chevron_up else R.drawable.ic_chevron_down
//                    ),
//                    contentDescription = if (expanded) "Collapse" else "Expand",
//                    tint = headerContentColor,
//                    modifier = Modifier
//                        .size(20.dp)
//                        .rotate(rotation)
//                )
//            }
//        }
//        // Contenu animé lors de l'apparition/disparition
//        AnimatedVisibility(
//            visible = expanded,
//            enter = expandVertically(),
//            exit = shrinkVertically()
//        ) {
//            Surface(
//                modifier = Modifier.fillMaxWidth(),
//                shape = headerShape, // Vous pouvez utiliser un autre shape pour le contenu
//                color = contentBackgroundColor,
//                contentColor = contentContentColor,
//                tonalElevation = 0.dp
//            ) {
//                // Fournir le contexte ColumnScope pour le contenu
//                Column(modifier = Modifier.padding(contentPadding), content = content)
//            }
//        }
//    }
//}
@Composable
fun TailwindAccordionItemView(
    item: TailwindAccordionItem,
    expanded: Boolean,
    onToggle: () -> Unit,
    headerBgColor: Color,
    headerTextColor: Color,
    headerPadding: Dp,
    headerShape: Shape,
    contentBgColor: Color,
    contentTextColor: Color,
    contentPadding: Dp,
    modifier: Modifier = Modifier
) {
    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Column(modifier = modifier.fillMaxWidth()) {
        // En-tête
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(headerBgColor, headerShape) // bg personnalisé + forme
                .clickable { onToggle() }
                .padding(headerPadding), // padding personnalisé
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
                contentDescription = if (expanded) "Collapse" else "Expand",
                tint = headerTextColor,
                modifier = Modifier
                    .size(20.dp)
                    .rotate(rotation)
            )
        }

        // Contenu
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(contentBgColor) // bg personnalisé
                    .padding(contentPadding) // padding personnalisé
            ) {
                CompositionLocalProvider(LocalContentColor provides contentTextColor) {
                    item.content
                }
            }
        }
    }
}
