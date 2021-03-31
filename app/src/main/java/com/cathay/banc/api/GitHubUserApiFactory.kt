package com.cathay.banc.api

import com.cathay.banc.util.Constants.RETROFIT_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitHubUserApiFactory {

    private val retrofit: Retrofit
    private val gitHubUserAPI: GitHubUserAPI

    // TODO: 2021/3/31 attach log interceptor
    private val okHttpClient = OkHttpClient()

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(RETROFIT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        gitHubUserAPI = retrofit.create(GitHubUserAPI::class.java)
    }

    val instance: GitHubUserAPI
        get() = gitHubUserAPI
}
