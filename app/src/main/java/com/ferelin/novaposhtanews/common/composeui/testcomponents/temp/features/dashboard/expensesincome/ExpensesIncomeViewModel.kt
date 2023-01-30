package com.endava.budgetplanner.features.dashboard.expensesincome

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endava.budgetplanner.common.composeui.components.piechart.PieChartItem
import com.endava.budgetplanner.domain.DashboardTabType
import com.endava.budgetplanner.domain.LoadingContentState
import com.endava.budgetplanner.features.dashboard.expensesincome.uiState.ExpensesIncomeUiState
import com.endava.budgetplanner.features.dashboard.expensesincome.uiState.MonthBudget
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val FAKE_LOADING_DELAY = 1_000L
private const val MOCK_JANUARY = "January"
private const val MOCK_FEBRUARY = "February"
private const val MOCK_MARCH = "March"

class ExpensesIncomeViewModel @AssistedInject constructor(
    @Assisted private val dashboardTabType: DashboardTabType
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesIncomeUiState())
    val uiState: StateFlow<ExpensesIncomeUiState> = _uiState.asStateFlow()

    private val dates = arrayOf(MOCK_JANUARY, MOCK_FEBRUARY, MOCK_MARCH)
    private val datesToIndex = hashMapOf(
        MOCK_JANUARY to 0,
        MOCK_FEBRUARY to 1,
        MOCK_MARCH to 2
    )

    private val dateToChartItemsContainer = hashMapOf(
        MOCK_JANUARY to
            MonthBudget(
                spent = "5 437 EUR",
                left = "4 563 EUR",
                chartItems = listOf(
                    PieChartItem(14.3f, Color.Yellow),
                    PieChartItem(14.7f, Color.Blue),
                    PieChartItem(52.2f, Color.Cyan),
                    PieChartItem(18.8f, Color.Magenta)
                )
            ),

        MOCK_FEBRUARY to MonthBudget(
            spent = "10 437 EUR",
            left = "2 563 EUR",
            chartItems = listOf(
                PieChartItem(99.5f, Color.Yellow),
                PieChartItem(0.5f, Color.Blue)
            )
        ),
        MOCK_MARCH to MonthBudget(
            spent = "437 EUR",
            left = "9 563 EUR",
            chartItems = listOf(
                PieChartItem(5f, Color.Yellow),
                PieChartItem(5f, Color.Blue),
                PieChartItem(5f, Color.Cyan),
                PieChartItem(5f, Color.Red),
                PieChartItem(80f, Color.Magenta)
            )
        )
    )

    init {
        dashboardTabType

        viewModelScope.launch {
            _uiState.update { it.copy(loadingContentState = LoadingContentState.Loading) }

            delay(FAKE_LOADING_DELAY)

            _uiState.update {
                it.copy(
                    loadingContentState = LoadingContentState.Content,
                    pieChartDate = MOCK_FEBRUARY,
                    pieChartItems = dateToChartItemsContainer[MOCK_FEBRUARY]!!.chartItems,
                    pieChartLeftBudget = dateToChartItemsContainer[MOCK_FEBRUARY]!!.left,
                    pieChartSpentBudget = dateToChartItemsContainer[MOCK_FEBRUARY]!!.spent,
                    pieChartNextDateExist = true,
                    pieChartPreviousDateExist = true
                )
            }
        }
    }

    fun onPieChartNextDateClick() {
        val currentDate = _uiState.value.pieChartDate
        val nextDateIndex = datesToIndex[currentDate]!! + 1
        switchPieChartTo(nextDateIndex)
    }

    fun onPieChartPreviousDateClick() {
        val currentDate = _uiState.value.pieChartDate
        val previousDateIndex = datesToIndex[currentDate]!! - 1
        switchPieChartTo(previousDateIndex)
    }

    private fun switchPieChartTo(index: Int) {
        val newDate = dates[index]
        val newDateMonthBudget = dateToChartItemsContainer[newDate]!!

        _uiState.update {
            it.copy(
                pieChartDate = newDate,
                pieChartItems = newDateMonthBudget.chartItems,
                pieChartLeftBudget = newDateMonthBudget.left,
                pieChartSpentBudget = newDateMonthBudget.spent,
                pieChartNextDateExist = index != dates.lastIndex,
                pieChartPreviousDateExist = index != 0
            )
        }
    }
}
