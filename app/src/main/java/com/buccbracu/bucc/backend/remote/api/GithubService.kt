package com.buccbracu.bucc.backend.remote.api

import com.buccbracu.bucc.backend.remote.CONTRIBUTORS
import com.buccbracu.bucc.backend.remote.GITHUB_TOKEN_BEARER
import com.buccbracu.bucc.backend.remote.models.Contributor
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface GithubService {

    @GET
    fun getDataFromUrl(
        @Url url: String =  CONTRIBUTORS, // Dynamically pass the full URL
        @Header("Authorization") token: String = GITHUB_TOKEN_BEARER // Optional token
    ): Call<List<Contributor>> // Replace Any with your specific data model
}