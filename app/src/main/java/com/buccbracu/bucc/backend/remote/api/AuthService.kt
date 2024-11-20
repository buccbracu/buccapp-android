package com.buccbracu.bucc.backend.remote.api

import com.buccbracu.bucc.backend.remote.BASE_URL
import com.buccbracu.bucc.backend.remote.SESSION
import com.buccbracu.bucc.backend.remote.SIGN_IN
import com.buccbracu.bucc.backend.remote.SIGN_OUT
import com.buccbracu.bucc.backend.remote.TOKEN
import com.buccbracu.bucc.backend.remote.models.CsrfTokenResponse
import com.buccbracu.bucc.backend.remote.models.SessionResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface AuthService {

    // Get CSRF Token
    @GET(TOKEN)
    fun getCsrfToken(): Call<CsrfTokenResponse>

    // Sign In with Credentials (ResponseBody for flexibility)
    @FormUrlEncoded
    @POST(SIGN_IN)
    fun signIn(
        @Field("csrfToken") csrfToken: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    // Get User Session
    @GET(SESSION)
    fun getSession(): Call<SessionResponse>

    // Sign Out
    @FormUrlEncoded
    @POST(SIGN_OUT)
    fun signOut(
        @Field("csrfToken") csrfToken: String,
        @Field("callbackUrl") callbackUrl: String = BASE_URL
    ): Call<ResponseBody>
}
