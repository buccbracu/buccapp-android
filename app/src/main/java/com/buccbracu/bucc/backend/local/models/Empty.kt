package com.buccbracu.bucc.backend.local.models

import com.buccbracu.bucc.backend.local.models.User.Profile
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.backend.remote.models.MemberSocials

val emptySession = Session().apply {
    objectid = 1
}

val emptyProfile = Profile().apply {
    objectid = 1
}

val emptyMember = Member(
    _id = "",
    name = "",
    studentId = "",
    email = "",
    buccDepartment = "",
    designation = "",
    personalEmail = "",
    contactNumber = "",
    joinedBracu = "",
    departmentBracu = "",
    profileImage = "",
    birthDate = "",
    bloodGroup = "",
    gender = "",
    emergencyContact = "",
    joinedBucc = "",
    lastPromotion = "",
    memberStatus = "",
    memberSkills = emptyList(),
    memberSocials = MemberSocials(
        Facebook = "",
        LinkedIn = "",
        Github = ""
    )
)
