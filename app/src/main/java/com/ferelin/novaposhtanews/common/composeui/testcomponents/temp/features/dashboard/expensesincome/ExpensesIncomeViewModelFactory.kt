package com.endava.budgetplanner.features.dashboard.expensesincome

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.endava.budgetplanner.domain.DashboardTabType
import com.endava.budgetplanner.features.main.MainActivity
import dagger.assisted.AssistedFactory
import dagger.hilt.android.EntryPointAccessors

@AssistedFactory
interface ExpensesIncomeViewModelFactory {
    fun create(dashboardTabType: DashboardTabType): ExpensesIncomeViewModel
}

@Composable
fun expensesIncomeViewModel(
    dashboardTabType: DashboardTabType
): ExpensesIncomeViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).expensesIncomeViewModelFactory()

    return viewModel(
        factory = provideFactory(factory, dashboardTabType),
        key = dashboardTabType.name
    )
}

@Suppress("UNCHECKED_CAST")
private fun provideFactory(
    assistedFactory: ExpensesIncomeViewModelFactory,
    dashboardTabType: DashboardTabType
): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(dashboardTabType) as T
    }
}
