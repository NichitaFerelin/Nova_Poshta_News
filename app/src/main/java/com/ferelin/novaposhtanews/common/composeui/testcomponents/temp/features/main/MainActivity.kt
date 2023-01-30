package com.endava.budgetplanner.features.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.endava.budgetplanner.common.composeui.navigation.AppNavigationGraph
import com.endava.budgetplanner.common.composeui.theme.AppTheme
import com.endava.budgetplanner.features.dashboard.expensesincome.ExpensesIncomeViewModelFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun expensesIncomeViewModelFactory(): ExpensesIncomeViewModelFactory
    }

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.loadingState.value
            }
        }
        setContent {
            AppTheme {
                val navController = rememberNavController()
                AppNavigationGraph(
                    navController = navController,
                    mainViewModel.appDestinations.value
                )
            }
        }
    }
}
