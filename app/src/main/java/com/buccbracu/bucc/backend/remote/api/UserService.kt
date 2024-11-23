package com.buccbracu.bucc.backend.remote.api

import com.buccbracu.bucc.backend.remote.SIGN_IN
import com.buccbracu.bucc.backend.remote.USER_PROFILE
import com.buccbracu.bucc.backend.remote.models.MemberResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    // Get CSRF Token
    @GET(USER_PROFILE)
    fun getUserProfile(@Header("Cookie") cookie: String ): Call<MemberResponse>

    // Sign In with Credentials (ResponseBody for flexibility)
    @FormUrlEncoded
    @POST(SIGN_IN)
    fun signIn(
        @Field("csrfToken") csrfToken: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseBody>
}