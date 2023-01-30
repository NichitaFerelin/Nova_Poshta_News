@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.endava.budgetplanner.features.dashboard.expensesincome

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.AppCircularProgressBar
import com.endava.budgetplanner.common.composeui.theme.AppTheme
import com.endava.budgetplanner.domain.DashboardTabType
import com.endava.budgetplanner.domain.LoadingContentState
import com.endava.budgetplanner.features.dashboard.expensesincome.components.ExpensesIncomeBody
import com.endava.budgetplanner.features.dashboard.expensesincome.uiState.ExpensesIncomeTitles
import com.endava.budgetplanner.features.dashboard.expensesincome.uiState.ExpensesIncomeUiState

@Composable
fun ExpensesIncomeScreenRoute(
    dashboardTabType: DashboardTabType,
    viewModel: ExpensesIncomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val expensesIncomeTitles = remember {
        if (dashboardTabType == DashboardTabType.EXPENSES) {
            ExpensesIncomeTitles.expensesTitles()
        } else {
            ExpensesIncomeTitles.incomeTitles()
        }
    }

    ExpensesIncomeScreen(
        uiState = uiState,
        expensesIncomeTitles = expensesIncomeTitles,
        onPieChartNextDateClick = viewModel::onPieChartNextDateClick,
        onPieChartPreviousDateClick = viewModel::onPieChartPreviousDateClick
    )
}

@Composable
private fun ExpensesIncomeScreen(
    uiState: ExpensesIncomeUiState,
    expensesIncomeTitles: ExpensesIncomeTitles,
    onPieChartNextDateClick: () -> Unit,
    onPieChartPreviousDateClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.backgroundSecondary)
            .verticalScroll(rememberScrollState())
    ) {
        Crossfade(targetState = uiState.loadingContentState) { lce ->
            when (lce) {
                is LoadingContentState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        AppCircularProgressBar(
                            modifier = Modifier.padding(
                                top = dimensionResource(id = R.dimen.dashboard_padding_from_toolbar)
                            ),
                            color = AppTheme.colors.contendAccentPrimary
                        )
                    }
                }
                is LoadingContentState.Content -> {
                    ExpensesIncomeBody(
                        uiState = uiState,
                        expensesIncomeTitles = expensesIncomeTitles,
                        onPieChartNextDateClick = onPieChartNextDateClick,
                        onPieChartPreviousDateClick = onPieChartPreviousDateClick
                    )
                }
                is LoadingContentState.Error -> {
                    // show error
                }
                else -> Unit
            }
        }
    }
}
