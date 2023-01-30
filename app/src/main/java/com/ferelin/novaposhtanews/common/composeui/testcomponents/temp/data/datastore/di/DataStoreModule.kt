package com.endava.budgetplanner.data.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.endava.budgetplanner.UserPreferences
import com.endava.budgetplanner.data.datastore.UserPreferencesSerializer
import com.endava.budgetplanner.data.dispatchers.AppDispatchers
import com.endava.budgetplanner.data.dispatchers.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private const val DATA_STORE_FILE_NAME = "user_prefs.pb"

@InstallIn(SingletonComponent::class)
@Module
object DatastoreModule {

    @Singleton
    @Provides
    fun provideProtoDataStore(
        @ApplicationContext appContext: Context,
        @Dispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher
    ): DataStore<UserPreferences> {
        return DataStoreFactory.create(
            serializer = UserPreferencesSerializer,
            produceFile = { appContext.dataStoreFile(DATA_STORE_FILE_NAME) },
            corruptionHandler = null,
            scope = CoroutineScope(ioDispatcher + SupervisorJob())
        )
    }
}
