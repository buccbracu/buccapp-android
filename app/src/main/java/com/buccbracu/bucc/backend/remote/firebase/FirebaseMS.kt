package com.buccbracu.bucc.backend.remote.firebase

import android.app.Application
import com.buccbracu.bucc.backend.local.repositories.SharedRepository
import com.buccbracu.bucc.backend.local.repositories.UserRepository
import com.buccbracu.bucc.components.createNotification
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import javax.inject.Inject

class FirebaseMS : FirebaseMessagingService() {

    @Inject
    lateinit var userR: UserRepository

    @Inject
    lateinit var sharedR: SharedRepository

    // This function is triggered when a message is received
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val notificationTitle = message.notification?.title ?: "New Message"
        val notificationBody = message.notification?.body ?: "Message received"
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

