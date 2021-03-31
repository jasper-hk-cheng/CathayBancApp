package com.cathay.banc.contract

import com.cathay.banc.entity.UserDetail
import com.cathay.banc.entity.UserListItem

class UserListContract {

    interface IUserPresenter {
        fun getUserList()
        fun getUserDetail(userLogin: String)
    }

    interface IUserListView {
        fun onGetResult(userListItemList: List<UserListItem>?)
    }

    interface IUserDetailCallback {
        fun onGetUserDetail(userDetail: UserDetail?)
    }
}
