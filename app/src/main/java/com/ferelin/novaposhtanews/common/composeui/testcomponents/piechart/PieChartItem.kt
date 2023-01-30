package com.endava.budgetplanner.common.composeui.components.piechart

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class PieChartItem(
    val percentsValue: Float,
    val color: Color
)
