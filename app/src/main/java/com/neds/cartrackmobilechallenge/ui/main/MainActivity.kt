package com.neds.cartrackmobilechallenge.ui.main

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.neds.cartrackmobilechallenge.R
import com.neds.cartrackmobilechallenge.data.viewModels.MainViewModel
import com.neds.cartrackmobilechallenge.data.views.UserView
import com.neds.cartrackmobilechallenge.databinding.ActivityMainBinding
import com.neds.cartrackmobilechallenge.infrastructure.Lg
import com.neds.cartrackmobilechallenge.ui.BaseActivity
import com.neds.cartrackmobilechallenge.ui.login.LoginActivity
import com.neds.cartrackmobilechallenge.ui.userDetails.UserDetailsActivity
import com.neds.cartrackmobilechallenge.ui.util.AlertDialogUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModel()

    private lateinit var adapter: UserAdapter
    private val users = mutableListOf<UserView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.log_out) {
            AlertDialogUtil.show(
                this, getString(R.string.logout),
                getString(R.string.log_out_confirmation),
                getString(R.string.log_out),
                { dialog, _ ->
                    vm.logOut()
                    dialog.dismiss()
                    startClearTaskActivity(Intent(this@MainActivity, LoginActivity::class.java))
                },
                getString(android.R.string.cancel)
            )
        }
        return true
    }
}