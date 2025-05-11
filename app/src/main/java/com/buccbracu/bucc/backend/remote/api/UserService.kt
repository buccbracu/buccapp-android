package com.buccbracu.bucc.backend.remote.api

import com.buccbracu.bucc.backend.remote.SIGN_IN
import com.buccbracu.bucc.backend.remote.USER_PROFILE
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.backend.remote.models.MemberResponse
import com.buccbracu.bucc.backend.remote.models.PatchMember
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {

    @GET(USER_PROFILE)
    fun getUserProfile(@Header("Cookie") cookie: String ): Call<MemberResponse>

    @PATCH(USER_PROFILE)
    fun updateUserProfile(
        @Header ("Cookie") cookie: String,
        @Body user: PatchMember
    ): Call<MemberResponse>
}