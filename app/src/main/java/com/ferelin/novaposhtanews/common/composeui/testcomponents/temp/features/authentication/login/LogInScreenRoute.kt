package com.endava.budgetplanner.features.authentication.login

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.AppButton
import com.endava.budgetplanner.common.composeui.components.OverflowedText
import com.endava.budgetplanner.common.composeui.components.apptoolbar.AppToolbarBack
import com.endava.budgetplanner.common.composeui.components.textfield.AppTextField
import com.endava.budgetplanner.common.composeui.components.textfield.AppTextFieldWithClearIcon
import com.endava.budgetplanner.common.composeui.theme.AppTheme
import com.endava.budgetplanner.features.authentication.login.components.LoginHeaderSection
import com.endava.budgetplanner.features.authentication.login.model.AuthLogInViewModel
import com.endava.budgetplanner.features.authentication.login.model.CredentialValidationStatuses
import com.endava.budgetplanner.utils.showToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val SPACER_WEIGHT = 1f
private const val PASSWORD_MASK = '\u002A'

@Composable
fun LogInScreenRoute(
    viewModel: AuthLogInViewModel = hiltViewModel(),
    onBackRoute: () -> Unit,
    onLogInRoute: () -> Unit,
    onSignUpRoute: () -> Unit
) {
    val curContext = LocalContext.current
    ShowMessages(viewModel, curContext)

    if (viewModel.credentialsValidated) {
        LaunchedEffect(key1 = viewModel.credentialsValidated) {
            onLogInRoute()
        }
    }

    LoginScreen(onBackRoute, viewModel, onSignUpRoute)
}

@Composable
private fun LoginScreen(
    onBackRoute: () -> Unit,
    viewModel: AuthLogInViewModel,
    onSignUpRoute: () -> Unit
) {
    Scaffold(
        topBar = {
            AppToolbarBack(
                title = stringResource(id = R.string.login_text_sign_in),
                onBackClick = onBackRoute
            )
        },
        content = { contentPadding ->
            LogInMainContext(
                contentPadding,
                viewModel.email,
                viewModel.password,
                viewModel.enableLoginButton,
                viewModel.isLoading,
                viewModel.validationStatus,
                viewModel::onEmailChanged,
                viewModel::onPasswordChanged,
                viewModel::validateCredentials,
                viewModel::clearPassword,
                onSignUpRoute
            )
        },
        backgroundColor = AppTheme.colors.backgroundPrimary
    )
}

@Composable
private fun ShowMessages(
    viewModel: AuthLogInViewModel,
    curContext: Context
) {
    LaunchedEffect(Unit) {
        viewModel.userMessages.onEach { curContext.showToast(it.errorRes) }.launchIn(this)
    }
}

@Composable
private fun LogInMainContext(
    contentPadding: PaddingValues,
    email: String,
    password: String,
    enableLoginButton: Boolean,
    isLoading: Boolean,
    validationStatus: CredentialValidationStatuses,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    validateCredentials: () -> Unit,
    clearPassword: () -> Unit,
    onSignUpRoute: () -> Unit
) {
    Surface(
        color = AppTheme.colors.backgroundPrimary,
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.login_main_surface_padding),
                    end = dimensionResource(id = R.dimen.login_main_surface_padding),
                    top = dimensionResource(R.dimen.login_main_surface_padding_top)
                )
                .verticalScroll(rememberScrollState())
        ) {
            LoginHeaderSection(onSignUpRoute)

            val focusManager = LocalFocusManager.current

            AppTextField(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.login_email_padding_top))
                    .fillMaxWidth(),
                value = email,
                label = stringResource(id = R.string.label_email_address),
                error = "",
                readOnly = isLoading,
                isError = (validationStatus == CredentialValidationStatuses.EMAIL_NOT_VALID) ||
                    (validationStatus == CredentialValidationStatuses.EMAIL_AND_PASSWORD_NOT_VALID),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                onValueChange = onEmailChanged
            )

            AppTextFieldWithClearIcon(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.login_password_padding_top))
                    .fillMaxWidth(),
                value = password,
                label = stringResource(id = R.string.label_password),
                error = "",
                isReadOnly = isLoading,
                isError = (validationStatus == CredentialValidationStatuses.PASSWORD_NOT_VALID) ||
                    (validationStatus == CredentialValidationStatuses.EMAIL_AND_PASSWORD_NOT_VALID),
                visualTransformation = PasswordVisualTransformation(mask = PASSWORD_MASK),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                onValueChange = onPasswordChanged,
                onClearClick = clearPassword
            )

            Spacer(modifier = Modifier.weight(SPACER_WEIGHT))

            AppButton(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.login_auth_btn_padding_top))
                    .height(dimensionResource(id = R.dimen.login_button_height)),
                enabled = enableLoginButton,
                isLoading = isLoading,
                onClick = validateCredentials
            ) {
                OverflowedText(
                    text = stringResource(id = R.string.login_text_log_in),
                    style = AppTheme.typography.boldBody,
                    color = AppTheme.colors.onContendAccentPrimary
                )
            }
        }
    }
}
