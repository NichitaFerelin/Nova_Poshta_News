package com.endava.budgetplanner.data.datastore.source

import androidx.datastore.core.DataStore
import com.endava.budgetplanner.UserPreferences
import com.endava.budgetplanner.copy
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserPreferencesLocalSource {
    val userPreferences: Flow<UserPreferences>
    suspend fun updateUserPreferences(userPreferences: UserPreferences)
}

class UserPreferencesLocalSourceImpl @Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferences>
) : UserPreferencesLocalSource {

    override val userPreferences: Flow<UserPreferences> = userPreferencesDataStore.data

    override suspend fun updateUserPreferences(userPreferences: UserPreferences) {
        userPreferencesDataStore.updateData {
            it.copy {
                authToken = userPreferences.authToken
                userEmail = userPreferences.userEmail
                tokenExpStamp = userPreferences.tokenExpStamp
            }
        }
    }
}
