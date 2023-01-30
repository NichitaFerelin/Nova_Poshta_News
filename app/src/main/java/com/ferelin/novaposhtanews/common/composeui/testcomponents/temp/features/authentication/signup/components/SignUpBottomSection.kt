package com.endava.budgetplanner.features.authentication.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.AppButton
import com.endava.budgetplanner.common.composeui.components.AppClickableText
import com.endava.budgetplanner.common.composeui.components.OverflowedText
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun SignUpBottomSection(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    signUpBtnEnabled: Boolean,
    onTermsOfServiceClick: () -> Unit,
    onSignUpButtonClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppClickableText(
            commonTextStyle = TextStyle(textAlign = TextAlign.Center),
            defaultText = stringResource(id = R.string.sign_up_agreement) + "\n",
            defaultTextStyle = AppTheme.typography.regularCallout,
            defaultTextColor = AppTheme.colors.text2,
            clickableText = stringResource(id = R.string.sign_up_terms_of_service),
            clickableTextStyle = AppTheme.typography.boldCallout,
            clickableTextColor = AppTheme.colors.contendAccentPrimary,
            onClick = onTermsOfServiceClick
        )
        Spacer(
            modifier = Modifier.height(
                dimensionResource(id = R.dimen.authentication_padding_top_from_terms_of_service)
            )
        )
        AppButton(
            enabled = signUpBtnEnabled,
            isLoading = isLoading,
            onClick = onSignUpButtonClick
        ) {
            OverflowedText(
                text = stringResource(id = R.string.sign_up_i_am_new_here),
                style = AppTheme.typography.boldBody,
                color = AppTheme.colors.onContendAccentPrimary
            )
        }
    }
}
