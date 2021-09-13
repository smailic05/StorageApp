package com.example.storageapp

import android.app.Application
import androidx.room.Room
import com.example.storageapp.room.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationStorageApp: Application()