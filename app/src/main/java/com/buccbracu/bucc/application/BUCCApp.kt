package com.buccbracu.bucc.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.buccbracu.bucc.backend.local.models.Session
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

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
