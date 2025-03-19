package com.verimsolution.tailwind_prod

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlin.math.max

/**
 * Tokens par défaut inspirés de Tailwind CSS/Flowbite pour les modals.
 */
object TailwindModalDefaults {
    val backgroundColor = Color(0xFFFFFFFF) // bg-white
    val borderColor = Color(0xFFE5E7EB) // border-gray-200
    val iconColor = Color(0xFF6B7280) // text-gray-500
    val titleColor = Color(0xFF1F2937) // text-gray-800
    val textColor = Color(0xFF6B7280) // text-gray-500
    val buttonColor = Color(0xFF1D4ED8) // text-blue-700
    val overlayColor = Color(0x00000000) // bg-black/50
    val shape = RoundedCornerShape(8.dp) // rounded-lg
    val elevation = 8.dp // shadow-lg
    val minWidth = 280.dp // Material 3 minimum width
    val maxWidth = 560.dp // Material 3 maximum width
    val popupMinWidth = 200.dp // Plus petit pour pop-up
    val popupMaxWidth = 320.dp // Plus petit pour pop-up
    val padding = PaddingValues(all = 24.dp) // Material 3 padding
    val formPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp) // Adapté pour formulaires
    val popupPadding = PaddingValues(all = 16.dp) // Plus compact pour pop-up
    val iconPadding = PaddingValues(bottom = 16.dp)
    val titlePadding = PaddingValues(bottom = 16.dp)
    val textPadding = PaddingValues(bottom = 24.dp)
    val buttonsMainAxisSpacing = 8.dp
    val buttonsCrossAxisSpacing = 12.dp

    // Variantes Flowbite
    val darkBg = Color(0xFF1F2937) // bg-gray-800
    val darkBorder = Color(0xFF374151) // border-gray-700
    val darkIconColor = Color(0xFF9CA3AF) // text-gray-400
    val darkTitleColor = Color(0xFFD1D5DB) // text-gray-300
    val darkTextColor = Color(0xFF9CA3AF) // text-gray-400
    val darkButtonColor = Color(0xFF60A5FA) // text-blue-400
}

/**
 * Variantes Flowbite pour les modals.
 */
enum class ModalVariant {
    Default,  // Style clair
    Dark      // Style sombre
}

/**
 * Types de modals inspirés de Flowbite.
 */
enum class ModalType {
    Default,      // Modal standard
    Static,       // Modal non fermable par clic extérieur
    Popup,        // Modal compact pour messages courts
    FormElement,  // Modal pour formulaires
    Placement     // Modal avec position personnalisée
}

/**
 * Positionnement pour le type Placement.
 */
enum class ModalPlacement {
    Top,
    Bottom,
    Left,
    Right,
    Center
}

/**
 * Une boîte de dialogue modale inspirée de Flowbite et Material 3.
 *
 * @param onDismissRequest Callback appelé lorsque le modal est fermé (si applicable).
 * @param confirmButton Bouton de confirmation (obligatoire sauf pour Popup).
 * @param dismissButton Bouton de dismissal (optionnel).
 * @param icon Icône optionnelle au-dessus du titre ou du texte.
 * @param title Titre optionnel spécifiant l’objectif du modal.
 * @param text Texte optionnel détaillant l’objectif du modal.
 * @param modalType Type de modal (Default, Static, Popup, FormElement, Placement).
 * @param placement Position pour le type Placement (Top, Bottom, Left, Right, Center).
 * @param variant Variante Flowbite (Default ou Dark).
 * @param modifier Modificateur appliqué au contenu du modal.
 * @param backgroundColor Couleur de fond personnalisée.
 * @param borderColor Couleur de bordure personnalisée.
 * @param iconColor Couleur de l’icône personnalisée.
 * @param titleColor Couleur du titre personnalisée.
 * @param textColor Couleur du texte personnalisée.
 * @param buttonColor Couleur des boutons personnalisée.
 * @param overlayColor Couleur de l’overlay personnalisée.
 * @param shape Forme personnalisée.
 * @param padding Padding interne personnalisé.
 * @param elevation Élévation de l’ombre personnalisée.
 * @param minWidth Largeur minimale personnalisée.
 * @param maxWidth Largeur maximale personnalisée.
 * @param properties Propriétés spécifiques à la plateforme.
 */
