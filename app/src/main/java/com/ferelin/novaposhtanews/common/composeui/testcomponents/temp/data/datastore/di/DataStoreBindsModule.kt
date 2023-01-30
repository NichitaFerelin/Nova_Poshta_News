package com.endava.budgetplanner.data.datastore.di

import com.endava.budgetplanner.data.datastore.source.UserPreferencesLocalSource
import com.endava.budgetplanner.data.datastore.source.UserPreferencesLocalSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataStoreBindsModule {

    @Binds
    fun bindsUserPreferencesLocalSource(
        impl: UserPreferencesLocalSourceImpl
    ): UserPreferencesLocalSource
}
