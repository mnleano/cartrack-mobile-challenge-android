package com.neds.cartrackmobilechallenge.data.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.neds.cartrackmobilechallenge.data.maps.asView
import com.neds.cartrackmobilechallenge.data.repositories.AccountRepository
import com.neds.cartrackmobilechallenge.data.repositories.UserRepository
import com.neds.cartrackmobilechallenge.data.views.UserView
import com.neds.cartrackmobilechallenge.infrastructure.Lg
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(
    private val userRepository: UserRepository,
    private val accountRepository: AccountRepository
) : BaseViewModel() {

    private val mUsers = MediatorLiveData<List<UserView>>()
    val users: LiveData<List<UserView>> = mUsers

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            mLoading.postValue(true)
            try {
                userRepository.getUsers().let { response ->
                    mLoading.postValue(false)
                    response.body()?.let { users ->
                        mUsers.postValue(users.map { u -> u.asView() })
                    }
                }
            } catch (e: IOException) {
                mErrorMessage.postValue(e.message)
                mLoading.postValue(false)
                Lg.e("getUsers: IOException=${e.message}")
            }
        }
    }

    fun logOut() = accountRepository.logOut()
}