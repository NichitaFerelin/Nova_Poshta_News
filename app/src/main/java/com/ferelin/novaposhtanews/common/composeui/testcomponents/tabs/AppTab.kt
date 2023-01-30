package com.endava.budgetplanner.common.composeui.components.tabs

import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.endava.budgetplanner.common.composeui.theme.AppTheme

private const val UNSELECTED_TAB_ALPHA = 0.75f

@Composable
fun AppTab(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Tab(
        modifier = modifier,
        selected = selected,
        text = {
            Text(
                text = text,
                style = AppTheme.typography.regularCaption3
            )
        },
        onClick = onClick,
        selectedContentColor = AppTheme.colors.onContendAccentPrimary,
        unselectedContentColor = AppTheme.colors.onContendAccentPrimary
            .copy(alpha = UNSELECTED_TAB_ALPHA)
    )
}
