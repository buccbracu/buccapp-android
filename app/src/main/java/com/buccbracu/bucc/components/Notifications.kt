package com.buccbracu.bucc.components

import android.content.Context
import androidx.core.app.NotificationCompat
import com.buccbracu.bucc.R
import com.buccbracu.bucc.application.NotificationMan

fun createNotification(
    context: Context,
    title: String,
    bodyText: String
){
    val notificationManager = NotificationMan.notificationManager
    val notification = NotificationCompat
        .Builder(context, "bucc_notification")
        .setContentTitle(title)
        .setContentText(bodyText)
        .setSmallIcon(R.drawable.bucc_logo_dark)
        .build()
    notificationManager.notify(2024, notification)

}