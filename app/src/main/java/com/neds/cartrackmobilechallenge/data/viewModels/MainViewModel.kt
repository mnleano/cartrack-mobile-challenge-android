package com.neds.cartrackmobilechallenge.data.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.neds.cartrackmobilechallenge.data.maps.asView
import com.neds.cartrackmobilechallenge.data.repositories.AccountRepository
import com.neds.cartrackmobilechallenge.data.repositories.UserRepository
import com.neds.cartrackmobilechallenge.data.views.UserView
import kotlinx.coroutines.launch

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
            userRepository.getUsers().let { response ->
                mLoading.postValue(false)
                response.body()?.let { users ->
                    mUsers.postValue(users.map { u -> u.asView() })
                }
            }
        }
    }

    fun logOut() = accountRepository.logOut()
}