@file:OptIn(ExperimentalPagerApi::class)

package com.endava.budgetplanner.common.composeui.components.tabs

import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.endava.budgetplanner.common.composeui.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset

@Composable
fun AppTabRow(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    selectedTabIndex: Int,
    tabIndicator: @Composable (List<TabPosition>) -> Unit = { tabPositions ->
        TabRowDefaults.Indicator(
            modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
            color = AppTheme.colors.onContendAccentPrimary
        )
    },
    tabs: @Composable () -> Unit
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        backgroundColor = AppTheme.colors.contendAccentPrimary,
        indicator = tabIndicator,
        tabs = tabs
    )
}
