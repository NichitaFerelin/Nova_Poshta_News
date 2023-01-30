package com.endava.budgetplanner.data.repositories

import com.endava.budgetplanner.data.remote.model.authentication.SignInResponse

interface AuthRepository {
    suspend fun getCredentialsInfo(login: String, password: String): SignInResponse
}
