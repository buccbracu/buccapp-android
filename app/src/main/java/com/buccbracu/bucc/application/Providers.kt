package com.buccbracu.bucc.application

import com.buccbracu.bucc.backend.remote.api.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import okhttp3.CookieJar
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Providers {

    @Provides
    @Singleton
    fun provideRealm(): Realm {
        return LocalServer.realm
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return RetrofitServer.retrofit
    }

    @Provides
    @Singleton
    fun provideRetrofitAuth(): AuthService {
        return RetrofitServer.Auth
    }
    @Provides
    @Singleton
    fun provideRetrofitCookieMap(): Map<String, String> {
        return RetrofitServer.cookieMap
    }
}