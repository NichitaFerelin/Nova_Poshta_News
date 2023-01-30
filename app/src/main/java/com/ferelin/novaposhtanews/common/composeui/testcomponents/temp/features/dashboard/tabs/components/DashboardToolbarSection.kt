package com.endava.budgetplanner.features.dashboard.tabs.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.AppIconButton
import com.endava.budgetplanner.common.composeui.components.apptoolbar.AppToolbar
import com.endava.budgetplanner.common.composeui.components.apptoolbar.AppToolbarTitle
import com.endava.budgetplanner.common.composeui.components.tabs.AppTab
import com.endava.budgetplanner.common.composeui.components.tabs.AppTabRow
import com.endava.budgetplanner.common.composeui.theme.AppTheme
import com.endava.budgetplanner.domain.DashboardTabType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

private const val EXPENSES_TAB_INDEX = 0
private const val INCOME_TAB_INDEX = 1

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DashboardToolbarSection(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    selectedScreen: DashboardTabType,
    onSelectScreenClick: (DashboardTabType) -> Unit,
    onSettingsClick: () -> Unit
) {
    Surface(elevation = dimensionResource(id = R.dimen.app_elevation)) {
        Column(modifier = modifier.fillMaxWidth()) {
            AppToolbar(
                modifier = modifier.fillMaxWidth(),
                title = { externalModifier ->
                    AppToolbarTitle(
                        modifier = externalModifier,
                        title = stringResource(id = R.string.dashboard_title)
                    )
                },
                rightAction = { externalModifier ->
                    AppIconButton(
                        modifier = externalModifier,
                        painter = painterResource(id = R.drawable.ic_settings),
                        contentDescription = stringResource(id = R.string.description_settings),
                        enabledTint = AppTheme.colors.onContendAccentPrimary,
                        disabledTint = AppTheme.colors.onContendAccentPrimary,
                        enabled = true,
                        onClick = onSettingsClick
                    )
                }
            )
            AppTabRow(
                pagerState = pagerState,
                selectedTabIndex = selectedScreen.index
            ) {
                AppTab(
                    text = stringResource(id = R.string.dashboard_expenses),
                    selected = selectedScreen.index == EXPENSES_TAB_INDEX,
                    onClick = { onSelectScreenClick(DashboardTabType.EXPENSES) }
                )
                AppTab(
                    text = stringResource(id = R.string.dashboard_income),
                    selected = selectedScreen.index == INCOME_TAB_INDEX,
                    onClick = { onSelectScreenClick(DashboardTabType.INCOME) }
                )
            }
        }
    }
}
