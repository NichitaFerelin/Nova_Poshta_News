package com.endava.budgetplanner.features.authentication.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.textfield.AppTextField
import com.endava.budgetplanner.common.composeui.components.textfield.AppTextFieldWithClearIcon
import com.endava.budgetplanner.domain.LoadingContentState
import com.endava.budgetplanner.features.authentication.signup.EmailErrorState
import com.endava.budgetplanner.features.authentication.signup.PasswordErrorState
import com.endava.budgetplanner.features.authentication.signup.SignUpUiState

private const val PASSWORD_MASK = '\u002A'

@Composable
fun SignUpTextFieldsSection(
    modifier: Modifier = Modifier,
    uiState: SignUpUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordClearClick: () -> Unit,
    onImeDone: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTextField(
            value = uiState.emailInput,
            label = stringResource(id = R.string.label_email_address),
            readOnly = uiState.loadingContentState is LoadingContentState.Loading,
            isError = uiState.emailErrorState != EmailErrorState.None,
            error = stringResource(id = uiState.emailErrorState.errorRes),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            onValueChange = onEmailChange
        )
        Spacer(
            modifier = Modifier.height(
                dimensionResource(id = R.dimen.authentication_text_fields_padding_between)
            )
        )
        AppTextFieldWithClearIcon(
            value = uiState.passwordInput,
            label = stringResource(id = R.string.label_password),
            error = stringResource(id = uiState.passwordErrorState.errorRes),
            isReadOnly = uiState.loadingContentState is LoadingContentState.Loading,
            isError = uiState.passwordErrorState != PasswordErrorState.None,
            visualTransformation = PasswordVisualTransformation(mask = PASSWORD_MASK),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onImeDone() }
            ),
            onValueChange = onPasswordChange,
            onClearClick = onPasswordClearClick
        )
    }
}
