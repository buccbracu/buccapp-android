package com.buccbracu.bucc.backend.remote.models

data class CsrfTokenResponse(
    val csrfToken: String
)

data class SessionResponse(
    val user: User,
    val expires: String
)

data class User(
    val name: String,
    val email: String,
    val image: String?,
    val id: String,
    val designation: String,
    val buccDepartment: String
)

data class ResetPassword(
    val email: String? = null,
    val message: String? = null
)