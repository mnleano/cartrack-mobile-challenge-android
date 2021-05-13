package com.neds.cartrackmobilechallenge.data.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.neds.cartrackmobilechallenge.data.enums.Status
import com.neds.cartrackmobilechallenge.data.repositories.LoginRepository
import com.neds.cartrackmobilechallenge.data.views.LoginView
import com.neds.cartrackmobilechallenge.infrastructure.Lg
import com.neds.cartrackmobilechallenge.infrastructure.RepositoryResult
import com.neds.cartrackmobilechallenge.infrastructure.SingleEvent

class LoginViewModel(private val repository: LoginRepository) : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val country = MutableLiveData<String>()

    private val mCountryClickEvent = MutableLiveData<SingleEvent<Unit>>()
    val countryClickEvent: LiveData<SingleEvent<Unit>> = mCountryClickEvent

    private val mLoggedInResult = MediatorLiveData<RepositoryResult<Void>>()
    val loggedInResult: LiveData<RepositoryResult<Void>> = mLoggedInResult

    init {
        formValidation.addRequiredString(email)
        formValidation.addRequiredString(password)
        formValidation.addRequiredString(country)
    }

    fun login() {

        validator?.let { if (!it.validate()) return }

        val username = this.email.value
        val password = this.password.value
        val country = this.country.value
        Lg.d("login: username=$username, password=$password, country=$country")

        if (username == null || password == null || country == null)
            return

        val loginData = repository.login(LoginView(username, password, country))
        mLoading.addSource(loginData) {
            if (it.status != Status.LOADING) mLoading.removeSource(loginData)
            mLoading.postValue(it.status == Status.LOADING)
        }

        mLoggedInResult.addSource(loginData) {
            Lg.d("login.mLoggedInResult: result=${it.status}")
            if (it.status == Status.LOADING) return@addSource
            mLoggedInResult.removeSource(loginData)
            mLoggedInResult.postValue(it)
        }
    }

    fun countryClickEvent() = mCountryClickEvent.postValue(SingleEvent())

    fun clearFields() {
        email.postValue("")
        password.postValue("")
        country.postValue("")
    }
}