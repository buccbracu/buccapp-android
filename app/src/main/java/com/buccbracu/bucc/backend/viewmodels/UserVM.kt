package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.backend.local.repositories.SharedRepository
import com.buccbracu.bucc.backend.local.repositories.UserRepository
import com.buccbracu.bucc.backend.remote.api.UserService
import com.buccbracu.bucc.backend.remote.models.Member
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
open class UserVM @Inject constructor(
    private val userR: UserRepository,
    private val sharedR: SharedRepository,
    private val User: UserService
): ViewModel() {

    val profileData = sharedR.profile
    val session = sharedR.session



}