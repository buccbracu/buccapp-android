package com.buccbracu.bucc.backend.remote.api

import com.buccbracu.bucc.backend.remote.MEMBERS
import com.buccbracu.bucc.backend.remote.models.MembersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface DeptMemberService {

    @GET(MEMBERS)
    fun getUserProfile(@Header("Cookie") cookie: String ): Call<MembersResponse>
}