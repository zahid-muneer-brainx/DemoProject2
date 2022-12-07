package com.example.demoproject2.application

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        lateinit var appInstance: MyApplication
        fun getAppContext(): Context = appInstance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

}