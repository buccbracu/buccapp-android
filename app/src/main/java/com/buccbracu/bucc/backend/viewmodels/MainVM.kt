package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import com.buccbracu.bucc.backend.local.repositories.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class MainVM @Inject constructor(
    private val sessionR: SessionRepository,
    private val cookieMap: Map<String, String>,
): ViewModel() {


}