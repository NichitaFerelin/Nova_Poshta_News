package com.endava.budgetplanner.data.remote.api

import com.endava.budgetplanner.data.remote.model.authentication.AuthenticationRequestParams
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

const val API_SIGNUP_SUCCESS = 201
const val API_SIGNUP_VALIDATION_ERROR = 400
const val API_SIGNUP_USER_EXIST = 409

interface SignUpApi {
    @POST("api/account/register")
    suspend fun signUp(@Body params: AuthenticationRequestParams): Response<ResponseBody>
}