@Composable
fun TailwindModal(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit)? = null,
    dismissButton: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    modalType: ModalType = ModalType.Default,
    placement: ModalPlacement = ModalPlacement.Center,
    variant: ModalVariant = ModalVariant.Default,
    modifier: Modifier = Modifier,
    backgroundColor: Color = TailwindModalDefaults.backgroundColor,
    borderColor: Color = TailwindModalDefaults.borderColor,
    iconColor: Color = TailwindModalDefaults.iconColor,
    titleColor: Color = TailwindModalDefaults.titleColor,
    textColor: Color = TailwindModalDefaults.textColor,
    buttonColor: Color = TailwindModalDefaults.buttonColor,
    overlayColor: Color = TailwindModalDefaults.overlayColor,
    shape: Shape = TailwindModalDefaults.shape,
    padding: PaddingValues? = null,
    elevation: Dp = TailwindModalDefaults.elevation,
    minWidth: Dp? = null,
    maxWidth: Dp? = null,
    properties: DialogProperties = DialogProperties()
) {
    // Résoudre les styles selon la variante
    val resolvedBgColor = when (variant) {
        ModalVariant.Default -> backgroundColor
        ModalVariant.Dark -> TailwindModalDefaults.darkBg
    }
    val resolvedBorderColor = when (variant) {
        ModalVariant.Default -> borderColor
        ModalVariant.Dark -> TailwindModalDefaults.darkBorder
    }
    val resolvedIconColor = when (variant) {
        ModalVariant.Default -> iconColor
        ModalVariant.Dark -> TailwindModalDefaults.darkIconColor
    }
    val resolvedTitleColor = when (variant) {
        ModalVariant.Default -> titleColor
        ModalVariant.Dark -> TailwindModalDefaults.darkTitleColor
    }
    val resolvedTextColor = when (variant) {
        ModalVariant.Default -> textColor
        ModalVariant.Dark -> TailwindModalDefaults.darkTextColor
    }
    val resolvedButtonColor = when (variant) {
        ModalVariant.Default -> buttonColor
        ModalVariant.Dark -> TailwindModalDefaults.darkButtonColor
    }

    // Déterminer les propriétés selon le type
    val resolvedProperties = when (modalType) {
        ModalType.Static -> DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false)
        else -> properties
    }
    val resolvedPadding = padding ?: when (modalType) {
        ModalType.Popup -> TailwindModalDefaults.popupPadding
        ModalType.FormElement -> TailwindModalDefaults.formPadding
        else -> TailwindModalDefaults.padding
    }
    val resolvedMinWidth = minWidth ?: when (modalType) {
        ModalType.Popup -> TailwindModalDefaults.popupMinWidth
        else -> TailwindModalDefaults.minWidth
    }
    val resolvedMaxWidth = maxWidth ?: when (modalType) {
        ModalType.Popup -> TailwindModalDefaults.popupMaxWidth
        else -> TailwindModalDefaults.maxWidth
    }
    val resolvedAlignment = when (modalType) {
        ModalType.Placement -> when (placement) {
            ModalPlacement.Top -> Alignment.TopCenter
            ModalPlacement.Bottom -> Alignment.BottomCenter
            ModalPlacement.Left -> Alignment.CenterStart
            ModalPlacement.Right -> Alignment.CenterEnd
            ModalPlacement.Center -> Alignment.Center
        }
        else -> Alignment.Center
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = resolvedProperties
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(overlayColor),
            contentAlignment = resolvedAlignment
        ) {
            Column(
                modifier = modifier
                    .sizeIn(minWidth = resolvedMinWidth, maxWidth = resolvedMaxWidth)
                    .shadow(elevation, shape)
                    .background(resolvedBgColor, shape)
                    .border(1.dp, resolvedBorderColor, shape)
                    .padding(resolvedPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Icône (optionnelle)
                icon?.let {
                    CompositionLocalProvider(LocalContentColor provides resolvedIconColor) {
                        Box(
                            modifier = Modifier
                                .padding(TailwindModalDefaults.iconPadding)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            it()
                        }
                    }
                }

                // Titre (optionnel)
                title?.let {
                    CompositionLocalProvider(LocalContentColor provides resolvedTitleColor) {
                        Box(
                            modifier = Modifier
                                .padding(TailwindModalDefaults.titlePadding)
                                .align(if (icon == null) Alignment.Start else Alignment.CenterHorizontally)
                        ) {
                            it()
                        }
                    }
                }

                // Texte (optionnel)
                text?.let {
                    CompositionLocalProvider(LocalContentColor provides resolvedTextColor) {
                        Box(
                            modifier = Modifier
                                .weight(1f, fill = false)
                                .padding(TailwindModalDefaults.textPadding)
                                .align(Alignment.Start)
                        ) {
                            it()
                        }
                    }
                }

                // Boutons (sauf pour Popup si aucun bouton fourni)
                if (confirmButton != null || dismissButton != null) {
                    CompositionLocalProvider(LocalContentColor provides resolvedButtonColor) {
                        TailwindModalFlowRow(
                            mainAxisSpacing = TailwindModalDefaults.buttonsMainAxisSpacing,
                            crossAxisSpacing = TailwindModalDefaults.buttonsCrossAxisSpacing
                        ) {
                            dismissButton?.invoke()
                            confirmButton?.invoke()
                        }
                    }
                }
            }
        }
    }
}

