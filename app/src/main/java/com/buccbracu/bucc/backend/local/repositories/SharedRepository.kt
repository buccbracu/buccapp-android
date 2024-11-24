package com.buccbracu.bucc.backend.local.repositories

import com.buccbracu.bucc.backend.local.models.User.Profile
import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.local.models.emptySession
import com.buccbracu.bucc.backend.local.models.emptyProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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


    private val _session: MutableStateFlow<Session?> = MutableStateFlow(emptySession)
    val session: StateFlow<Session?> = _session

    private val _profile: MutableStateFlow<Profile?> = MutableStateFlow(emptyProfile)
    val profile: StateFlow<Profile?> = _profile

    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init{
        fetchAll()
    }

    fun fetchAll(){
        observeSession()
        observeProfile()
    }

    fun printProfile(){
        println("PRINT PROFILE ${_profile.value!!.personalEmail}")
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