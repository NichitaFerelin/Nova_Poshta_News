package com.endava.budgetplanner.features.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endava.budgetplanner.copy
import com.endava.budgetplanner.data.datastore.source.UserPreferencesLocalSource
import com.endava.budgetplanner.data.dispatchers.AppDispatchers
import com.endava.budgetplanner.data.dispatchers.Dispatcher
import com.endava.budgetplanner.data.remote.model.authentication.AuthenticationRequestParams
import com.endava.budgetplanner.data.remote.model.authentication.SignInResponse
import com.endava.budgetplanner.data.remote.model.authentication.SignUpResponse
import com.endava.budgetplanner.data.remote.source.SignInRemoteSource
import com.endava.budgetplanner.data.remote.source.SignUpRemoteSource
import com.endava.budgetplanner.domain.LoadingContentState
import com.endava.budgetplanner.features.authentication.utils.couldBeSubstringOfEmail
import com.endava.budgetplanner.features.authentication.utils.isValidEmail
import com.endava.budgetplanner.features.authentication.utils.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signInRemoteSource: SignInRemoteSource,
    private val signUpRemoteSource: SignUpRemoteSource,
    private val userPreferencesLocalSource: UserPreferencesLocalSource,
    @Dispatcher(AppDispatchers.Default) private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _signUpScreenEvent = Channel<SignUpScreenEvent>()
    val signUpScreenEvent = _signUpScreenEvent.receiveAsFlow()

    fun onPasswordDone() {
        if (_uiState.value.signUpBtnEnabled) signUp()
    }

    fun signUp() {
        _uiState.update { it.copy(loadingContentState = LoadingContentState.Loading) }

        viewModelScope.launch {
            val email = _uiState.value.emailInput
            val isEmailValid = withContext(defaultDispatcher) { email.isValidEmail() }

            val password = _uiState.value.passwordInput
            val isPasswordValid = withContext(defaultDispatcher) { password.isValidPassword() }

            if (isEmailValid && isPasswordValid) {
                tryToRegisterUser()
            } else {
                updateUiStateOnInvalidInput(isEmailValid, isPasswordValid)
            }
        }
    }

    fun onEmailChange(email: String) {
        if (!email.couldBeSubstringOfEmail()) return

        _uiState.update {
            it.copy(
                emailInput = email,
                emailErrorState = EmailErrorState.None,
                signUpBtnEnabled = email.isNotEmpty() &&
                    it.passwordInput.isNotEmpty() &&
                    it.passwordErrorState == PasswordErrorState.None
            )
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                passwordInput = password,
                passwordErrorState = PasswordErrorState.None,
                signUpBtnEnabled = password.isNotEmpty() &&
                    it.emailInput.isNotEmpty() &&
                    it.emailErrorState == EmailErrorState.None
            )
        }
    }

    fun onPasswordClearClick() {
        _uiState.update {
            it.copy(
                passwordInput = "",
                signUpBtnEnabled = false
            )
        }
    }

    private fun tryToRegisterUser() {
        viewModelScope.launch {
            val signUpResponse = signUpRemoteSource.signUp(
                AuthenticationRequestParams(
                    email = _uiState.value.emailInput,
                    password = _uiState.value.passwordInput
                )
            )

            if (signUpResponse is SignUpResponse.Success) {
                tryToSignInUser()
            } else {
                updateUiStateOnSignUpFailedResponse(signUpResponse)
            }
        }
    }

    private fun tryToSignInUser() {
        viewModelScope.launch {
            val signInResponse = signInRemoteSource.signIn(
                AuthenticationRequestParams(
                    email = _uiState.value.emailInput,
                    password = _uiState.value.passwordInput
                )
            )

            if (signInResponse is SignInResponse.Success) {
                onSignInSuccess(signInResponse)
            } else {
                _signUpScreenEvent.trySend(SignUpScreenEvent.SignUpSuccessSignInFailed)
                _uiState.update {
                    it.copy(loadingContentState = LoadingContentState.None)
                }
            }
        }
    }

    private fun onSignInSuccess(signInResponse: SignInResponse.Success) {
        viewModelScope.launch {
            signInResponse.cacheUpdatedToken()
            _signUpScreenEvent.trySend(SignUpScreenEvent.SignInSuccess)

            _uiState.update {
                it.copy(
                    loadingContentState = LoadingContentState.Content,
                    signUpBtnEnabled = false
                )
            }
        }
    }

    private fun updateUiStateOnSignUpFailedResponse(signUpResponse: SignUpResponse) {
        when (signUpResponse) {
            is SignUpResponse.UserAlreadyExist -> {
                _uiState.update {
                    it.copy(
                        loadingContentState = LoadingContentState.None,
                        emailErrorState = EmailErrorState.AlreadyExist,
                        signUpBtnEnabled = false
                    )
                }
            }
            is SignUpResponse.ValidationError -> {
                val isPasswordValid = signUpResponse.errors.password.isNullOrEmpty()
                val isEmailValid = signUpResponse.errors.email.isNullOrEmpty()

                _uiState.update {
                    it.copy(
                        loadingContentState = LoadingContentState.None,
                        emailErrorState = if (isEmailValid) {
                            EmailErrorState.None
                        } else {
                            EmailErrorState.NotValid
                        },
                        passwordErrorState = if (isPasswordValid) {
                            PasswordErrorState.None
                        } else {
                            PasswordErrorState.NotValid
                        },
                        signUpBtnEnabled = false
                    )
                }
            }
            is SignUpResponse.UnknownBodyError -> {
                _uiState.update {
                    it.copy(loadingContentState = LoadingContentState.None)
                }
                _signUpScreenEvent.trySend(SignUpScreenEvent.SignUpUnknownServerResponse)
            }
            is SignUpResponse.UnknownError -> {
                _uiState.update {
                    it.copy(loadingContentState = LoadingContentState.None)
                }
                _signUpScreenEvent.trySend(SignUpScreenEvent.SignUpUnknownError)
            }
            is SignUpResponse.Success -> Unit
        }
    }

    private fun updateUiStateOnInvalidInput(isEmailValid: Boolean, isPasswordValid: Boolean) {
        _uiState.update {
            it.copy(
                loadingContentState = LoadingContentState.None,
                emailErrorState = if (isEmailValid) {
                    EmailErrorState.None
                } else {
                    EmailErrorState.NotValid
                },
                passwordErrorState = if (isPasswordValid) {
                    PasswordErrorState.None
                } else {
                    PasswordErrorState.NotValid
                },
                signUpBtnEnabled = false
            )
        }
    }

    private suspend fun SignInResponse.Success.cacheUpdatedToken() {
        val userPreferences = userPreferencesLocalSource.userPreferences.first()
        userPreferencesLocalSource.updateUserPreferences(
            userPreferences.copy {
                userEmail = _uiState.value.emailInput
                authToken = this@cacheUpdatedToken.accessToken
                tokenExpStamp = this@cacheUpdatedToken.expiredDate
            }
        )
    }
}
