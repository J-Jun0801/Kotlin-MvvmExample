package com.jjg.testmvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jjg.testmvvm.R
import com.jjg.testmvvm.model.network.vo.resp.User
import kotlinx.android.synthetic.main.exam_user_list_item.view.*

class UserAdapter : PagedListAdapter<User, UserAdapter.UserViewHolder>(USER_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exam_user_list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.user_avatar
        private val userName = view.user_name
        private val userEmail = view.user_email
        fun bind(user: User) {
            userName.text = user.firstName + " " + user.lastName
            userEmail.text = user.email
//            Glide.with(imageView.context)
//                .load(user.avatar)
//                .placeholder(R.drawable.loading)
//                .into(imageView);
        }
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return newItem == oldItem
            }
        }
    }
}