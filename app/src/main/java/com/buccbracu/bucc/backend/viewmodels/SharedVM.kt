package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import com.buccbracu.bucc.backend.local.repositories.SessionRepository
import com.buccbracu.bucc.backend.remote.api.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class SharedVM @Inject constructor(
    private val sessionR: SessionRepository,
    private val cookieMap: Map<String, String>,
    private val Auth: AuthService
): ViewModel() {
}