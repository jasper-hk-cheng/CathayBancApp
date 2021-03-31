package com.cathay.banc.entity

import com.google.gson.annotations.SerializedName

data class UserListItem(
    @SerializedName("avatar_url") var avatarUrl: String = "",
    @SerializedName("login") var login: String = "",
    @SerializedName("site_admin") var siteAdmin: Boolean = false,
)
