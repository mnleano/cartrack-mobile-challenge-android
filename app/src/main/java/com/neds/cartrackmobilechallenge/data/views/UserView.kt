package com.neds.cartrackmobilechallenge.data.views

import com.neds.cartrackmobilechallenge.data.remote.Address
import com.neds.cartrackmobilechallenge.data.remote.Company
import java.io.Serializable

data class UserView(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) : Serializable