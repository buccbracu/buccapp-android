package com.buccbracu.bucc.backend.remote.firebase

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

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
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    // Function to initialize Firebase (if not initialized in the Application class)
    fun initializeFirebase(application: Application) {
        if (FirebaseApp.getApps(application).isNotEmpty()) {
            FirebaseApp.initializeApp(application)
        }
    }

    // Function to subscribe to a topic
    fun subscribeToTopic(topic: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("Successfully subscribed to topic: $topic")
                } else {
                    println("Failed to subscribe to topic: $topic")
                }
            }
    }
}

