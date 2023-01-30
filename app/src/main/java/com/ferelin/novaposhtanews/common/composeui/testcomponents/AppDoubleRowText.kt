package com.endava.budgetplanner.common.composeui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.endava.budgetplanner.common.composeui.components.animation.AnimatedContentBlink

@Composable
fun AppDoubleRowText(
    modifier: Modifier = Modifier,
    textTop: String,
    textTopColor: Color,
    textTopStyle: TextStyle,
    textBottom: String,
    textBottomColor: Color,
    textBottomStyle: TextStyle,
    spaceBetween: Dp = 0.dp,
    alignment: Alignment.Horizontal = Alignment.Start,
    textAlign: TextAlign = TextAlign.Start
) {
    Column(
        modifier = modifier,
        horizontalAlignment = alignment
    ) {
        OverflowedText(
            text = textTop,
            color = textTopColor,
            style = textTopStyle,
            textAlign = textAlign
        )
        Spacer(modifier = Modifier.height(spaceBetween))
        AnimatedContentBlink(targetState = textBottom) {
            OverflowedText(
                text = textBottom,
                color = textBottomColor,
                style = textBottomStyle,
                textAlign = textAlign
            )
        }
    }
}
