package com.endava.budgetplanner.data.remote.api

import com.endava.budgetplanner.data.remote.model.authentication.AuthenticationRequestParams
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

const val API_SIGNIN_SUCCESS = 200
const val API_SIGNIN_ERROR = 400

interface SignInApi {
    @POST("api/account/login/")
    suspend fun signIn(@Body params: AuthenticationRequestParams): Response<ResponseBody>
}
