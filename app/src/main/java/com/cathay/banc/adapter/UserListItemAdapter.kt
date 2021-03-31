package com.cathay.banc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cathay.banc.R
import com.cathay.banc.entity.UserListItem
import com.cathay.banc.util.CircleTransform
import com.squareup.picasso.Picasso

/*
    TODO: to recycle view
 */
class UserListItemAdapter(
    private val context: Context,
    private val userItemList: List<UserListItem>,
) : BaseAdapter() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = userItemList.size
    override fun getItem(position: Int): UserListItem = userItemList[position]
    override fun getItemId(position: Int): Long = position.toLong()

    private class ViewHolder {
        lateinit var ivUserAvatar: ImageView
        lateinit var tvUserName: TextView
        lateinit var tvSiteAdmin: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        val viewHolder: ViewHolder

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_user_list, null)
            //
            viewHolder = ViewHolder()
            viewHolder.ivUserAvatar = view.findViewById(R.id.ivAvatar)
            viewHolder.tvUserName = view.findViewById(R.id.tvLabel)
            viewHolder.tvSiteAdmin = view.findViewById(R.id.tvSiteAdmin)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }
        /*
            set data
         */
        val userItem = getItem(position)
        // avatar
        Picasso.with(context).load(userItem.avatarUrl).transform(CircleTransform()).into(viewHolder.ivUserAvatar)
        // user name
        viewHolder.tvUserName.text = userItem.login
        // shape_badge
        if (userItem.siteAdmin) {
            viewHolder.tvSiteAdmin.text = "STAFF"
            viewHolder.tvSiteAdmin.visibility = View.VISIBLE
        } else {
            viewHolder.tvSiteAdmin.text = ""
            viewHolder.tvSiteAdmin.visibility = View.GONE
        }
        //
        return view!!
    }
}