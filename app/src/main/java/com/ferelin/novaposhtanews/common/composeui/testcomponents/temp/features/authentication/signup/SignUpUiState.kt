package com.endava.budgetplanner.features.authentication.signup

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.endava.budgetplanner.R
import com.endava.budgetplanner.domain.LoadingContentState

@Immutable
data class SignUpUiState(
    val loadingContentState: LoadingContentState = LoadingContentState.None,
    val emailInput: String = "",
    val emailErrorState: EmailErrorState = EmailErrorState.None,
    val passwordInput: String = "",
    val passwordErrorState: PasswordErrorState = PasswordErrorState.None,
    val signUpBtnEnabled: Boolean = false
)

enum class EmailErrorState(@StringRes val errorRes: Int) {
    None(R.string.error_unknown),
    NotValid(R.string.error_not_valid_email),
    AlreadyExist(R.string.error_email_already_exist)
}

enum class PasswordErrorState(@StringRes val errorRes: Int) {
    None(R.string.error_unknown),
    NotValid(R.string.error_not_valid_password)
}

sealed class SignUpScreenEvent {
    object SignUpUnknownServerResponse : SignUpScreenEvent()
    object SignUpUnknownError : SignUpScreenEvent()
    object SignUpSuccessSignInFailed : SignUpScreenEvent()
    object SignInSuccess : SignUpScreenEvent()
}
