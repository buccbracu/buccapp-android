package com.buccbracu.bucc.backend.local.repositories

import com.buccbracu.bucc.backend.local.models.Session
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val realm: Realm
) {

    suspend fun createSession(
        emailData: String,
        passwordData: String,
        token: String
    ) {
        realm.write {
            val sessionData = Session().apply {
                objectid = 1
                email = emailData
                password = passwordData
                expires = getCookieExpiry(token)
                authJsToken = token
            }
            copyToRealm(sessionData, updatePolicy = UpdatePolicy.ALL)
        }
    }
    private fun getCookieExpiry(cookieString: String): String {
        val parts = cookieString.split(";")
        return parts[1]
    }

    suspend fun createEmptySession() {
        realm.write {
            deleteAll()
            val sessionData = Session().apply {
                objectid = 1
            }
            copyToRealm(sessionData, updatePolicy = UpdatePolicy.ALL)
        }
    }

    suspend fun deleteSession() {
        realm.write {
            val sessions = query<Session>().find() // Find all Session objects
            delete(sessions) // Delete all found Session objects
        }
    }


    fun getAllSession() : Flow<List<Session>> {
        return realm
            .query<Session>()
            .asFlow()
            .map { results ->
                results.list.toList()
            }
    }
    fun getSession(): Flow<Session?> {
        return realm.
        query<Session>("objectid = 1")
            .first()
            .asFlow()
            .map { it.obj }
    }


    suspend fun getAllSessionSnapshot() : List<Session> {
        return realm
            .query<Session>()
            .asFlow()
            .map { results ->
                results.list.toList()
            }
            .first()
    }

}