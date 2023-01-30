package com.endava.budgetplanner.features.dashboard.tabs

import androidx.lifecycle.ViewModel
import com.endava.budgetplanner.domain.DashboardTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DashboardTabsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardTabsUiState())
    val uiState: StateFlow<DashboardTabsUiState> = _uiState.asStateFlow()

    fun onDashboardTabScreenSelect(dashboardTabType: DashboardTabType) {
        _uiState.update { it.copy(dashboardSelectedTabScreen = dashboardTabType) }
    }
}
