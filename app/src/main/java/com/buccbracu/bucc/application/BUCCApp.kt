package com.buccbracu.bucc.application

import android.app.Application
import com.buccbracu.bucc.application.Retrofit.RetrofitServer
import dagger.hilt.android.HiltAndroidApp
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging


@HiltAndroidApp
class BUCCApp : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalServer.initializeRealm()
        NotificationMan.initializeNotificationChannel(this)
        RetrofitServer.initializeRetrofit()
        FirebaseApp.initializeApp(this)
    }
}