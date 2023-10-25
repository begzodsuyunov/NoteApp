package com.example.noteapp.app

import android.app.Application
import com.example.noteapp.BuildConfig
import com.example.noteapp.data.local.AppDatabase
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        AppDatabase.init(this)
    }
}