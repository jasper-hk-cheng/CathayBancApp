package com.cathay.banc.repository

import android.content.Context
import android.widget.Toast
import com.cathay.banc.api.GitHubUserApiFactory
import com.cathay.banc.entity.UserDetail
import com.cathay.banc.entity.UserListItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface IUserRepository {

    fun getUserList(loadUserListCallback: LoadUserListCallback)

    interface LoadUserListCallback {
        fun onUserListResult(userListItemList: List<UserListItem>?)
    }

    fun getUserDetail(userLogin: String, loadUserDetailCallback: LoadUserDetailCallback)

    interface LoadUserDetailCallback {
        fun onUserDetailResult(userDetail: UserDetail?)
    }
}

class UserRepository(
    private val context: Context,
) : IUserRepository {

    override fun getUserList(loadUserListCallback: IUserRepository.LoadUserListCallback) {

        GitHubUserApiFactory.instance.getUserList().enqueue(object : Callback<List<UserListItem>> {

            override fun onResponse(call: Call<List<UserListItem>>, response: Response<List<UserListItem>>) {
                loadUserListCallback.onUserListResult(response.body())
            }

            override fun onFailure(call: Call<List<UserListItem>>, throwable: Throwable) {
                Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun getUserDetail(userLogin: String, loadUserDetailCallback: IUserRepository.LoadUserDetailCallback) {

        GitHubUserApiFactory.instance.getUserDetail(userLogin).enqueue(object : Callback<UserDetail> {

            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                loadUserDetailCallback.onUserDetailResult(response.body())
            }

            override fun onFailure(call: Call<UserDetail>, throwable: Throwable) {
                Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
