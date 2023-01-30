package com.endava.budgetplanner.features.authentication.login.di

import com.endava.budgetplanner.data.repositories.AuthRepository
import com.endava.budgetplanner.data.repositories.DefaultLoggedUserRepository
import com.endava.budgetplanner.data.repositories.DefaultLoginAuthRepository
import com.endava.budgetplanner.data.repositories.LoggedUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginRepositoryModule {
    @Binds
    abstract fun bindLoginDataRepository(
        loginAuthRepository: DefaultLoginAuthRepository
    ): AuthRepository

    @Binds
    abstract fun bindLoggedUserRepository(
        defaultLoggedUserRepository: DefaultLoggedUserRepository
    ): LoggedUserRepository
}
