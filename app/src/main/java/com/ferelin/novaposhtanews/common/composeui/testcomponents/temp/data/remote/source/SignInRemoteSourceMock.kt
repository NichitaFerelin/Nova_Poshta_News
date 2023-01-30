package com.endava.budgetplanner.data.remote.source

import com.endava.budgetplanner.data.remote.model.authentication.AuthenticationRequestParams
import com.endava.budgetplanner.data.remote.model.authentication.SignInResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

private const val FAKE_TOKEN = "eAAAeferaf205djsd395493sdsf"
private const val DIV_TWO = 2
private const val DIV_THREE = 3
private const val WRONG_CREDENTIALS_CODE = "400"
private const val EXP_TOKEN_DATE = "2022-11-11T11:31:50.8670641+02:00"

class SignInRemoteSourceMock @Inject constructor() : SignInRemoteSource {

    private var requestCounter = 0

    override suspend fun signIn(params: AuthenticationRequestParams): SignInResponse {
        requestCounter++

        delay(RESPONSE_DELAY)

        return when {
            requestCounter % DIV_THREE == 0 -> SignInResponse.InvalidCredentialsError(
                WRONG_CREDENTIALS_CODE
            )
            requestCounter % DIV_TWO == 0 -> SignInResponse.UnknownError()
            else -> SignInResponse.Success(FAKE_TOKEN, EXP_TOKEN_DATE)
        }
    }

    private companion object {
        const val RESPONSE_DELAY = 1_000L
    }
}
