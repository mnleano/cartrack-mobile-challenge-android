package com.neds.cartrackmobilechallenge.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.neds.cartrackmobilechallenge.R
import com.neds.cartrackmobilechallenge.data.viewModels.SplashViewModel
import com.neds.cartrackmobilechallenge.ui.login.LoginActivity
import com.neds.cartrackmobilechallenge.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashscreenActivity : BaseActivity() {

    private val vm: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler(Looper.getMainLooper()).postDelayed({
            startClearTaskActivity(
                Intent(
                    this@SplashscreenActivity,
                    if (vm.isLoggedIn()) MainActivity::class.java
                    else LoginActivity::class.java
                )
            )
        }, 500)
    }
}