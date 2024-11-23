package com.buccbracu.bucc.backend.local.repositories

import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.local.models.User.User
import com.buccbracu.bucc.backend.local.models.User.UserSocial
import com.buccbracu.bucc.backend.remote.models.Member
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val realm: Realm
) {
    suspend fun saveProfile(user: Member) {
        realm.write {
            val userData = User().apply {
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
                memberSocials = UserSocial().apply {
                    Facebook = user.memberSocials.Facebook
                    Github = user.memberSocials.Github
                    LinkedIn = user.memberSocials.LinkedIn
                }
            }
            copyToRealm(userData, updatePolicy = UpdatePolicy.ALL)
        }
        println("Profile saved to Realm.")
    }

    suspend fun createEmptyProfile(user: User){
        realm.write {
            copyToRealm(user, updatePolicy = UpdatePolicy.ALL)
        }
    }


    fun getProfile(): Flow<User?> {
        return realm.
        query<User>("objectid = 1")
            .first()
            .asFlow()
            .map { it.obj }
    }

    suspend fun deleteProfile() {
        realm.write {
            val users = query<User>().find()
            delete(users)
        }
        println("Profile deleted from Realm.")
    }

    fun getAllProfiles(): Flow<List<User>> {
        return realm
            .query<User>()
            .asFlow()
            .map { results ->
                results.list.toList()
            }
    }

}


