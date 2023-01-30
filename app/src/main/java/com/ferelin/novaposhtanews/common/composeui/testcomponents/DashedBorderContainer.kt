package com.endava.budgetplanner.common.composeui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

private const val PATH_INTERVAL_ON = 15f
private const val PATH_INTERVAL_OFF = 15f
private const val PATH_INTERVALS_OFFSET = 0f

@Composable
fun DashedBorderContainer(
    modifier: Modifier = Modifier,
    width: Dp,
    color: Color,
    cornerRadius: CornerRadius,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier) {
        val widthPx = LocalDensity.current.run { width.toPx() }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRoundRect(
                color = color,
                cornerRadius = cornerRadius,
                style = Stroke(
                    width = widthPx,
                    pathEffect = PathEffect.dashPathEffect(
                        floatArrayOf(PATH_INTERVAL_ON, PATH_INTERVAL_OFF),
                        PATH_INTERVALS_OFFSET
                    )
                )
            )
        }

        content()
    }
}
