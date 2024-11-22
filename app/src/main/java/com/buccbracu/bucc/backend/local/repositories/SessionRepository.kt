package com.buccbracu.bucc.backend.local.repositories

import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.remote.models.SessionResponse
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


    suspend fun createSession(session: SessionResponse, token: String) {
        realm.write {
            val sessionData = Session().apply {
                name = session.user.name
                email = session.user.email
                image = session.user.image
                id = session.user.id
                designation = session.user.designation
                buccDepartment = session.user.buccDepartment
                expires = session.expires
                authJsToken = token
            }
            copyToRealm(sessionData, updatePolicy = UpdatePolicy.ALL)
        }
        println("DATA WRITTEN TO SESSION")
    }


    fun getAllSession() : Flow<List<Session>> {
        return realm
            .query<Session>()
            .asFlow()
            .map { results ->
                results.list.toList()
            }
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