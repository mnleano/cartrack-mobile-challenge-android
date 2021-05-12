package com.neds.cartrackmobilechallenge.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.neds.cartrackmobilechallenge.data.entities.AppUser
import com.neds.cartrackmobilechallenge.data.entities.AppUser_
import com.neds.cartrackmobilechallenge.data.local.AccountPrefStore
import com.neds.cartrackmobilechallenge.data.views.LoginView
import com.neds.cartrackmobilechallenge.infrastructure.Lg
import com.neds.cartrackmobilechallenge.infrastructure.RepositoryResult
import io.objectbox.Box

class LoginRepository(
    private val appUserBox: Box<AppUser>,
    private val accountPrefStore: AccountPrefStore
) {

    fun login(loginView: LoginView): LiveData<RepositoryResult<Void>> {
        val liveData = MediatorLiveData<RepositoryResult<Void>>()
        liveData.postValue(RepositoryResult.loading())

        val result = appUserBox.query().equal(AppUser_.username, loginView.username)
            .and().equal(AppUser_.password, loginView.password)
            .and().equal(AppUser_.country, loginView.country).build().findFirst()

        liveData.postValue(result?.let { RepositoryResult.success() }
            ?: RepositoryResult.error("Invalid username or password"))

        return liveData
    }
}