/**
 * Disposition personnalisée pour les boutons, inspirée de Material 3 AlertDialogFlowRow.
 */
@Composable
fun TailwindModalFlowRow(
    mainAxisSpacing: Dp,
    crossAxisSpacing: Dp,
    content: @Composable () -> Unit
) {
    Layout(content) { measurables, constraints ->
        val sequences = mutableListOf<List<Placeable>>()
        val crossAxisSizes = mutableListOf<Int>()
        val crossAxisPositions = mutableListOf<Int>()

        var mainAxisSpace = 0
        var crossAxisSpace = 0

        val currentSequence = mutableListOf<Placeable>()
        var currentMainAxisSize = 0
        var currentCrossAxisSize = 0

        fun canAddToCurrentSequence(placeable: Placeable) =
            currentSequence.isEmpty() ||
                    currentMainAxisSize + mainAxisSpacing.roundToPx() + placeable.width <= constraints.maxWidth

        fun startNewSequence() {
            if (sequences.isNotEmpty()) {
                crossAxisSpace += crossAxisSpacing.roundToPx()
            }
            sequences.add(0, currentSequence.toList())
            crossAxisSizes += currentCrossAxisSize
            crossAxisPositions += crossAxisSpace

            crossAxisSpace += currentCrossAxisSize
            mainAxisSpace = max(mainAxisSpace, currentMainAxisSize)

            currentSequence.clear()
            currentMainAxisSize = 0
            currentCrossAxisSize = 0
        }

        measurables.forEach { measurable ->
            val placeable = measurable.measure(constraints)
            if (!canAddToCurrentSequence(placeable)) startNewSequence()
            if (currentSequence.isNotEmpty()) {
                currentMainAxisSize += mainAxisSpacing.roundToPx()
            }
            currentSequence.add(placeable)
            currentMainAxisSize += placeable.width
            currentCrossAxisSize = max(currentCrossAxisSize, placeable.height)
        }

        if (currentSequence.isNotEmpty()) startNewSequence()

        val mainAxisLayoutSize = max(mainAxisSpace, constraints.minWidth)
        val crossAxisLayoutSize = max(crossAxisSpace, constraints.minHeight)

        layout(mainAxisLayoutSize, crossAxisLayoutSize) {
            sequences.forEachIndexed { i, placeables ->
                val childrenMainAxisSizes = IntArray(placeables.size) { j ->
                    placeables[j].width + if (j < placeables.lastIndex) mainAxisSpacing.roundToPx() else 0
                }
                val arrangement = Arrangement.End
                val mainAxisPositions = IntArray(childrenMainAxisSizes.size) { 0 }
                with(arrangement) {
                    arrange(mainAxisLayoutSize, childrenMainAxisSizes, layoutDirection, mainAxisPositions)
                }
                placeables.forEachIndexed { j, placeable ->
                    placeable.place(x = mainAxisPositions[j], y = crossAxisPositions[i])
                }
            }
        }
    }
}