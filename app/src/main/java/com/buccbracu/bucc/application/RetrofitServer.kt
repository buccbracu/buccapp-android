package com.buccbracu.bucc.application

import com.buccbracu.bucc.backend.remote.BASE_URL
import com.buccbracu.bucc.backend.remote.TOKEN_KEY
import com.buccbracu.bucc.backend.remote.api.AuthService

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitServer {

    lateinit var retrofit: Retrofit
    private lateinit var moshi: Moshi
    lateinit var cookieMap: MutableMap<String, String>
    lateinit var Auth: AuthService
        private set

    fun initializeRetrofit() {
        cookieMap = mutableMapOf()
        val cookieJar = object : CookieJar {
            val cookies = mutableListOf<Cookie>()


            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                return cookies.filter { it.matches(url) }
            }

            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                this.cookies.addAll(cookies)
                cookies.filter { it.name == TOKEN_KEY }.let { filtered ->
                   filtered.forEach{ cookie ->
                       val value = "${cookie.name}=${cookie.value};${cookie.expiresAt};${cookie.domain}"
                       cookieMap[cookie.name] = value
                   }
                }
            }

            fun getCookies(){
                cookies.forEach{
                    println("Cookie: $it")
                }
            }
        }

        val client = OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .build()
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .baseUrl(BASE_URL)
            .build()


        Auth = retrofit.create(AuthService::class.java)
    }

}
