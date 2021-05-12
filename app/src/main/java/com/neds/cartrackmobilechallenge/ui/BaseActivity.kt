package com.neds.cartrackmobilechallenge.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {

    fun startClearTaskActivity(intent: Intent) {
        startActivity(intent.apply { this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) })
        finish()
    }
}