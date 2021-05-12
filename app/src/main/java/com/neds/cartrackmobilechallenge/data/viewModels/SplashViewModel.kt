package com.neds.cartrackmobilechallenge.data.viewModels

import com.neds.cartrackmobilechallenge.data.repositories.SplashRepository

class SplashViewModel(private val repository: SplashRepository) : BaseViewModel() {

    init {
        repository.populateAppUser()
    }

    fun isLoggedIn() = repository.isLoggedIn()
}