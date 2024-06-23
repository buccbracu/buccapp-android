package com.buccbracu.bucc.backend.local

import android.app.Application
import com.buccbracu.bucc.backend.local.models.Session
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class LocalServer: Application() {
    // register models and store it in a variable for accessing all over the application
    companion object{
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()

        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(     // register models here
                    Session::class,
                )
            )
        )
    }

}