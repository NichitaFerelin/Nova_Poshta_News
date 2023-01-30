@file:OptIn(ExperimentalPagerApi::class, ExperimentalLifecycleComposeApi::class)

package com.endava.budgetplanner.features.dashboard.tabs

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.AppFloatingActionButton
import com.endava.budgetplanner.domain.DashboardTabType
import com.endava.budgetplanner.features.dashboard.expensesincome.ExpensesIncomeScreenRoute
import com.endava.budgetplanner.features.dashboard.expensesincome.expensesIncomeViewModel
import com.endava.budgetplanner.features.dashboard.tabs.components.DashboardToolbarSection
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

private const val DASHBOARD_TABS = 2

@Composable
fun DashboardTabsScreenRoute(
    viewModel: DashboardTabsViewModel = hiltViewModel(),
    onSettingsRoute: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DashboardTabsScreen(
        uiState = uiState,
        onDashboardTabClick = viewModel::onDashboardTabScreenSelect,
        onSettingsClick = onSettingsRoute,
        onCreateTransactionClick = { /**/ }
    )
}

@Composable
private fun DashboardTabsScreen(
    uiState: DashboardTabsUiState,
    onDashboardTabClick: (DashboardTabType) -> Unit,
    onSettingsClick: () -> Unit,
    onCreateTransactionClick: () -> Unit
) {
    val pagerState = rememberPagerState()

    LaunchedEffect(uiState.dashboardSelectedTabScreen) {
        pagerState.scrollToPage(uiState.dashboardSelectedTabScreen.index)
    }

    Scaffold(
        topBar = {
            DashboardToolbarSection(
                pagerState = pagerState,
                selectedScreen = uiState.dashboardSelectedTabScreen,
                onSelectScreenClick = onDashboardTabClick,
                onSettingsClick = onSettingsClick
            )
        },
        floatingActionButton = {
            AppFloatingActionButton(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = stringResource(id = R.string.description_create_transaction),
                onClick = onCreateTransactionClick
            )
        }
    ) { externalPadding ->
        HorizontalPager(
            modifier = Modifier.padding(externalPadding),
            state = pagerState,
            count = DASHBOARD_TABS,
            userScrollEnabled = false
        ) { pageIndex ->
            when (pageIndex) {
                DashboardTabType.EXPENSES.index -> {
                    ExpensesIncomeScreenRoute(
                        dashboardTabType = DashboardTabType.EXPENSES,
                        viewModel = expensesIncomeViewModel(DashboardTabType.EXPENSES)
                    )
                }
                DashboardTabType.INCOME.index -> {
                    ExpensesIncomeScreenRoute(
                        dashboardTabType = DashboardTabType.INCOME,
                        viewModel = expensesIncomeViewModel(DashboardTabType.INCOME)
                    )
                }
                else -> error("HorizontalPager screen not found for index [$pageIndex]")
            }
        }
    }
}
