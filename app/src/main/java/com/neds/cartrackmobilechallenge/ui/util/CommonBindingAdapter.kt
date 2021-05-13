package com.neds.cartrackmobilechallenge.ui.util

import androidx.databinding.BindingAdapter
import com.neds.cartrackmobilechallenge.ui.widgets.GThumb

@BindingAdapter("gThumbUsername")
fun GThumb.bindUser(username: String?) =
    username?.let {
        this.loadThumbForInitials(username)
    }