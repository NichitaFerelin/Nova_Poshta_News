package com.endava.budgetplanner.data.remote.model.authentication

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class SignUpResponse {
    object Success : SignUpResponse()
    object UnknownBodyError : SignUpResponse()
    object UserAlreadyExist : SignUpResponse()
    data class UnknownError(val exception: Exception? = null) : SignUpResponse()

    @JsonClass(generateAdapter = true)
    data class ValidationError(
        @Json(name = "type") val type: String,
        @Json(name = "title") val title: String,
        @Json(name = "status") val status: Int,
        @Json(name = "traceId") val traceId: String,
        @Json(name = "errors") val errors: SignUpErrors
    ) : SignUpResponse()
}

@JsonClass(generateAdapter = true)
data class SignUpErrors(
    @Json(name = "Email") val email: List<String>?,
    @Json(name = "Password") val password: List<String>?
)
