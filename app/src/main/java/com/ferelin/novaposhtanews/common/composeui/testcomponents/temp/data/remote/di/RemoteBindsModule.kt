package com.endava.budgetplanner.data.remote.di

import com.endava.budgetplanner.data.remote.source.SignInRemoteSource
import com.endava.budgetplanner.data.remote.source.SignInRemoteSourceImpl
import com.endava.budgetplanner.data.remote.source.SignUpRemoteSource
import com.endava.budgetplanner.data.remote.source.SignUpRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteBindsModule {
    @Binds
    fun bindsSignInRemoteSource(impl: SignInRemoteSourceImpl): SignInRemoteSource

    @Binds
    fun bindsSignUpRemoteSource(impl: SignUpRemoteSourceImpl): SignUpRemoteSource
}
