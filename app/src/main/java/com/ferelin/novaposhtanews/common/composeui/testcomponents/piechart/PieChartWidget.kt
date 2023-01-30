package com.endava.budgetplanner.common.composeui.components.piechart

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.MainBudgetWidgetContainer

@Composable
fun <S> PieChartWidget(
    modifier: Modifier = Modifier,
    leftValue: String,
    leftValueTitle: String,
    rightValue: String,
    rightValueTitle: String,
    date: String,
    previousDateExist: Boolean,
    nextDateExist: Boolean,
    itemsStateKey: S,
    itemsSize: Int,
    itemPercentsValue: (Int) -> Float,
    itemsColor: (Int) -> Color,
    onPreviousDateClick: () -> Unit,
    onNextDateClick: () -> Unit
) {
    MainBudgetWidgetContainer(
        modifier = modifier,
        leftValue = leftValue,
        leftValueTitle = leftValueTitle,
        rightValue = rightValue,
        rightValueTitle = rightValueTitle,
        date = date,
        previousDateExist = previousDateExist,
        nextDateExist = nextDateExist,
        onPreviousDateClick = onPreviousDateClick,
        onNextDateClick = onNextDateClick
    ) {
        PieChart(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.dashboard_pie_chart_size))
                .align(Alignment.CenterHorizontally),
            itemsStateKey = itemsStateKey,
            itemsSize = itemsSize,
            itemPercentsValue = itemPercentsValue,
            itemsColor = itemsColor
        )
    }
}
