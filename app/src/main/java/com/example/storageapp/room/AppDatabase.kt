package com.example.storageapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Dog::class), version = 1)
abstract  class AppDatabase: RoomDatabase() {
    abstract fun repositoriesDao(): DatabaseDao
}