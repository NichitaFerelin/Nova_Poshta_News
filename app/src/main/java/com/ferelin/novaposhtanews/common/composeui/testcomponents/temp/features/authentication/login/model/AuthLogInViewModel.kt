package com.endava.budgetplanner.features.authentication.login.model

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endava.budgetplanner.R
import com.endava.budgetplanner.data.remote.model.authentication.SignInResponse
import com.endava.budgetplanner.data.repositories.AuthRepository
import com.endava.budgetplanner.data.repositories.LoggedUserRepository
import com.endava.budgetplanner.utils.CredentialValidationHelpers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthLogInViewModel @Inject constructor(
    private val authDataRepository: AuthRepository,
    private val loggedUserRepository: LoggedUserRepository
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var validationStatus: CredentialValidationStatuses by mutableStateOf(
        CredentialValidationStatuses.NONE
    )
        private set

    var enableLoginButton by mutableStateOf(false)
        private set

    var isLoading by mutableStateOf(false)
        private set

    private val _userMessages = Channel<UserMessages>()
    val userMessages = _userMessages.receiveAsFlow()

    var credentialsValidated by mutableStateOf(false)
        private set

    fun onEmailChanged(email: String) {
        this.email = email
        validationStatus = CredentialValidationStatuses.NONE
        validateUiCredentialsFields()
    }

    fun onPasswordChanged(password: String) {
        this.password = password
        validationStatus = CredentialValidationStatuses.NONE
        validateUiCredentialsFields()
    }

    fun clearPassword() {
        password = ""
    }

    fun validateCredentials() {
        val emailValidationStatus = CredentialValidationHelpers.validateEmail(email)
        val passwordValidationStatus = CredentialValidationHelpers.validatePassword(password)

        validationStatus = when {
            !emailValidationStatus &&
                !passwordValidationStatus -> CredentialValidationStatuses.EMAIL_AND_PASSWORD_NOT_VALID
            !emailValidationStatus -> CredentialValidationStatuses.EMAIL_NOT_VALID
            !passwordValidationStatus -> CredentialValidationStatuses.PASSWORD_NOT_VALID
            else -> CredentialValidationStatuses.SUCCESS
        }

        if (validationStatus == CredentialValidationStatuses.SUCCESS) {
            isLoading = true
            checkLogin(email, password)
        }
    }

    private fun validateUiCredentialsFields() {
        enableLoginButton = email.isNotEmpty() && password.isNotEmpty()
    }

    private fun checkLogin(login: String, password: String) {
        viewModelScope.launch {
            loggedUserRepository.setToken("")

            when (val loginCheckResult = authDataRepository.getCredentialsInfo(login, password)) {
                is SignInResponse.UnknownBodyError -> {
                    isLoading = false
                    _userMessages.send(UserMessages.GenericError)
                }
                is SignInResponse.UnknownError -> {
                    isLoading = false
                    _userMessages.send(UserMessages.NetworkError)
                }
                is SignInResponse.Success -> {
                    isLoading = false
                    if (loginCheckResult.accessToken.isNotEmpty()) {
                        loggedUserRepository.setToken(
                            loginCheckResult.accessToken,
                            loginCheckResult.expiredDate
                        )
                        credentialsValidated = true
                        _userMessages.send(UserMessages.Success)
                    }
                }
                is SignInResponse.InvalidCredentialsError -> {
                    isLoading = false
                    validationStatus =
                        CredentialValidationStatuses.EMAIL_AND_PASSWORD_NOT_VALID
                    _userMessages.send(UserMessages.InvalidCredentials)
                }
            }
        }
    }
}

enum class CredentialValidationStatuses {
    SUCCESS,
    EMAIL_NOT_VALID,
    PASSWORD_NOT_VALID,
    EMAIL_AND_PASSWORD_NOT_VALID,
    NONE
}

enum class UserMessages(@StringRes val errorRes: Int) {
    InvalidCredentials(R.string.error_invalid_credentials),
    NetworkError(R.string.error_network),
    GenericError(R.string.error_generic),
    Success(R.string.login_success_message)
}
