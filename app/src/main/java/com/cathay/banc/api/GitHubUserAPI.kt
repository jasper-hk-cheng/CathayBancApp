package com.cathay.banc.api

import com.cathay.banc.entity.UserDetail
import com.cathay.banc.entity.UserListItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubUserAPI {
    @GET("/users")
    fun getUserList(): Call<List<UserListItem>>

    @GET("users/{userName}")
    fun getUserDetail(@Path("userName") userName: String): Call<UserDetail>
}
