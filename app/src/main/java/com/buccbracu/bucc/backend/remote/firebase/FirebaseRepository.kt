package com.buccbracu.bucc.backend.remote.firebase

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class FirebaseRepository @Inject constructor() {

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

    fun unsubscribeFromAllTopics(topics: List<String>) {
        if (topics.isEmpty()) {
            println("No topics to unsubscribe from.")
            return
        }

        topics.forEach { topic ->
            FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        println("Successfully unsubscribed from topic: $topic")
                    } else {
                        println("Failed to unsubscribe from topic: $topic")
                    }
                }
        }
    }


}
