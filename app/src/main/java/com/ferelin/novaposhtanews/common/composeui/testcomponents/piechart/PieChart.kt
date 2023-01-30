package com.endava.budgetplanner.common.composeui.components.piechart

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.animation.AnimatedContentScale
import com.endava.budgetplanner.common.composeui.theme.AppTheme

private const val MAX_VALUE = 100f
private const val MAX_ANGLE = 360f
private const val DRAW_START_ANGLE = 140F
private const val DRAW_ANGLE_PER_VALUE = MAX_ANGLE / MAX_VALUE

@Composable
fun <S> PieChart(
    modifier: Modifier = Modifier,
    itemsStateKey: S,
    itemsSize: Int,
    itemPercentsValue: (Int) -> Float,
    itemsColor: (Int) -> Color
) {
    val chartBackground = AppTheme.colors.backgroundSecondary
    val paddingFromBackground = dimensionResource(id = R.dimen.chart_padding_from_background)
    val paddingFromBackgroundPx = LocalDensity.current.run { paddingFromBackground.toPx() }

    AnimatedContentScale(
        modifier = modifier,
        targetState = itemsStateKey
    ) {
        Canvas(modifier = Modifier) {
            var stepStartAngle = DRAW_START_ANGLE

            drawArc(
                color = chartBackground,
                startAngle = DRAW_START_ANGLE,
                sweepAngle = MAX_ANGLE,
                useCenter = true,
                style = Stroke(width = paddingFromBackgroundPx)
            )

            repeat(itemsSize) { itemIndex ->
                val angleToDraw = itemPercentsValue(itemIndex) * DRAW_ANGLE_PER_VALUE

                drawArc(
                    color = itemsColor(itemIndex),
                    startAngle = stepStartAngle,
                    sweepAngle = angleToDraw,
                    useCenter = true
                )

                stepStartAngle += angleToDraw
            }
        }
    }
}
