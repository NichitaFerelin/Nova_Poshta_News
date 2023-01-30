package com.endava.budgetplanner.data.remote.interceptor

import com.endava.budgetplanner.data.datastore.source.UserPreferencesLocalSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val userPreferencesLocalSource: UserPreferencesLocalSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        val originalRequest = chain.request()
        val requestType = originalRequest.tag(AuthorizationType::class.java)

        val newRequest = if (requestType == AuthorizationType.ACCESS_TOKEN) {
            val userPreferences = userPreferencesLocalSource.userPreferences.first()
            chain.request()
                .newBuilder()
                .header("Authorization", "Bearer [${userPreferences.authToken}]")
                .build()
        } else {
            originalRequest
        }
        return@runBlocking chain.proceed(newRequest)
    }
}

enum class AuthorizationType {
    ACCESS_TOKEN
}
