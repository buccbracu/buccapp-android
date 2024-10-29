package com.buccbracu.bucc.components

import android.content.Context
import androidx.core.app.NotificationCompat
import com.buccbracu.bucc.R
import com.buccbracu.bucc.application.NotificationMan

fun createNotification(context: Context){
    val notificationManager = NotificationMan.notificationManager
    val notification = NotificationCompat
        .Builder(context, "bucc_notification")
        .setContentTitle("BRAC University Computer Club")
        .setContentText("Demo notification")
        .setSmallIcon(R.drawable.bucc)
        .build()
    notificationManager.notify(2024, notification)

}