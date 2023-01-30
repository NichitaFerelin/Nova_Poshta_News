package com.endava.budgetplanner.features.dashboard.tabs

import androidx.compose.runtime.Immutable
import com.endava.budgetplanner.domain.DashboardTabType

@Immutable
data class DashboardTabsUiState(
    val dashboardSelectedTabScreen: DashboardTabType = DashboardTabType.EXPENSES
)
