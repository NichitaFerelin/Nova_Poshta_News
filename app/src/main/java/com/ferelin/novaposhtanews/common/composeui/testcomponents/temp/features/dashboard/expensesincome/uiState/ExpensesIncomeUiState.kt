package com.endava.budgetplanner.features.dashboard.expensesincome.uiState

import androidx.compose.runtime.Immutable
import com.endava.budgetplanner.common.composeui.components.piechart.PieChartItem
import com.endava.budgetplanner.domain.LoadingContentState

@Immutable
data class ExpensesIncomeUiState(
    val loadingContentState: LoadingContentState = LoadingContentState.None,
    val pieChartItems: List<PieChartItem> = emptyList(),
    val pieChartDate: String = "",
    val pieChartPreviousDateExist: Boolean = false,
    val pieChartNextDateExist: Boolean = false,
    val pieChartLeftBudget: String = "",
    val pieChartSpentBudget: String = ""
)
