package com.cathay.banc.presenter

import android.content.Context
import com.cathay.banc.contract.UserListContract
import com.cathay.banc.entity.UserDetail
import com.cathay.banc.entity.UserListItem
import com.cathay.banc.repository.IUserRepository
import com.cathay.banc.repository.UserRepository

class UserPresenter(
    context: Context,
    private val userListView: UserListContract.IUserListView,
    private val userDetailCallback: UserListContract.IUserDetailCallback,
) : UserListContract.IUserPresenter {

    /*
        repository
     */
    private val userRepository: UserRepository = UserRepository(context)

    /**
     * user list
     */
    override fun getUserList() {
        userRepository.getUserList(object : IUserRepository.LoadUserListCallback {

            override fun onUserListResult(userListItemList: List<UserListItem>?) {
                userListView.onGetResult(userListItemList)
            }
        })
    }

    /**
     * user detail
     */
    override fun getUserDetail(userLogin: String) {
        userRepository.getUserDetail(userLogin, object : IUserRepository.LoadUserDetailCallback {

            override fun onUserDetailResult(userDetail: UserDetail?) {
                userDetailCallback.onGetUserDetail(userDetail)
            }
        })
    }
}
