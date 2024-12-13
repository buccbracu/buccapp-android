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

}
