package com.neds.cartrackmobilechallenge.data.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neds.cartrackmobilechallenge.infrastructure.SingleEvent

class LoginViewModel : BaseViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val country = MutableLiveData<String>()

    private val mCountryClickEvent = MutableLiveData<SingleEvent<Unit>>()
    val countryClickEvent: LiveData<SingleEvent<Unit>> = mCountryClickEvent

    private val mLoginSuccessfulEvent = MutableLiveData<SingleEvent<Unit>>()
    val loginSuccessfulEvent: LiveData<SingleEvent<Unit>> = mLoginSuccessfulEvent

    init {
        formValidation.addRequiredString(username)
        formValidation.addRequiredString(password)
    }

    fun login(){

    }

    fun countryClickEvent() = mCountryClickEvent.postValue(SingleEvent(Unit))

}