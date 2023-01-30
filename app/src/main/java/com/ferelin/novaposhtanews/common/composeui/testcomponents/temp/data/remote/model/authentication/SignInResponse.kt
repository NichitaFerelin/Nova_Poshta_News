package com.endava.budgetplanner.data.remote.model.authentication

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class SignInResponse {
    @JsonClass(generateAdapter = true)
    data class Success(
        @Json(name = "token") val accessToken: String,
        @Json(name = "expirationDate") val expiredDate: String
    ) : SignInResponse()

    data class InvalidCredentialsError(val error: String) : SignInResponse()
    data class UnknownError(val exception: Exception? = null) : SignInResponse()
    object UnknownBodyError : SignInResponse()
}
