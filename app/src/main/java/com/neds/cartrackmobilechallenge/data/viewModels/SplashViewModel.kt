package com.neds.cartrackmobilechallenge.data.viewModels

import com.neds.cartrackmobilechallenge.data.repositories.AccountRepository

class SplashViewModel(private val repository: AccountRepository) : BaseViewModel() {

    init {
        repository.populateAppUser()
    }

    fun isLoggedIn() = repository.isLoggedIn()
}