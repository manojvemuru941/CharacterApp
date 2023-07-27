package com.manoj.rnm.universe

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        private lateinit var application: Application
        fun getContext(): Context {
            return application.applicationContext
        }
    }
}