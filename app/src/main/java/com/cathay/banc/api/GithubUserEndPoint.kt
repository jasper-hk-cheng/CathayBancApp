package com.cathay.banc.api

import com.cathay.banc.entity.UserDetail
import com.cathay.banc.entity.UserListItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/*
    TODO
        topic 1 - query string
        @GET("users/list?sort=desc")
        or
        @GET("users/list")
        fun groupList(@Query("sort") sort:String):Call<List<User>>

    TODO
        topic 2 - query string as part of path
        @GET("group/{id}/users")
        fun groupList(@Path("id") groupId:Int):Call<List<User>>

    TODO
        topic 3 - packing all query string in a Map object
        @GET("group/{id}/users")
        fun groupList(@Path("id") groupId:Int, @QueryMap options:Map<String, String>):Call<List<User>>

    TODO
        topic 4 - Post request
        @POST("users/new")
        fun createUser(@Body user:User):Call<User>
 */
interface GithubUserEndPoint {
    @GET("/users")
    fun getUserList(): Call<List<UserListItem>>

    @GET("users/{userName}")
    fun getUserDetail(@Path("userName") userName:String):Call<UserDetail>
}
