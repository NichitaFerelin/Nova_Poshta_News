package com.endava.budgetplanner.common.composeui.components.apptoolbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.endava.budgetplanner.common.composeui.components.OverflowedText
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun AppToolbarTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    OverflowedText(
        modifier = modifier,
        text = title,
        style = AppTheme.typography.regularTitle4,
        color = AppTheme.colors.onContendAccentPrimary
    )
}
