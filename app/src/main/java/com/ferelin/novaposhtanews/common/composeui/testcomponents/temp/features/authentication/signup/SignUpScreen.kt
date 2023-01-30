package com.endava.budgetplanner.features.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.endava.budgetplanner.BuildConfig
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.apptoolbar.AppToolbarBack
import com.endava.budgetplanner.common.composeui.theme.AppTheme
import com.endava.budgetplanner.domain.LoadingContentState
import com.endava.budgetplanner.features.authentication.signup.components.SignUpBottomSection
import com.endava.budgetplanner.features.authentication.signup.components.SignUpInfoSection
import com.endava.budgetplanner.features.authentication.signup.components.SignUpTextFieldsSection
import com.endava.budgetplanner.utils.openUrl
import com.endava.budgetplanner.utils.showToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SignUpScreenRoute(
    viewModel: SignUpViewModel = hiltViewModel(),
    onBackRoute: () -> Unit,
    onSignInRoute: () -> Unit,
    onMainScreenRoute: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.signUpScreenEvent
            .onEach { signUpEvent ->
                when (signUpEvent) {
                    is SignUpScreenEvent.SignInSuccess -> onMainScreenRoute()
                    is SignUpScreenEvent.SignUpUnknownError -> {
                        context.showToast(R.string.error_unknown)
                    }
                    is SignUpScreenEvent.SignUpUnknownServerResponse -> {
                        context.showToast(R.string.error_unknown_server_response)
                    }
                    is SignUpScreenEvent.SignUpSuccessSignInFailed -> {
                        context.showToast(R.string.error_success_sign_up_error_sign_in)
                        onSignInRoute()
                    }
                }
            }
            .launchIn(this)
    }

    val onTermsOfServiceClick = remember {
        {
            val success = context.openUrl(BuildConfig.TERMS_AND_CONDITIONS_URL)
            if (!success) {
                context.showToast(R.string.error_no_browser_found)
            }
        }
    }

    SignUpScreen(
        uiState = uiState,
        onBackClick = onBackRoute,
        onSignInClick = onSignInRoute,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onPasswordClearClick = viewModel::onPasswordClearClick,
        onPasswordImeDone = viewModel::onPasswordDone,
        onTermsOfServiceClick = onTermsOfServiceClick,
        onSignUpButtonClick = viewModel::signUp
    )
}

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    onBackClick: () -> Unit,
    onSignInClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordClearClick: () -> Unit,
    onPasswordImeDone: () -> Unit,
    onTermsOfServiceClick: () -> Unit,
    onSignUpButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.backgroundPrimary)
    ) {
        AppToolbarBack(
            title = stringResource(id = R.string.sign_up_title),
            onBackClick = onBackClick
        )
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val (bodySection, bottomSection) = createRefs()
            val bodySectionTopPadding = dimensionResource(
                id = R.dimen.authentication_padding_top_from_toolbar
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.authentication_body_padding_horizontal)
                    )
                    .constrainAs(bodySection) {
                        top.linkTo(parent.top, margin = bodySectionTopPadding)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignUpInfoSection(onSignInClick = onSignInClick)
                Spacer(
                    modifier = Modifier.height(
                        dimensionResource(id = R.dimen.authentication_padding_top_from_title_description)
                    )
                )
                SignUpTextFieldsSection(
                    uiState = uiState,
                    onEmailChange = onEmailChange,
                    onPasswordChange = onPasswordChange,
                    onPasswordClearClick = onPasswordClearClick,
                    onImeDone = onPasswordImeDone
                )
            }
            SignUpBottomSection(
                modifier = Modifier
                    .constrainAs(bottomSection) {
                        linkTo(bodySection.bottom, bottomSection.top, bias = 1f)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(
                        start = dimensionResource(id = R.dimen.authentication_body_padding_horizontal),
                        end = dimensionResource(id = R.dimen.authentication_body_padding_horizontal),
                        bottom = dimensionResource(id = R.dimen.authentication_bottom_space)
                    ),
                isLoading = uiState.loadingContentState is LoadingContentState.Loading,
                signUpBtnEnabled = uiState.signUpBtnEnabled,
                onTermsOfServiceClick = onTermsOfServiceClick,
                onSignUpButtonClick = onSignUpButtonClick
            )
        }
    }
}
