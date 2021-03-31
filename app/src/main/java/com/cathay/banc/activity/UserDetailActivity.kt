package com.cathay.banc.activity

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cathay.banc.R
import com.cathay.banc.adapter.UserDetailItemAdapter
import com.cathay.banc.entity.UserDetail
import com.cathay.banc.entity.UserDetailItem
import com.cathay.banc.util.CircleTransform
import com.cathay.banc.util.Constants.PARCELABLE_KEY_USER_DETAIL
import com.squareup.picasso.Picasso

class UserDetailActivity : AppCompatActivity() {

    /*
        views
     */
    private lateinit var imgBtnDetailGoBack: ImageButton
    private lateinit var ivUserAvatar: ImageView
    private lateinit var tvUserName: TextView
    private lateinit var tvUserBio: TextView
    private lateinit var lvUserDetail: ListView

    /*
        adapters
     */
    private lateinit var userDetailAdapter: UserDetailItemAdapter

    /*
        model
     */
    private lateinit var userDetail: UserDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        findViews()
        loadData()
        setData()
    }

    private fun findViews() {
        //
        imgBtnDetailGoBack = findViewById(R.id.imgBtnDetailGoBack)
        imgBtnDetailGoBack.setOnClickListener { finish() }
        //
        ivUserAvatar = findViewById(R.id.ivUserAvatar)
        //
        tvUserName = findViewById(R.id.tvUserName)
        //
        tvUserBio = findViewById(R.id.tvUserBio)
        //
        lvUserDetail = findViewById(R.id.lvUserDetail)
    }

    private fun loadData() {
        userDetail = intent.getParcelableExtra(PARCELABLE_KEY_USER_DETAIL)
        // adapter
        val userDetailItemList: List<UserDetailItem> = listOf(
            UserDetailItem(userDetail.login, if (userDetail.siteAdmin) "STAFF" else ""),
            UserDetailItem(label = userDetail.location),
            UserDetailItem(label = userDetail.blog),
        )
        userDetailAdapter = UserDetailItemAdapter(this, userDetailItemList)
    }

    private fun setData() {
        // avatar
        Picasso.with(this).load(userDetail.avatarUrl).transform(CircleTransform).into(ivUserAvatar)
        // user name
        tvUserName.text = userDetail.name
        // user autobiography
        tvUserBio.text = userDetail.bio
        // detail list
        lvUserDetail.adapter = userDetailAdapter
    }
}