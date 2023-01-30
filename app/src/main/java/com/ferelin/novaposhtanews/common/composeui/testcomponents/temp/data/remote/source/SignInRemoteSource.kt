package com.endava.budgetplanner.data.remote.source

import com.endava.budgetplanner.data.dispatchers.AppDispatchers
import com.endava.budgetplanner.data.dispatchers.Dispatcher
import com.endava.budgetplanner.data.remote.api.API_SIGNIN_ERROR
import com.endava.budgetplanner.data.remote.api.API_SIGNIN_SUCCESS
import com.endava.budgetplanner.data.remote.api.SignInApi
import com.endava.budgetplanner.data.remote.model.authentication.AuthenticationRequestParams
import com.endava.budgetplanner.data.remote.model.authentication.SignInResponse
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SignInRemoteSource {
    suspend fun signIn(params: AuthenticationRequestParams): SignInResponse
}

class SignInRemoteSourceImpl @Inject constructor(
    private val signInApi: SignInApi,
    private val signInSuccessAdapter: JsonAdapter<SignInResponse.Success>,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : SignInRemoteSource {

    override suspend fun signIn(params: AuthenticationRequestParams): SignInResponse =
        withContext(ioDispatcher) {
            try {
                val response = signInApi.signIn(params)
                val responseBody = response.body()?.string().orEmpty()

                when (response.code()) {
                    API_SIGNIN_SUCCESS -> {
                        signInSuccessAdapter.fromJson(responseBody)
                            ?: SignInResponse.UnknownBodyError
                    }
                    API_SIGNIN_ERROR -> SignInResponse.InvalidCredentialsError(responseBody)
                    else -> SignInResponse.UnknownError()
                }
            } catch (e: Exception) {
                SignInResponse.UnknownError(e)
            }
        }
}
