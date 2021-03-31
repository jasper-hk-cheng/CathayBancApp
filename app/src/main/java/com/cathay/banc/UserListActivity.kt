package com.cathay.banc

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cathay.banc.adapter.UserListItemAdapter
import com.cathay.banc.api.GithubUserEndPoint
import com.cathay.banc.entity.UserDetail
import com.cathay.banc.entity.UserListItem
import com.cathay.banc.util.RetrofitFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
    TODO:
 */
class UserListActivity : AppCompatActivity() {

    /*
        views
     */
    private lateinit var lvUserList: ListView

    /*
        adapters
     */
    private lateinit var userItemAdapter: UserListItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        findViews()
        loadData()
    }

    private fun findViews() {
        //
        lvUserList = findViewById(R.id.lvUserList)
    }

    private fun loadData() {
        // create end point
        val githubUserEndPoint: GithubUserEndPoint = RetrofitFactory.instance.create(GithubUserEndPoint::class.java)
        // enqueue
        githubUserEndPoint.getUserList().enqueue(object : Callback<List<UserListItem>> {

            override fun onResponse(call: Call<List<UserListItem>>, response: Response<List<UserListItem>>) {
                // TODO: 2021/3/31 start toast if no data received...
                val userItemList: List<out UserListItem> = response.body() ?: return
                userItemAdapter = UserListItemAdapter(this@UserListActivity, userItemList)
                //
                setData()
            }

            override fun onFailure(call: Call<List<UserListItem>>, throwable: Throwable) {
                Toast.makeText(this@UserListActivity, throwable.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setData() {
        lvUserList.adapter = userItemAdapter
        lvUserList.onItemClickListener = onUserItemClickLnr
    }

    private val onUserItemClickLnr = AdapterView.OnItemClickListener { parent, view, position, id ->
        val userItem: UserListItem = userItemAdapter.getItem(position)

        val githubUserEndPoint: GithubUserEndPoint = RetrofitFactory.instance.create(GithubUserEndPoint::class.java)

        githubUserEndPoint.getUserDetail(userItem.login).enqueue(object : Callback<UserDetail> {
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                // TODO: 2021/3/31 start toast if no data received...
                val userDetail: UserDetail = response.body() ?: return
                // TODO: use parcelable instead...
                val intent = Intent(this@UserListActivity, UserDetailActivity::class.java)
                intent.putExtra("userDetail", Gson().toJson(userDetail))

                startActivity(intent)
            }

            override fun onFailure(call: Call<UserDetail>, throwable: Throwable) {
                Toast.makeText(this@UserListActivity, throwable.message, Toast.LENGTH_SHORT).show()
            }
        })


    }
}