package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.local.repositories.SessionRepository
import com.buccbracu.bucc.backend.remote.api.AuthService
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Document
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
open class LoginVM @Inject constructor(
    private val sessionR: SessionRepository,
    private val Auth: AuthService
    ): ViewModel() {

    val allSessions = sessionR.getAllSession()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun loginSuccess(
        memberID: String,
        memberName: String,
        memberDesignation: String,
        memberDepartment: String
    ){
        viewModelScope.launch {
            sessionR.createSession(
                listOf(
                    memberID,
                    memberName,
                    memberDesignation,
                    memberDepartment
                )
            )
        }
    }

    fun getSessionSnapshot(setValue: (List<Session>) -> Unit){
        viewModelScope.launch {
            setValue(sessionR.getAllSessionSnapshot())
        }
    }

    private val signInError = "Sign in failed. Check the details you provided are correct."
    private fun signInSuccess(html: String): Boolean{
        val doc: Document = Ksoup.parse(html = html)
        val paragraphs = doc.select("p").text()
        return !paragraphs.contains(signInError)
    }
    fun remoteLogin(){
        viewModelScope.launch {
            val email = "amir.ul.islam@g.bracu.ac.bd"
            val password = ""

            try {
                val csrf = Auth.getCsrfToken().awaitResponse()
                csrf.body()?.let {
                    val token = it.csrfToken
                    val signIn = Auth.signIn(token, email, password).awaitResponse()
                    val signInResult = signInSuccess(signIn.body()!!.string())
                    if (signInResult) {
                        val session = Auth.getSession().awaitResponse()
                        if (session.isSuccessful) {
                            session.body()?.let { sessionData ->
                                println("Sign in Successful")
                                println("User: ${sessionData.user.name}")
                                println("User ID: ${sessionData.user.id}")
                                println("Department: ${sessionData.user.buccDepartment}")
                                println("Designation: ${sessionData.user.designation}")
                                println("Session Expires: ${sessionData.expires}")
                            }
                        } else {
                            println("Session error: ${session.errorBody()}")
                        }
                    } else {
                        println("Sign In failed: $signInError")
                    }
                }
            } catch (e: Exception) {
                println("Error during login: ${e.message}")
            }
        }
    }



}