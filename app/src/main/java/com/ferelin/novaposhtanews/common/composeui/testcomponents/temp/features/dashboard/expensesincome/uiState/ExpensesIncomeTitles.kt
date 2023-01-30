package com.endava.budgetplanner.features.dashboard.expensesincome.uiState

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.endava.budgetplanner.R

@Suppress("DataClassPrivateConstructor")
@Immutable
data class ExpensesIncomeTitles private constructor(
    @StringRes val pieChartLeftTitle: Int,
    @StringRes val pieChartRightTitle: Int
) {
    companion object {
        fun incomeTitles(): ExpensesIncomeTitles = ExpensesIncomeTitles(
            pieChartLeftTitle = R.string.main_budget_earned_budget,
            pieChartRightTitle = R.string.main_budget_left_budget
        )

        fun expensesTitles(): ExpensesIncomeTitles = ExpensesIncomeTitles(
            pieChartLeftTitle = R.string.main_budget_spent_budget,
            pieChartRightTitle = R.string.main_budget_left_budget
        )
    }
}
