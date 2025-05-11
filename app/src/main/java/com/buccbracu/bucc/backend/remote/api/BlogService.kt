package com.buccbracu.bucc.backend.remote.api

import com.buccbracu.bucc.backend.remote.BLOG
import com.buccbracu.bucc.backend.remote.BLOG_PUBLIC
import com.buccbracu.bucc.backend.remote.models.Blog

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface BlogService {

    @GET(BLOG_PUBLIC)
    fun getBlogs(
        @Header("Cookie") cookie: String,
    ): Call<List<Blog>>

    @GET("$BLOG/{id}")
    fun getBlog(
        @Header("Cookie") cookie: String,
        @Path("id") id: String
    ): Call<Blog>
}