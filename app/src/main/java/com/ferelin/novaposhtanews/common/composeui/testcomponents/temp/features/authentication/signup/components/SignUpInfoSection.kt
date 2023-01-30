package com.endava.budgetplanner.features.authentication.signup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.AppClickableText
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun SignUpInfoSection(
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(id = R.string.description_logo)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_top_from_logo)))
        Text(
            text = stringResource(id = R.string.sign_up_title_description),
            style = AppTheme.typography.boldTitle4,
            color = AppTheme.colors.text1,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_top_from_title_description)))
        AppClickableText(
            commonTextStyle = TextStyle(textAlign = TextAlign.Center),
            defaultText = stringResource(id = R.string.sign_up_already_have_account) + " ",
            defaultTextStyle = AppTheme.typography.regularCallout,
            defaultTextColor = AppTheme.colors.text2,
            clickableText = stringResource(id = R.string.sign_up_hint_sign_in),
            clickableTextStyle = AppTheme.typography.boldCallout,
            clickableTextColor = AppTheme.colors.contendAccentPrimary,
            onClick = onSignInClick
        )
    }
}
