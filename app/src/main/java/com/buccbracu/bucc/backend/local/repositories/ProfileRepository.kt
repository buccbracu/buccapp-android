package com.buccbracu.bucc.backend.local.repositories

import com.buccbracu.bucc.backend.local.models.Profile.Profile
import com.buccbracu.bucc.backend.local.models.Profile.ProfileSocial
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.backend.remote.models.MemberSocials
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.internal.interop.Link
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val realm: Realm
) {
    suspend fun saveProfile(profile: Member) {
        realm.write {
            val profileData = Profile().apply {
                objectid = 1
                _id = profile._id
                name = profile.name
                studentId = profile.studentId
                email = profile.email
                buccDepartment = profile.buccDepartment
                designation = profile.designation
                personalEmail = profile.personalEmail
                contactNumber = profile.contactNumber
                joinedBracu = profile.joinedBracu
                departmentBracu = profile.departmentBracu
                profileImage = profile.profileImage
                birthDate = profile.birthDate
                bloodGroup = profile.bloodGroup
                gender = profile.gender
                emergencyContact = profile.emergencyContact
                joinedBucc = profile.joinedBucc
                lastPromotion = profile.lastPromotion
                memberStatus = profile.memberStatus
                memberSkills.addAll(profile.memberSkills)
                // Convert the MemberSocials object to a RealmList of ProfileSocial
                memberSocials = ProfileSocial().apply {
                    Facebook = profile.memberSocials.Facebook
                    Github = profile.memberSocials.Github
                    LinkedIn = profile.memberSocials.LinkedIn
                }
            }
            copyToRealm(profileData, updatePolicy = UpdatePolicy.ALL)
        }
        println("Profile saved to Realm.")
    }

    suspend fun createEmptyProfile(profile: Member): Profile {
        val profileData = Profile().apply {
            objectid = 1
        }
        realm.write {
            copyToRealm(profileData, updatePolicy = UpdatePolicy.ALL)
        }
        println("Profile saved to Realm.")
        return profileData
    }

    suspend fun getProfile(): Profile? {
        return realm.write {
            query<Profile>().first().find()
        }
    }

    suspend fun deleteProfile() {
        realm.write {
            val profiles = query<Profile>().find()
            delete(profiles)
        }
        println("Profile deleted from Realm.")
    }

    fun getAllProfiles(): Flow<List<Profile>> {
        return realm
            .query<Profile>()
            .asFlow()
            .map { results ->
                results.list.toList()
            }
    }

}


