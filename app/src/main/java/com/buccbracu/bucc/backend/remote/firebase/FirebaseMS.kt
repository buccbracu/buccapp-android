package com.buccbracu.bucc.backend.remote.firebase

import android.app.Application
import com.buccbracu.bucc.components.createNotification
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMS : FirebaseMessagingService() {

    // This function is triggered when a message is received
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        // Extract notification title and body
        val notificationTitle = message.notification?.title ?: "New Message"
        val notificationBody = message.notification?.body ?: "Message received"

        // You can handle notifications here
        // For example, show a notification or process the data
        println("Notification Title: $notificationTitle")
        println("Notification Body: $notificationBody")
        createNotification(
            this,
            title = notificationTitle,
            bodyText = notificationBody
        )

    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}

