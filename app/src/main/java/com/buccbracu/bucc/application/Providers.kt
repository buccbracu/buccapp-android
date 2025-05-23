package com.buccbracu.bucc.application

import com.buccbracu.bucc.application.Retrofit.RetrofitServer
import com.buccbracu.bucc.backend.remote.api.AuthService
import com.buccbracu.bucc.backend.remote.api.BlogService
import com.buccbracu.bucc.backend.remote.api.DeptMemberService
import com.buccbracu.bucc.backend.remote.api.GithubService
import com.buccbracu.bucc.backend.remote.api.TaskService
import com.buccbracu.bucc.backend.remote.api.UserService
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
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
    fun provideRetrofitUser(): UserService {
        return RetrofitServer.User
    }
    @Provides
    @Singleton
    fun provideRetrofitMember(): DeptMemberService {
        return RetrofitServer.DeptMember
    }
    @Provides
    @Singleton
    fun provideRetrofitBlog(): BlogService {
        return RetrofitServer.Blog
    }
    @Provides
    @Singleton
    fun provideRetrofitTask(): TaskService {
        return RetrofitServer.Task
    }
    @Provides
    @Singleton
    fun provideRetrofitGithub(): GithubService {
        return RetrofitServer.Github
    }


}