package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.backend.local.repositories.SharedRepository
import com.buccbracu.bucc.backend.local.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class UserVM @Inject constructor(
    private val userR: UserRepository,
    private val sharedR: SharedRepository,
): ViewModel() {



    val profileData = sharedR.profile

    fun fetchProfileData(){
        if (profileData.value!!._id.isNotBlank()){

        }
    }


}