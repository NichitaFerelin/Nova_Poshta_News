package com.endava.budgetplanner.data.repositories

interface LoggedUserRepository {
    suspend fun setToken(authToken: String, tokenExpStamp: String = "")
    suspend fun getLoggedInStatus(): Boolean
}
