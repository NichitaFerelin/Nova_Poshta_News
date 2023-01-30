package com.endava.budgetplanner.features.initialbudget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.AppButton
import com.endava.budgetplanner.common.composeui.components.OverflowedText
import com.endava.budgetplanner.common.composeui.components.apptoolbar.AppToolbarBack
import com.endava.budgetplanner.common.composeui.components.textfield.AppNumberTextField
import com.endava.budgetplanner.common.composeui.theme.AppTheme
import com.endava.budgetplanner.domain.LoadingContentState
import com.endava.budgetplanner.features.initialbudget.models.InitialBudgetViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun InitialBudgetSetupScreenRoute(
    viewModel: InitialBudgetViewModel = hiltViewModel(),
    onContinueRoute: () -> Unit,
    onBackRoute: () -> Unit
) {
    val uitState by viewModel.uiState.collectAsStateWithLifecycle()

    InitialBudgetSetupScreen(
        uiState = uitState,
        onContinueRoute = onContinueRoute,
        onBackRoute = onBackRoute,
        onValueChange = viewModel::onInputValueChange
    )
}

@Composable
fun InitialBudgetSetupScreen(
    uiState: InitialBudgetState,
    onContinueRoute: () -> Unit,
    onBackRoute: () -> Unit,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.backgroundPrimary)
    ) {
        AppToolbarBack(
            title = stringResource(id = R.string.initial_budget_screen_toolbar_title),
            onBackClick = onBackRoute
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val (bodySection, bottomSection) = createRefs()
            val bodySectionTopPadding = dimensionResource(
                id = R.dimen.authentication_padding_top_from_toolbar
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .constrainAs(bodySection) {
                        top.linkTo(parent.top, margin = bodySectionTopPadding)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Spacer(
                    modifier = Modifier.height(
                        dimensionResource(id = R.dimen.initial_budget_screen_top_margin_large)
                    )
                )

                AppNumberTextField(
                    inputValue = uiState.budgetInput,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal =
                            dimensionResource(id = R.dimen.initial_budget_screen_padding_horizontal_medium)
                        ),
                    onValueChange = onValueChange
                )

                Spacer(
                    modifier = Modifier.padding(
                        end = dimensionResource(id = R.dimen.initial_budget_screen_spacer_small)
                    )
                )

                Text(
                    text = stringResource(id = R.string.initial_budget_screen_info_text),
                    style = AppTheme.typography.regularSubheadline,
                    color = AppTheme.colors.text2
                )
            }
            AppButton(
                enabled = uiState.continueButtonEnabled,
                isLoading = uiState.loadingContentState is LoadingContentState.Loading,
                onClick = onContinueRoute,
                modifier = Modifier
                    .constrainAs(bottomSection) {
                        linkTo(bodySection.bottom, bottomSection.top, bias = 1f)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(
                        start = dimensionResource(id = R.dimen.initial_budget_screen_padding_horizontal_medium),
                        end = dimensionResource(id = R.dimen.initial_budget_screen_padding_horizontal_medium),
                        bottom = dimensionResource(id = R.dimen.initial_budget_screen_bottom_space_large)
                    )
            ) {
                OverflowedText(
                    text = stringResource(id = R.string.initial_budget_screen_continue),
                    color = AppTheme.colors.iconPrimary,
                    style = AppTheme.typography.boldBody
                )
            }
        }
    }
}
