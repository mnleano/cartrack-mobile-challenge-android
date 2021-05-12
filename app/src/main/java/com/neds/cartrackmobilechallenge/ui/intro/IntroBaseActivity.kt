package com.neds.cartrackmobilechallenge.ui.intro

import android.content.Intent
import com.neds.cartrackmobilechallenge.ui.BaseActivity
import com.neds.cartrackmobilechallenge.ui.MainActivity


open class IntroBaseActivity : BaseActivity() {

    fun startLoginActivity(finishedActivity: Boolean = true) {
        startActivity(Intent(this, LoginActivity::class.java))
        if (finishedActivity) finish()
    }

    fun startSignUpActivity(finishedActivity: Boolean = true) {
        startActivity(Intent(this, SignUpActivity::class.java))
        if (finishedActivity) finish()
    }

    fun startMainActivity(){
        startClearTaskActivity(Intent(this, MainActivity::class.java))
    }
}