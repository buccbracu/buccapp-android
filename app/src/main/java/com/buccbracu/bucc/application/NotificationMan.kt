package com.buccbracu.bucc.application

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

object NotificationMan {

    lateinit var notificationManager: NotificationManager
        private set

    fun initializeNotificationChannel(context: Context) {
        val notificationChannel = NotificationChannel(
            "bucc_notification",
            "bucc",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "BRAC University Computer Club"
            enableVibration(true)
            enableLights(true)
        }

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}
