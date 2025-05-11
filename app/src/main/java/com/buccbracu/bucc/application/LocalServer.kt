package com.buccbracu.bucc.application

import com.buccbracu.bucc.backend.local.models.User.Profile
import com.buccbracu.bucc.backend.local.models.User.ProfileSocial
import com.buccbracu.bucc.backend.local.models.Session
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object LocalServer {
    lateinit var realm: Realm
        private set

    fun initializeRealm() {
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    Session::class,
                    Profile::class,
                    ProfileSocial::class
                ) // register models here
            )
        )
    }
}