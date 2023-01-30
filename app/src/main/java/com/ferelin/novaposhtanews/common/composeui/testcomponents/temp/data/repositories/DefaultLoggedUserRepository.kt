package com.endava.budgetplanner.data.repositories

import com.endava.budgetplanner.copy
import com.endava.budgetplanner.data.datastore.source.UserPreferencesLocalSourceImpl
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DefaultLoggedUserRepository @Inject constructor(
    private val localSettingsDataSource: UserPreferencesLocalSourceImpl
) : LoggedUserRepository {

    override suspend fun setToken(authToken: String, tokenExpStamp: String) {
        var userSettings = localSettingsDataSource.userPreferences.first()

        userSettings = userSettings.copy {
            this.authToken = authToken
            this.tokenExpStamp = tokenExpStamp
        }

        localSettingsDataSource.updateUserPreferences(userSettings)
    }

    override suspend fun getLoggedInStatus(): Boolean {
        val userSettings = localSettingsDataSource.userPreferences.first()
        return userSettings.authToken.isNotEmpty()
    }
}
