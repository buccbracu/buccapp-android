package com.buccbracu.bucc.application

import android.app.Application
import com.buccbracu.bucc.application.Retrofit.RetrofitServer
import com.buccbracu.bucc.backend.remote.firebase.FirebaseMS
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BUCCApp : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalServer.initializeRealm()
        NotificationMan.initializeNotificationChannel(this)
        RetrofitServer.initializeRetrofit()
        initializeFirebase()

    }

    private fun initializeFirebase() {
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this)
        }
    }
}