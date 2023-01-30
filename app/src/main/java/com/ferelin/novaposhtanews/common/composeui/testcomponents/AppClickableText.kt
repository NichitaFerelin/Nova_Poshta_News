package com.endava.budgetplanner.common.composeui.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle

@Composable
fun AppClickableText(
    modifier: Modifier = Modifier,
    defaultText: String,
    defaultTextStyle: TextStyle,
    defaultTextColor: Color,
    clickableText: String,
    clickableTextStyle: TextStyle,
    clickableTextColor: Color,
    commonTextStyle: TextStyle = TextStyle.Default,
    softWrap: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onClick: () -> Unit,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    val annotatedString = remember(defaultTextColor, clickableTextColor) {
        val defaultSpanStyle = defaultTextStyle.toSpanStyle()
            .copy(color = defaultTextColor)
        val clickableSpanStyle = clickableTextStyle.toSpanStyle()
            .copy(color = clickableTextColor)

        buildAnnotatedString {
            withStyle(style = defaultSpanStyle) { append(defaultText) }
            withStyle(style = clickableSpanStyle) { append(clickableText) }
        }
    }

    ClickableText(
        modifier = modifier,
        style = commonTextStyle,
        text = annotatedString,
        softWrap = softWrap,
        overflow = overflow,
        maxLines = maxLines,
        onClick = { onClick() },
        onTextLayout = onTextLayout
    )
}
