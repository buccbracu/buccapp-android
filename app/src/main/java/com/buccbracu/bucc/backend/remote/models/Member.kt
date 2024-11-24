package com.buccbracu.bucc.backend.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MemberResponse(
    val user: Member
)

data class MembersResponse(
    val users: List<Member>
)

data class Member(
    var _id: String,
    var name: String,
    var studentId: String,
    var email: String,
    var buccDepartment: String,
    var designation: String,
    var personalEmail: String,
    var contactNumber: String,
    var joinedBracu: String,
    var departmentBracu: String,
    var profileImage: String,
    var birthDate: String,
    var bloodGroup: String,
    var gender: String,
    var emergencyContact: String,
    var joinedBucc: String,
    var lastPromotion: String,
    var memberStatus: String,
    var memberSkills: List<String>,
    var memberSocials: MemberSocials
)

data class PatchMember(
    var personalEmail: String,
    var contactNumber: String,
    var profileImage: String,
    var birthDate: String,
    var bloodGroup: String,
    var gender: String,
    var emergencyContact: String,
    var memberSkills: List<String>,
    var memberSocials: MemberSocials
)

data class MemberSocials(
    var Facebook: String = "",
    var Linkedin: String = "",
    var Github: String = ""

)
