package com.buccbracu.bucc.backend.local.models.User

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Profile : RealmObject {
    @PrimaryKey var objectid: Int = 1
    var _id: String = ""
    var name: String = ""
    var studentId: String = ""
    var email: String = ""
    var buccDepartment: String = ""
    var designation: String = ""
    var personalEmail: String = ""
    var contactNumber: String = ""
    var joinedBracu: String = ""
    var departmentBracu: String = ""
    var profileImage: String = ""
    var birthDate: String = ""
    var bloodGroup: String = ""
    var gender: String = ""
    var emergencyContact: String = ""
    var joinedBucc: String = ""
    var lastPromotion: String = ""
    var memberStatus: String = ""
    var memberSkills: RealmList<String> = realmListOf()
    var memberSocials: ProfileSocial? = ProfileSocial()
}

