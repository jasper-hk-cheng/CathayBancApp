package com.cathay.banc.entity

import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("avatar_url") var avatarUrl: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("bio") var bio: String = "",
    @SerializedName("login") var login: String = "",
    @SerializedName("site_admin") var siteAdmin: Boolean = false,
    @SerializedName("location") var location: String = "",
    @SerializedName("blog") var blog: String = "",
)
