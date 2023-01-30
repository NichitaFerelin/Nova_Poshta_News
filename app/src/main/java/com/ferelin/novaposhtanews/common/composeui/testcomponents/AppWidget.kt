package com.endava.budgetplanner.common.composeui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun AppWidget(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = AppTheme.shapes.small
            )
            .padding(dimensionResource(id = R.dimen.main_budget_inner_padding)),
        content = content
    )
}
