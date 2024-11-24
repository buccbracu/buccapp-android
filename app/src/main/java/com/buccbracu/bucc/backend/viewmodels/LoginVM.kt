package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.backend.local.repositories.SessionRepository
import com.buccbracu.bucc.backend.local.repositories.SharedRepository
import com.buccbracu.bucc.backend.local.repositories.UserRepository
import com.buccbracu.bucc.backend.remote.TOKEN_KEY
import com.buccbracu.bucc.backend.remote.api.AuthService
import com.buccbracu.bucc.backend.remote.models.User
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Document
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
open class LoginVM @Inject constructor(
    private val sessionR: SessionRepository,
    private val sharedR: SharedRepository,
    private val userR: UserRepository,
    private val cookieMap: Map<String, String>,
    private val Auth: AuthService
    ): ViewModel() {


    val session = sharedR.session

    suspend fun createEmptySession(){
        viewModelScope.launch {
            sessionR.createEmptySession()
        }
    }

    private val signInError = "Sign in failed. Check the details you provided are correct."
    private fun signInSuccess(html: String): Boolean{
        val doc: Document = Ksoup.parse(html = html)
        val paragraphs = doc.select("p").text()
        return !paragraphs.contains(signInError)
    }
    fun login(email: String, password: String, loginStatus: (Boolean, User) -> Unit) {
        val emptyUser = User("", "", "", "", "", "")
        viewModelScope.launch {
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
                            val cookie = cookieMap[TOKEN_KEY]!!.trim()
                            sessionR.createSession(
                                session = sessionData,
                                token = cookie
                            )
                            userR.getRemoteProfileAndSave(cookie)
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
        }
    }

    fun logout(){
        viewModelScope.launch {

        }
    }


}