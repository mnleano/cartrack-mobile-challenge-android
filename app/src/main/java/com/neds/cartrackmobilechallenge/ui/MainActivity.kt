package com.neds.cartrackmobilechallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neds.cartrackmobilechallenge.R
import com.neds.cartrackmobilechallenge.data.viewModels.UserViewModel
import com.neds.cartrackmobilechallenge.databinding.ActivityMainBinding
import com.neds.cartrackmobilechallenge.infrastructure.Lg
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initViews()
        observeData()
    }

    private fun initViews() {
        binding.lifecycleOwner = this
        binding.vm
    }

    private fun observeData() {
        vm.users.observe(this, { users ->
            users.forEach { Lg.d("observeData.users: user=$it") }
        })
    }
}