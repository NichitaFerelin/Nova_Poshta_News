package com.endava.budgetplanner.data.database.di

import android.content.Context
import androidx.room.Room
import com.endava.budgetplanner.data.database.BudgetDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "BudgetPlanner"

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): BudgetDatabase {
        return Room.databaseBuilder(
            appContext,
            BudgetDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}
