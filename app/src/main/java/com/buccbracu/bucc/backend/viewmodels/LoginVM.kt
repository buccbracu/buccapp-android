package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.application.Retrofit.RetrofitCookieJar
import com.buccbracu.bucc.backend.local.repositories.SessionRepository
import com.buccbracu.bucc.backend.local.repositories.SharedRepository
import com.buccbracu.bucc.backend.local.repositories.UserRepository
import com.buccbracu.bucc.backend.remote.TOKEN_KEY
import com.buccbracu.bucc.backend.remote.api.AuthService
import com.buccbracu.bucc.backend.remote.firebase.FirebaseRepository
import com.buccbracu.bucc.backend.remote.firebase.topics
import com.buccbracu.bucc.backend.remote.models.ResetPassword
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
    private val Auth: AuthService,
    private val firebase: FirebaseRepository
    ): ViewModel() {


    val session = sharedR.session
    val profile = sharedR.profile

    suspend fun createEmptySession(){
        viewModelScope.launch {
            sessionR.createEmptySession()
        }
    }

    private fun signInResult(html: String): List<Any> {
        val doc: Document = Ksoup.parse(html)
        val errorDiv = doc.selectFirst("div.error")
        return if (errorDiv != null) {
            val errorMessage = errorDiv.selectFirst("p")?.text() ?: "Unknown error"
            listOf(false, errorMessage)
        } else {
            listOf(true, "Success")
        }
    }

    fun login(
        email: String,
        password: String,
        loginStatus: (Boolean, String) -> Unit = { _, _ -> },
        setLoading: (Boolean) -> Unit = { _ -> }
    ) {
        viewModelScope.launch {
            val csrf = Auth.getCsrfToken().awaitResponse()
            csrf.body()?.let {
                val token = it.csrfToken
                val signIn = Auth.signIn(token, email.trim(), password.trim()).awaitResponse()
                val signInResult = signInResult(signIn.body()!!.string())
                val status = signInResult[0] as Boolean
                val message = signInResult[1] as String
                if (status) {
                    val sessionResponse = Auth.getSession().awaitResponse()
                    val session = sessionResponse.body()
                    session?.let { data ->
                        println("Sign in Successful")
                        val cookie = RetrofitCookieJar.cookieMap[TOKEN_KEY]!!.trim()
                        sessionR.createSession(
                            emailData = email,
                            passwordData = password,
                            token = cookie
                        )
                        userR.getRemoteProfileAndSave(
                            cookie = cookie,
                            setTopics = { topics ->
                                topics.forEach{
                                    firebase.subscribeToTopic(it)
                                }
                            }
                        )

                        loginStatus(true, message)
                    }


                } else {
                    println("Sign In failed: $message")
                    loginStatus(false, message)
                }
                setLoading(false)
            }
        }
    }

    suspend fun logout(onComplete: () -> Unit){
        viewModelScope.launch {
            session.value?.let {
                val response = Auth.signOut(session.value!!.authJsToken).awaitResponse()
                response.body()?.let {
                    RetrofitCookieJar.clearCookies()
                    userR.createEmptyProfile()
                    sessionR.createEmptySession()
                    sharedR.fetchAll()

                    onComplete()
                }
            }
        }
    }
    suspend fun logoutOffline(onComplete: () -> Unit){
        viewModelScope.launch {
            session.value?.let {
                RetrofitCookieJar.clearCookies()
                userR.createEmptyProfile()
                sessionR.createEmptySession()
                sharedR.fetchAll()
                onComplete()
            }
        }
    }


    fun resetPassword(email: String, setMessage: (String) -> Unit){
        viewModelScope.launch {
            session.value?.let {
                val response = Auth.resetPassword(
                    ResetPassword(email= email)
                ).awaitResponse()
                response.body()?.let {
                    val message = response.body()!!.message
                    message?.let {
                        setMessage(message)
                    }
                    println("MESSAGE: $message")

                }
            }
        }
    }

    fun autoLogin(){
        viewModelScope.launch {
            session.value?.let {
                if(session.value!!.email != ""){
                    login(
                        email = session.value!!.email,
                        password = session.value!!.password,
                    )
                }
            }
        }
    }


}