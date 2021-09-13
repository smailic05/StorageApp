package com.example.storageapp.di

import android.content.Context
import androidx.room.Room
import com.example.storageapp.room.AppDatabase
import com.example.storageapp.room.DatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideDao(database: AppDatabase): DatabaseDao {
        return database.repositoriesDao()
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "food.db"
        ).build()
    }
}