package com.cathay.banc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.cathay.banc.R
import com.cathay.banc.entity.UserDetailItem
import com.squareup.picasso.Picasso

class UserDetailItemAdapter(
    private val context: Context,
    private val userDetailItemList: List<UserDetailItem>,
) : BaseAdapter() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = userDetailItemList.size
    override fun getItem(position: Int): UserDetailItem = userDetailItemList[position]
    override fun getItemId(position: Int): Long = position.toLong()

    private class ViewHolder {
        lateinit var ivAvatar: ImageView
        lateinit var tvLabel: TextView
        lateinit var tvSiteAdmin: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        val viewHolder: ViewHolder

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_user_list, null)
            viewHolder = ViewHolder()
            //
            viewHolder.ivAvatar = view.findViewById(R.id.ivAvatar)
            viewHolder.tvLabel = view.findViewById(R.id.tvLabel)
            viewHolder.tvSiteAdmin = view.findViewById(R.id.tvSiteAdmin)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }
        /*
            set data
         */
        val userDetailItem: UserDetailItem = getItem(position)
        // avatar
        val avatarResId = when (position) {
            0 -> R.drawable.portrait
            1 -> R.drawable.location
            2 -> R.drawable.hyperlink
            else -> R.drawable.cross
        }
        Picasso.with(context).load(avatarResId).placeholder(R.drawable.cross).noFade().into(viewHolder.ivAvatar)
        viewHolder.ivAvatar.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        viewHolder.ivAvatar.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
        // label
        viewHolder.tvLabel.text = userDetailItem.label
        // site admin (badge)
        viewHolder.tvSiteAdmin.text = userDetailItem.siteAdmin
        viewHolder.tvSiteAdmin.visibility = if (userDetailItem.siteAdmin.isNotBlank()) View.VISIBLE else View.GONE
        //
        return view!!
    }
}
