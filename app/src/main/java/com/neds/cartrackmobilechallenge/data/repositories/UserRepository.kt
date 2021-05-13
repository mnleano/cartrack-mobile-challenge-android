package com.neds.cartrackmobilechallenge.data.repositories

import com.neds.cartrackmobilechallenge.data.remote.UserService

class UserRepository(private val service: UserService) {

    suspend fun getUsers() = service.getUsers()
}