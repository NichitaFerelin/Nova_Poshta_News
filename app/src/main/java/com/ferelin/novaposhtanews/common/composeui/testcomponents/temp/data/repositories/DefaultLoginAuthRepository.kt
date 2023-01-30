package com.endava.budgetplanner.data.repositories

import com.endava.budgetplanner.data.dispatchers.AppDispatchers
import com.endava.budgetplanner.data.dispatchers.Dispatcher
import com.endava.budgetplanner.data.remote.model.authentication.AuthenticationRequestParams
import com.endava.budgetplanner.data.remote.model.authentication.SignInResponse
import com.endava.budgetplanner.data.remote.source.SignInRemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultLoginAuthRepository @Inject constructor(
    private val remoteAuthApiDataSource: SignInRemoteSource,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {

    override suspend fun getCredentialsInfo(
        login: String,
        password: String
    ): SignInResponse =
        withContext(ioDispatcher) {
            remoteAuthApiDataSource.signIn(AuthenticationRequestParams(login, password))
        }
}
