package com.neds.cartrackmobilechallenge.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.neds.cartrackmobilechallenge.ui.util.KeyboardUtil


open class BaseActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        KeyboardUtil.hideKeyboard(this, window.decorView.rootView)
    }

    fun startClearTaskActivity(intent: Intent) {
        startActivity(intent.apply { this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) })
        finish()
    }
}