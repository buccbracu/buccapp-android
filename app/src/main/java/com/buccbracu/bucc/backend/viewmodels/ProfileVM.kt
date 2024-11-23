package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import com.buccbracu.bucc.backend.local.repositories.ProfileRepository
import com.buccbracu.bucc.backend.local.repositories.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
open class ProfileVM @Inject constructor(
    private val profileR: ProfileRepository,
): ViewModel() {



}