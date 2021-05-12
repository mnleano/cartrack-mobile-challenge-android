package com.neds.cartrackmobilechallenge.data.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import br.com.ilhasoft.support.validation.Validator
import com.neds.cartrackmobilechallenge.infrastructure.FormValidationMux

open class BaseViewModel: ViewModel() {

    protected val mLoading = MediatorLiveData<Boolean>()
    val loading: LiveData<Boolean> = mLoading

    protected val mErrorMessage = MediatorLiveData<String>()
    val errorMessage: LiveData<String> = mErrorMessage

    protected val formValidation = FormValidationMux()
    val valid: LiveData<Boolean> = formValidation.valid

    var validator: Validator? = null

    fun addValidator(validator: Validator) {
        validator.enableFormValidationMode()
        this.validator = validator
    }
}