package com.neds.cartrackmobilechallenge.data.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neds.cartrackmobilechallenge.infrastructure.SingleEvent

class LoginViewModel : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val mLoginSuccessfulEvent = MutableLiveData<SingleEvent<Unit>>()
    val loginSuccessfulEvent: LiveData<SingleEvent<Unit>> = mLoginSuccessfulEvent

    private val mSignUpClickEvent = MutableLiveData<SingleEvent<Unit>>()
    val signUpClickEvent: LiveData<SingleEvent<Unit>> = mSignUpClickEvent

    init {
        formValidation.addRequiredString(email)
        formValidation.addRequiredString(password)
    }

    fun login(){

    }

    fun signUpClickEvent(){

    }

}