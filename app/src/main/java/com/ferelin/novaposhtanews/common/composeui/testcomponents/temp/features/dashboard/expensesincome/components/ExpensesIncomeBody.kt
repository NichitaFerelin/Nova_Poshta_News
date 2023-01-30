package com.endava.budgetplanner.features.dashboard.expensesincome.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.piechart.PieChartWidget
import com.endava.budgetplanner.features.dashboard.expensesincome.uiState.ExpensesIncomeTitles
import com.endava.budgetplanner.features.dashboard.expensesincome.uiState.ExpensesIncomeUiState

@Composable
fun ExpensesIncomeBody(
    modifier: Modifier = Modifier,
    uiState: ExpensesIncomeUiState,
    expensesIncomeTitles: ExpensesIncomeTitles,
    onPieChartNextDateClick: () -> Unit,
    onPieChartPreviousDateClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.dashboard_content_padding))
    ) {
        Spacer(
            modifier = Modifier.height(
                dimensionResource(id = R.dimen.dashboard_padding_from_toolbar)
            )
        )
        PieChartWidget(
            leftValue = uiState.pieChartSpentBudget,
            leftValueTitle = stringResource(id = expensesIncomeTitles.pieChartLeftTitle),
            rightValue = uiState.pieChartLeftBudget,
            rightValueTitle = stringResource(id = expensesIncomeTitles.pieChartRightTitle),
            date = uiState.pieChartDate,
            itemsStateKey = uiState.pieChartItems,
            itemsSize = uiState.pieChartItems.size,
            itemPercentsValue = { uiState.pieChartItems[it].percentsValue },
            itemsColor = { uiState.pieChartItems[it].color },
            previousDateExist = uiState.pieChartPreviousDateExist,
            nextDateExist = uiState.pieChartNextDateExist,
            onPreviousDateClick = onPieChartPreviousDateClick,
            onNextDateClick = onPieChartNextDateClick
        )
        Spacer(
            modifier = Modifier.height(
                dimensionResource(id = R.dimen.dashboard_padding_from_pie_chart)
            )
        )
    }
}
