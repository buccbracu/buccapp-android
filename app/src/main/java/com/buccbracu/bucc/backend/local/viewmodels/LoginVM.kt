package com.buccbracu.bucc.backend.local.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.backend.local.repositories.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class LoginVM @Inject constructor(
    private val sessionR: SessionRepository
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

}