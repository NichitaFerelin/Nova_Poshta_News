package com.endava.budgetplanner.data.remote.source

import com.endava.budgetplanner.data.dispatchers.AppDispatchers
import com.endava.budgetplanner.data.dispatchers.Dispatcher
import com.endava.budgetplanner.data.remote.api.API_SIGNUP_SUCCESS
import com.endava.budgetplanner.data.remote.api.API_SIGNUP_USER_EXIST
import com.endava.budgetplanner.data.remote.api.API_SIGNUP_VALIDATION_ERROR
import com.endava.budgetplanner.data.remote.api.SignUpApi
import com.endava.budgetplanner.data.remote.model.authentication.AuthenticationRequestParams
import com.endava.budgetplanner.data.remote.model.authentication.SignUpResponse
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SignUpRemoteSource {
    suspend fun signUp(params: AuthenticationRequestParams): SignUpResponse
}

class SignUpRemoteSourceImpl @Inject constructor(
    private val signUpApi: SignUpApi,
    private val validationErrorAdapter: JsonAdapter<SignUpResponse.ValidationError>,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : SignUpRemoteSource {

    override suspend fun signUp(params: AuthenticationRequestParams): SignUpResponse =
        withContext(ioDispatcher) {
            try {
                val response = signUpApi.signUp(params)

                when (response.code()) {
                    API_SIGNUP_SUCCESS -> SignUpResponse.Success
                    API_SIGNUP_USER_EXIST -> SignUpResponse.UserAlreadyExist
                    API_SIGNUP_VALIDATION_ERROR -> {
                        val responseBody = response.errorBody()?.string().orEmpty()
                        validationErrorAdapter.fromJson(responseBody)
                            ?: SignUpResponse.UnknownBodyError
                    }
                    else -> SignUpResponse.UnknownError()
                }
            } catch (e: Exception) {
                SignUpResponse.UnknownError(e)
            }
        }
}
