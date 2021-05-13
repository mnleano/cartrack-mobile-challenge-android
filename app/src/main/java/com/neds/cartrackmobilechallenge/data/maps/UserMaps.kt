package com.neds.cartrackmobilechallenge.data.maps

import com.neds.cartrackmobilechallenge.data.remote.UserResponseDtoItem
import com.neds.cartrackmobilechallenge.data.views.UserView

fun UserResponseDtoItem.asView() =
    UserView(
        this.address,
        this.company,
        this.email,
        this.id,
        this.name,
        this.phone,
        this.username,
        this.website
    )