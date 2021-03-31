package com.cathay.banc.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDetail(
    @SerializedName("avatar_url") var avatarUrl: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("bio") var bio: String = "",
    @SerializedName("login") var login: String = "",
    @SerializedName("site_admin") var siteAdmin: Boolean = false,
    @SerializedName("location") var location: String = "",
    @SerializedName("blog") var blog: String = "",
) : Parcelable {

    companion object : Parceler<UserDetail> {

        override fun UserDetail.write(parcel: Parcel, flags: Int) {
            if (parcel == null) return

            parcel.writeString(avatarUrl)
            parcel.writeString(name)
            parcel.writeString(bio)
            parcel.writeString(login)
            parcel.writeInt(if (siteAdmin) 1 else 0)
            parcel.writeString(location)
            parcel.writeString(blog)
        }

        override fun create(parcel: Parcel): UserDetail {
            val userDetail = UserDetail()

            userDetail.avatarUrl = parcel.readString() ?: ""
            userDetail.name = parcel.readString() ?: ""
            userDetail.bio = parcel.readString() ?: ""
            userDetail.login = parcel.readString() ?: ""
            userDetail.siteAdmin = parcel.readInt()?.equals(1)
            userDetail.location = parcel.readString() ?: ""
            userDetail.blog = parcel.readString() ?: ""

            return userDetail
        }
    }
}
