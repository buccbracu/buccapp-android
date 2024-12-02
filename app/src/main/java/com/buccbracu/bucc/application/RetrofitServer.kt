package com.buccbracu.bucc.application

import com.buccbracu.bucc.backend.adapters.MemberAdapter
import com.buccbracu.bucc.backend.remote.BASE_URL
import com.buccbracu.bucc.backend.remote.TOKEN_KEY
import com.buccbracu.bucc.backend.remote.api.AuthService
import com.buccbracu.bucc.backend.remote.api.BlogService
import com.buccbracu.bucc.backend.remote.api.DeptMemberService
import com.buccbracu.bucc.backend.remote.api.UserService

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitServer {

    lateinit var retrofit: Retrofit
    private lateinit var moshi: Moshi
    lateinit var cookieMap: MutableMap<String, String>
    lateinit var Auth: AuthService
    lateinit var User: UserService
    lateinit var DeptMember: DeptMemberService
    lateinit var Blog: BlogService
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
                       println("${cookies.size} Cookie $value")
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
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .cookieJar(cookieJar)
//            .addInterceptor(loggingInterceptor)       // use if need logs. else no.
            .build()
        moshi = Moshi.Builder()
            .add(MemberAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .baseUrl(BASE_URL)
            .build()


        Auth = retrofit.create(AuthService::class.java)
        User = retrofit.create(UserService::class.java)
        DeptMember = retrofit.create(DeptMemberService::class.java)
        Blog = retrofit.create(BlogService::class.java)
    }

}
