package com.buccbracu.bucc.application

import android.app.Application
import com.buccbracu.bucc.application.Retrofit.RetrofitServer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BUCCApp : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalServer.initializeRealm()
        NotificationMan.initializeNotificationChannel(this)
        RetrofitServer.initializeRetrofit()
    }
}

// Singleton object to manage Realm database


// Singleton object to manage Notifications
