package com.verimsolution.tailwind_prod

import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.verimsolution.tailwind_prod.tokens.TypographyTokens

/**
 * CompositionLocal pour fournir le style de texte par défaut dans le thème Tailwind.
 */
val LocalTextStyle = compositionLocalOf(structuralEqualityPolicy()) { TypographyTokens.BodyLarge }

/**
 * Fournit un style de texte (TextStyle) à la hiérarchie de composition, en fusionnant avec le style courant.
 *
 * @param value Style de texte à appliquer.
 * @param content Contenu à rendre avec ce style.
 */
@Composable
fun TailwindProvideTextStyle(
    value: TextStyle,
    content: @Composable () -> Unit
) {
    val mergedStyle = LocalTextStyle.current.merge(value)
    CompositionLocalProvider(LocalTextStyle provides mergedStyle, content = content)
}

/**
 * Affiche un texte à partir d'un [AnnotatedString] en utilisant le thème Tailwind.
 *
 * @param text Texte annoté à afficher.
 * @param modifier Modificateur pour le composant.
 * @param color Couleur du texte (par défaut : non spécifiée, utilise le style courant).
 * @param fontSize Taille de la police (par défaut : non spécifiée).
 * @param fontStyle Style de la police (italique, normal).
 * @param fontWeight Poids de la police (gras, normal, etc.).
 * @param fontFamily Famille de polices.
 * @param letterSpacing Espacement des lettres.
 * @param textDecoration Décoration du texte (souligné, barré, etc.).
 * @param textAlign Alignement du texte.
 * @param lineHeight Hauteur de ligne.
 * @param overflow Gestion du débordement du texte.
 * @param softWrap Si true, le texte passe à la ligne automatiquement.
 * @param maxLines Nombre maximum de lignes.
 * @param minLines Nombre minimum de lignes.
 * @param inlineContent Contenu inline (ex. icônes dans le texte).
 * @param onTextLayout Callback pour le résultat de la mise en page.
 * @param style Style de texte à fusionner avec le style courant.
 */
@Composable
fun TailwindText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    inlineContent: Map<String, InlineTextContent> = emptyMap(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    BasicText(
        text = text,
        modifier = modifier,
        style = style.merge(
            color = color,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign ?: TextAlign.Unspecified,
            lineHeight = lineHeight
        ),
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        inlineContent = inlineContent,
        onTextLayout = onTextLayout
    )
}

/**
 * Affiche un texte à partir d'une [String] en utilisant le thème Tailwind.
 *
 * @param text Texte brut à afficher.
 * @param modifier Modificateur pour le composant.
 * @param color Couleur du texte (par défaut : non spécifiée, utilise le style courant).
 * @param fontSize Taille de la police (par défaut : non spécifiée).
 * @param fontStyle Style de la police (italique, normal).
 * @param fontWeight Poids de la police (gras, normal, etc.).
 * @param fontFamily Famille de polices.
 * @param letterSpacing Espacement des lettres.
 * @param textDecoration Décoration du texte (souligné, barré, etc.).
 * @param textAlign Alignement du texte.
 * @param lineHeight Hauteur de ligne.
 * @param overflow Gestion du débordement du texte.
 * @param softWrap Si true, le texte passe à la ligne automatiquement.
 * @param maxLines Nombre maximum de lignes.
 * @param minLines Nombre minimum de lignes.
 * @param onTextLayout Callback pour le résultat de la mise en page.
 * @param style Style de texte à fusionner avec le style courant.
 */
@Composable
fun TailwindText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    TailwindText(
        text = AnnotatedString(text),
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        inlineContent = emptyMap(),
        onTextLayout = onTextLayout,
        style = style.merge(
            color = color,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign ?: TextAlign.Unspecified,
            lineHeight = lineHeight
        )
    )
}