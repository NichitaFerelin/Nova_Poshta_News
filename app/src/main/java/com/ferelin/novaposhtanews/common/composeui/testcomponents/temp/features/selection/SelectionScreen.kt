package com.endava.budgetplanner.features.selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.AppButton
import com.endava.budgetplanner.common.composeui.components.AppClickableText
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun SelectionScreen(
    onSignUpRoute: () -> Unit,
    onSignInRoute: () -> Unit
) {
    Surface(
        color = AppTheme.colors.backgroundPrimary,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.selection_screen_margins_medium)
                )
        ) {
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(id = R.dimen.selection_screen_margin_top_large)
                )
            )
            Image(
                painter = painterResource(id = R.drawable.ic_selection_flow),
                contentDescription = stringResource(id = R.string.selection_screen_image_description)
            )
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(id = R.dimen.selection_screen_spacer_large)
                )
            )
            WelcomeText()
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(id = R.dimen.selection_screen_spacer_extra_large)
                )
            )
            AppButton(
                enabled = true,
                onClick = onSignUpRoute,
                modifier = Modifier.fillMaxWidth(),
                isLoading = false
            ) {
                Text(
                    text = stringResource(id = R.string.btn_sign_up),
                    style = AppTheme.typography.boldBody,
                    color = AppTheme.colors.onContendAccentPrimary
                )
            }
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(id = R.dimen.selection_screen_spacer_medium)
                )
            )
            AppClickableText(
                defaultText = stringResource(id = R.string.selection_screen_sign_in_pre_text) + " ",
                defaultTextStyle = AppTheme.typography.boldCallout,
                defaultTextColor = AppTheme.colors.text1,
                clickableText = stringResource(id = R.string.selection_screen_sign_in_text),
                clickableTextStyle = AppTheme.typography.boldCallout,
                clickableTextColor = AppTheme.colors.contendAccentPrimary,
                onClick = onSignInRoute
            )
        }
    }
}

@Composable
fun WelcomeText() {
    Text(
        text = stringResource(id = R.string.selection_screen_title),
        style = AppTheme.typography.boldTitle4,
        color = AppTheme.colors.text1
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.selection_screen_spacer_small)))
    Text(
        text = stringResource(id = R.string.selection_screen_sub_title),
        style = AppTheme.typography.regularBody,
        color = AppTheme.colors.text2,
        textAlign = TextAlign.Center
    )
}
