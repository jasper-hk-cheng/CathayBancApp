package com.cathay.banc.activity

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cathay.banc.R
import com.cathay.banc.adapter.UserListItemAdapter
import com.cathay.banc.contract.UserListContract
import com.cathay.banc.entity.UserDetail
import com.cathay.banc.entity.UserListItem
import com.cathay.banc.presenter.UserPresenter
import com.google.gson.Gson

/*

 */
class UserListActivity : AppCompatActivity(), UserListContract.IUserListView, UserListContract.IUserDetailCallback {

    /*
        views
     */
    private lateinit var lvUserList: ListView

    /*
        adapters
     */
    private lateinit var userItemAdapter: UserListItemAdapter

    /*
        presenters
     */
    private val userListPresenter = UserPresenter(this, this, this)

    /*
        start the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        findViews()
        loadData()
    }

    private fun findViews() {
        //
        lvUserList = findViewById(R.id.lvUserList)
        lvUserList.onItemClickListener = onUserItemClickLnr
    }

    private fun loadData() {
        userListPresenter.getUserList()
    }

    /*
        user list result
     */
    override fun onGetResult(userListItemList: List<UserListItem>?) {
        if(userListItemList == null){
            Toast.makeText(this@UserListActivity, "No GitHub User was received !!", Toast.LENGTH_SHORT).show()
            return
        }
        userItemAdapter = UserListItemAdapter(this@UserListActivity, userListItemList)
        lvUserList.adapter = userItemAdapter
    }

    /*
        user detail result
     */
    override fun onGetUserDetail(userDetail: UserDetail?) {
        if(userDetail == null){
            Toast.makeText(this@UserListActivity, "No GitHub User Detail was received !!", Toast.LENGTH_SHORT).show()
            return
        }
        // TODO: use parcelable instead...
        val intent = Intent(this@UserListActivity, UserDetailActivity::class.java)
        intent.putExtra("userDetail", Gson().toJson(userDetail))

        startActivity(intent)
    }

    /*
        listener
     */
    private val onUserItemClickLnr = AdapterView.OnItemClickListener { _, _, position, _ ->
        val userListItem: UserListItem = userItemAdapter.getItem(position)
        userListPresenter.getUserDetail(userListItem.login)
    }
}
