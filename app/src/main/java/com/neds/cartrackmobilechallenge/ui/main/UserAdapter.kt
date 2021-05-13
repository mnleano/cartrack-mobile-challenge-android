package com.neds.cartrackmobilechallenge.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neds.cartrackmobilechallenge.data.views.UserView
import com.neds.cartrackmobilechallenge.databinding.RowUsersBinding

class UserAdapter(
    private val users: MutableList<UserView>,
    private val listener: UserClickListener
) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            RowUsersBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = users[position]
        holder.binding.data = user
        holder.binding.root.setOnClickListener { listener.onUserClicked(user) }
    }

    override fun getItemCount(): Int = users.size

    inner class MyViewHolder(val binding: RowUsersBinding) : RecyclerView.ViewHolder(binding.root)
    interface UserClickListener {
        fun onUserClicked(user: UserView)
    }

}