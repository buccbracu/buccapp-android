package com.buccbracu.bucc.backend.local.repositories

import com.buccbracu.bucc.backend.local.models.User.Profile
import com.buccbracu.bucc.backend.local.models.User.ProfileSocial
import com.buccbracu.bucc.backend.local.models.emptyProfile
import com.buccbracu.bucc.backend.remote.api.UserService
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.backend.remote.models.MemberResponse
import com.buccbracu.bucc.backend.remote.models.PatchMember
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val realm: Realm,
    private val User: UserService
) {

    suspend fun saveProfile(user: Member) {
        realm.write {
            val userData = Profile().apply {
                objectid = 1
                _id = user._id
                name = user.name
                studentId = user.studentId
                email = user.email
                buccDepartment = user.buccDepartment
                designation = user.designation
                personalEmail = user.personalEmail
                contactNumber = user.contactNumber
                joinedBracu = user.joinedBracu
                departmentBracu = user.departmentBracu
                profileImage = user.profileImage
                birthDate = user.birthDate
                bloodGroup = user.bloodGroup
                gender = user.gender
                emergencyContact = user.emergencyContact
                joinedBucc = user.joinedBucc
                lastPromotion = user.lastPromotion
                memberStatus = user.memberStatus
                memberSkills.addAll(user.memberSkills)
                // Convert the MemberSocials object to a RealmList of ProfileSocial
                memberSocials = ProfileSocial().apply {
                    Facebook = user.memberSocials.Facebook
                    Github = user.memberSocials.Github
                    LinkedIn = user.memberSocials.Linkedin
                }
            }
            copyToRealm(userData, updatePolicy = UpdatePolicy.ALL)
        }
        println("Profile saved to Realm.")
    }

    suspend fun updateProfile(profileData: PatchMember, cookie: String): MemberResponse? {
        val response = User.updateUserProfile(
            cookie = cookie,
            user = profileData,
        ).awaitResponse()
        return response.body()
    }


    suspend fun getRemoteProfileAndSave(cookie: String){
        val response = User.getUserProfile(cookie).awaitResponse()
        val body = response.body()
        body?.let {
            println("REMOTE PROFILE ${body.user.name}")
            saveProfile(body.user)
        }

    }

    suspend fun createEmptyProfile(){
        realm.write {
            deleteAll()

            val emptyProfile = Profile().apply {
                objectid = 1
            }
            copyToRealm(emptyProfile, updatePolicy = UpdatePolicy.ALL)
        }
        println("Profile has been reset.")
    }


    fun getProfile(): Flow<Profile?> {
        return realm.
        query<Profile>("objectid = 1")
            .first()
            .asFlow()
            .map { it.obj }
    }

    suspend fun deleteProfile() {
        realm.write {
            val users = query<Profile>().find()
            delete(users)
        }
    }


}


