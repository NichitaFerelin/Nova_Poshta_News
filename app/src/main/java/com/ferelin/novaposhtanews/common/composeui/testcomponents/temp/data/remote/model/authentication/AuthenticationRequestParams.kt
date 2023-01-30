package com.endava.budgetplanner.data.remote.model.authentication

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationRequestParams(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)
