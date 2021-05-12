package com.neds.cartrackmobilechallenge.data.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neds.cartrackmobilechallenge.infrastructure.SingleEvent

class SignUpViewModel : BaseViewModel() {

    val fullName = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val emailAddress = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    private val mLoginClickEvent = MutableLiveData<SingleEvent<Unit>>()
    val loginClickEvent: LiveData<SingleEvent<Unit>> = mLoginClickEvent

    private val mRegisterSuccessfulEvent = MutableLiveData<SingleEvent<Unit>>()
    val registerSuccessfulEvent: LiveData<SingleEvent<Unit>> = mRegisterSuccessfulEvent

    init {
        formValidation.addRequiredString(fullName)
        formValidation.addRequiredString(phoneNumber)
        formValidation.addRequiredString(emailAddress)
        formValidation.addRequiredString(password)
        formValidation.addRequiredString(confirmPassword)
    }

    fun register() {

        validator?.let { if (!it.validate()) return }

        val fullName = fullName.value
        val phoneNumber = phoneNumber.value
        val emailAddress = emailAddress.value
        val password = password.value
        val confirmPassword = confirmPassword.value

        if (fullName.isNullOrEmpty() ||
            phoneNumber.isNullOrEmpty() ||
            emailAddress.isNullOrEmpty() ||
            password.isNullOrEmpty() ||
            confirmPassword.isNullOrEmpty()
        ) return

//        val signUpView =
//            SignUpView(fullName, "", phoneNumber, emailAddress, address, password)
//
//        repository.saveUser(signUpView)
//        mRegisterSuccessfulEvent.postValue(SingleEvent(Unit))
    }

    fun loginClickEvent() = mLoginClickEvent.postValue(SingleEvent(Unit))
}