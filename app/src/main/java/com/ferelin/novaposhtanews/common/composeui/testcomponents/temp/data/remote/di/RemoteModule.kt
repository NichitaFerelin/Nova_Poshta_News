package com.endava.budgetplanner.data.remote.di

import com.endava.budgetplanner.BuildConfig
import com.endava.budgetplanner.data.remote.api.SignInApi
import com.endava.budgetplanner.data.remote.api.SignUpApi
import com.endava.budgetplanner.data.remote.interceptor.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RemoteModule {

    @Provides
    fun providesOkHttpClient(
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BASIC)
                }
            )
            .addInterceptor(authorizationInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesSignInApi(retrofit: Retrofit): SignInApi {
        return retrofit.create(SignInApi::class.java)
    }

    @Provides
    fun provideSignUpApi(retrofit: Retrofit): SignUpApi {
        return retrofit.create(SignUpApi::class.java)
    }
}
