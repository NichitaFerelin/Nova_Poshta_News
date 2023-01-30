package com.endava.budgetplanner.data.remote.di

import com.endava.budgetplanner.data.remote.model.authentication.SignInResponse
import com.endava.budgetplanner.data.remote.model.authentication.SignUpResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class MoshiModule {

    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun providesMoshiSignUpValidationErrorAdapter(
        moshi: Moshi
    ): JsonAdapter<SignUpResponse.ValidationError> {
        return moshi.adapter(SignUpResponse.ValidationError::class.java)
    }

    @Provides
    fun providesMoshiSignInSuccessAdapter(
        moshi: Moshi
    ): JsonAdapter<SignInResponse.Success> {
        return moshi.adapter(SignInResponse.Success::class.java)
    }
}
