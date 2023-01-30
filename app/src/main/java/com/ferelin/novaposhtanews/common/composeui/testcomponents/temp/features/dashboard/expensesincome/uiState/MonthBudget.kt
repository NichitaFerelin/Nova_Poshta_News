package com.endava.budgetplanner.features.dashboard.expensesincome.uiState

import com.endava.budgetplanner.common.composeui.components.piechart.PieChartItem

data class MonthBudget(
    val spent: String,
    val left: String,
    val chartItems: List<PieChartItem>
)
