package com.endava.budgetplanner.features.authentication.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun LoginHeaderSection(onSignUpRoute: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = stringResource(id = R.string.content_desc_logo)
    )
    Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.login_header_spacer_padding_top)))
    Text(
        text = stringResource(id = R.string.login_text_greetings),
        style = AppTheme.typography.boldTitle4,
        color = AppTheme.colors.text1
    )
    Row(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.login_header_text_row_padding_top))) {
        Text(
            text = stringResource(id = R.string.login_text_account),
            style = AppTheme.typography.regularCallout,
            color = AppTheme.colors.text2
        )

        ClickableText(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.login_header_text_padding_start)
            ),
            text = AnnotatedString(stringResource(id = R.string.sign_up_title)),
            style = AppTheme.typography.regularCallout.plus(TextStyle(color = AppTheme.colors.contendAccentPrimary)),
            onClick = {
                onSignUpRoute()
            }
        )
    }
}
