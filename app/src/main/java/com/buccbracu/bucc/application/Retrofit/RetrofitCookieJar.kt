package com.buccbracu.bucc.application.Retrofit

import com.buccbracu.bucc.backend.remote.TOKEN_KEY
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

object RetrofitCookieJar : CookieJar {
    private val cookies = mutableListOf<Cookie>()
    val cookieMap: MutableMap<String, String> = mutableMapOf()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookies.filter { it.matches(url) }
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        this.cookies.addAll(cookies)
        cookies.filter { it.name == TOKEN_KEY }.forEach { cookie ->
            val value = "${cookie.name}=${cookie.value};${cookie.expiresAt};${cookie.domain}"
            cookieMap[cookie.name] = value
        }
    }

    fun clearCookies() {
        cookies.clear()
        cookieMap.clear()
        println("Cookies cleared.")
    }
}

//cookieMap = mutableMapOf()
//val cookieJar = object : CookieJar {
//    val cookies = mutableListOf<Cookie>()
//
//    override fun loadForRequest(url: HttpUrl): List<Cookie> {
//        return cookies.filter { it.matches(url) }
//    }
//
//    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
//        this.cookies.addAll(cookies)
//        cookies.filter { it.name == TOKEN_KEY }.let { filtered ->
//            filtered.forEach{ cookie ->
//                val value = "${cookie.name}=${cookie.value};${cookie.expiresAt};${cookie.domain}"
//                println("${cookies.size} Cookie $value")
//                cookieMap[cookie.name] = value
//            }
//        }
//    }
//
//    fun getCookies(){
//        cookies.forEach{
//            println("Cookie: $it")
//        }
//    }
//    fun clearCookies() {
//        cookies.clear()
//        cookieMap.clear()
//        println("Cookies cleared.")
//    }
//}