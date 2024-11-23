package com.buccbracu.bucc.backend.local.repositories

import com.buccbracu.bucc.backend.local.models.User.User
import com.buccbracu.bucc.backend.local.models.Session
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedRepository @Inject constructor(
    private val sessionR: SessionRepository,
    private val profileR: UserRepository,
) {

    private val emptySession = Session().apply {
        objectid = 1
    }
    private val emptyUser = User().apply {
        objectid = 1
    }
    private val _session: MutableStateFlow<Session?> = MutableStateFlow(emptySession)
    val session: StateFlow<Session?> = _session

    private val _profile: MutableStateFlow<User?> = MutableStateFlow(emptyUser)
    val profile: StateFlow<User?> = _profile

    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init{
        observeSession()
        observeProfile()
    }

    private fun observeSession() {
        sessionR.getSession()
            .onEach { _session.value = it } // Update StateFlow with new session data
            .launchIn(repositoryScope) // Keep this running as a background observer
    }

    private fun observeProfile() {
        profileR.getProfile()
            .onEach { _profile.value = it } // Update StateFlow with new profile data
            .launchIn(repositoryScope) // Keep this running as a background observer
    }

}