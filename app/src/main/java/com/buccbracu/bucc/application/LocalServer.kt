package com.buccbracu.bucc.application

import com.buccbracu.bucc.backend.local.models.Session
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object LocalServer {
    lateinit var realm: Realm
        private set

    fun initializeRealm() {
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(Session::class) // register models here
            )
        )
    }
}