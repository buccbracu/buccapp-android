package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.backend.local.models.Profile.Profile
import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.local.repositories.SessionRepository
import com.buccbracu.bucc.backend.remote.TOKEN_KEY
import com.buccbracu.bucc.backend.remote.api.AuthService
import com.buccbracu.bucc.backend.remote.models.User
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Document
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
open class LoginVM @Inject constructor(
    private val sessionR: SessionRepository,
    private val cookieMap: Map<String, String>,
    private val Auth: AuthService
    ): ViewModel() {

    val emptySession = Session().apply {
        objectid = 1
    }

    private val _session = MutableStateFlow<Session?>(emptySession)

        init{
            viewModelScope.launch {
                fetchSession()
            }

        }


    val session: StateFlow<Session?> get() = _session

    val allSessions = sessionR.getAllSession()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    private fun fetchSession() {
        viewModelScope.launch {
            val sessionData = sessionR.getSession()
            if(sessionData == null) sessionR.createEmptySession(emptySession)
            _session.value = sessionData ?: emptySession
        }
    }


    private val signInError = "Sign in failed. Check the details you provided are correct."
    private fun signInSuccess(html: String): Boolean{
        val doc: Document = Ksoup.parse(html = html)
        val paragraphs = doc.select("p").text()
        return !paragraphs.contains(signInError)
    }
    suspend fun remoteLogin(email: String, password: String, loginStatus: (Boolean, User) -> Unit) {
        val emptyUser = User("", "", "", "", "", "")
        viewModelScope.launch {
            try {
                val csrf = Auth.getCsrfToken().awaitResponse()
                csrf.body()?.let {
                    val token = it.csrfToken
                    val signIn = Auth.signIn(token, email.trim(), password.trim()).awaitResponse()
                    val signInResult = signInSuccess(signIn.body()!!.string())
                    if (signInResult) {
                        val session = Auth.getSession().awaitResponse()
                        if (session.isSuccessful) {
                            session.body()?.let { sessionData ->
                                println("Sign in Successful")
                                sessionR.createSession(
                                    session = sessionData,
                                    token = cookieMap[TOKEN_KEY]!!.trim()
                                )
                                loginStatus(true, sessionData.user)
                            }
                        } else {
                            println("Session error: ${session.errorBody()}")
                            loginStatus(false, emptyUser)
                        }
                    } else {
                        println("Sign In failed: $signInError")
                        loginStatus(false, emptyUser)
                    }
                }
            } catch (e: Exception) {
                println("Error during login: ${e.message}")
                loginStatus(false, emptyUser)
            }
        }
    }



}