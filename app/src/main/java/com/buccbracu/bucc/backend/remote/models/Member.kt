package com.buccbracu.bucc.backend.remote.models

data class Member(
    val _id: String,
    val name: String,
    val studentId: String,
    val email: String,
    val buccDepartment: String,
    val designation: String,
    val personalEmail: String,
    val contactNumber: String,
    val joinedBracu: String,
    val departmentBracu: String,
    val profileImage: String,
    val birthDate: String,
    val bloodGroup: String,
    val gender: String,
    val emergencyContact: String,
    val joinedBucc: String,
    val lastPromotion: String,
    val memberStatus: String,
    val memberSkills: List<String>,
    val memberSocials: MemberSocials
)

data class MemberSocials(
    val Facebook: String ="",
    val LinkedIn: String ="",
    val Github: String =""

)
