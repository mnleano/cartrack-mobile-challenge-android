package com.neds.cartrackmobilechallenge.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neds.cartrackmobilechallenge.R
import com.neds.cartrackmobilechallenge.data.viewModels.UserViewModel
import com.neds.cartrackmobilechallenge.data.views.UserView
import com.neds.cartrackmobilechallenge.databinding.ActivityMainBinding
import com.neds.cartrackmobilechallenge.infrastructure.Lg
import com.neds.cartrackmobilechallenge.ui.userDetails.UserDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: UserViewModel by viewModel()

    private lateinit var adapter: UserAdapter
    private val users = mutableListOf<UserView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initViews()
        observeData()
    }

    private fun initViews() {
        binding.lifecycleOwner = this
        binding.vm

        adapter = UserAdapter(users, object : UserAdapter.UserClickListener {
            override fun onUserClicked(user: UserView) {
                Lg.d("initViews.onUserClicked: user=$user")
                startActivity(UserDetailsActivity.makeIntent(this@MainActivity, user))
            }
        })

        binding.rvUsers.adapter = adapter
    }

    private fun observeData() {
        vm.users.observe(this, { users ->
            users.forEach { Lg.d("observeData.users: user=$it") }
            this.users.clear()
            this.users.addAll(users)
            adapter.notifyDataSetChanged()
        })
    }
}