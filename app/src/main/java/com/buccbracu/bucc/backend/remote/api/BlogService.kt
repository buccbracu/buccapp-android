package com.buccbracu.bucc.backend.remote.api

import com.buccbracu.bucc.backend.remote.BLOG
import com.buccbracu.bucc.backend.remote.models.Blog

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface BlogService {

    @GET(BLOG)
    fun getBlogs(
        @Header("Cookie") cookie: String,
    ): Call<List<Blog>>
}