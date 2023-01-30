package com.endava.budgetplanner.features.initialbudget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.AppButton
import com.endava.budgetplanner.common.composeui.components.OverflowedText
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun OnboardingScreen(
    onContinueRoute: () -> Unit
) {
    Surface(
        color = AppTheme.colors.backgroundPrimary,
        modifier = Modifier
            .fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val (bodySection, bottomSection) = createRefs()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.selection_screen_margins_medium)
                    )
                    .constrainAs(bodySection) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Spacer(
                    modifier = Modifier.height(
                        dimensionResource(id = R.dimen.selection_screen_margin_top_large)
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_onboarding_logo),
                    contentDescription = stringResource(id = R.string.selection_screen_image_description),
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.onboarding_screen_image_height))
                        .width(dimensionResource(id = R.dimen.onboarding_screen_image_width))
                )
                Spacer(
                    modifier = Modifier.height(
                        dimensionResource(id = R.dimen.initial_budget_screen_margin_medium)
                    )
                )
                Text(
                    text = stringResource(id = R.string.initial_budget_screen_question),
                    style = AppTheme.typography.boldTitle4,
                    textAlign = TextAlign.Center
                )
            }
            AppButton(
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
                    ),
                enabled = true,
                isLoading = false,
                onClick = onContinueRoute
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
