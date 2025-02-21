package com.example.movie.presentation.application

import android.app.Application
import com.example.movie.presentation.di.appModule


import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}