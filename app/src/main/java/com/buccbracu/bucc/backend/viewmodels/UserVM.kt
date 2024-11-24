package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.backend.local.models.User.Profile
import com.buccbracu.bucc.backend.local.repositories.SharedRepository
import com.buccbracu.bucc.backend.local.repositories.UserRepository
import com.buccbracu.bucc.backend.remote.api.UserService
import com.buccbracu.bucc.backend.remote.models.MemberSocials
import com.buccbracu.bucc.backend.remote.models.PatchMember
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class UserVM @Inject constructor(
    private val userR: UserRepository,
    private val sharedR: SharedRepository,
    private val User: UserService
): ViewModel() {

    val profileData = sharedR.profile
    val session = sharedR.session

    fun patchMemberFromProfile(profile: Profile): PatchMember {
        return PatchMember(
            personalEmail = profile.personalEmail,
            contactNumber = profile.contactNumber,
            profileImage = profile.profileImage,
            birthDate = profile.birthDate,
            bloodGroup = profile.bloodGroup,
            gender = profile.gender,
            emergencyContact = profile.emergencyContact,
            memberSkills = profile.memberSkills.toList(),
            memberSocials = MemberSocials(
                Github = profile.memberSocials!!.Github,
                Facebook = profile.memberSocials!!.Facebook,
                Linkedin = profile.memberSocials!!.LinkedIn
            )
        )
    }

    fun updateUserProfile(
        profileData: PatchMember,
        setLoading: (Boolean) -> Unit
    ){
        viewModelScope.launch {
            session.value?.let {
                val cookie = session.value!!.authJsToken
                val response = userR.updateProfile(
                    profileData = profileData,
                    cookie = cookie
                )
                response?.let {
                    val user = response.user
                    userR.saveProfile(user)
                    sharedR.fetchAll()
                    println("User profile updated")
                    sharedR.printProfile()
                }
                setLoading(false)

            }
        }
    }

    fun refreshProfile(){
        viewModelScope.launch {
            session.value?.let {
                userR.getRemoteProfileAndSave(session.value!!.authJsToken)
            }
        }
    }





}