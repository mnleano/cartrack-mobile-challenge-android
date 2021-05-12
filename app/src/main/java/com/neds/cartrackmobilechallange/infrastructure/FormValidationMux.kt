package com.neds.cartrackmobilechallange.infrastructure

import androidx.lifecycle.*

/**
 * Mechanism for piping all known fields with specific validation parameters into
 * a collection which emits if the whole form is either valid or invalid. It also
 * pipes out the errors resource IDs specified for each field.
 */
class FormValidationMux {
    private val requiredMap = mutableMapOf<BaseRequired, Boolean>()
    private val _valid = MediatorLiveData<Pair<BaseRequired, Boolean>>()

    internal open class BaseRequired(
        private val errorLiveData: MutableLiveData<Int?>,
        private val validLiveData: MutableLiveData<Pair<BaseRequired, Boolean>>
    ) {

        private var valid: Boolean = false

        protected fun assignError(errorId: Int?) {
            errorLiveData.value = errorId
            if (errorId == null) {
                validLiveData.value = Pair(this, true)
                valid = true
            } else {
                validLiveData.value = Pair(this, false)
                valid = false
            }
        }
    }

    internal class RequiredString(
        private val errorMessageResId: Int,
        errorLiveData: MutableLiveData<Int?>,
        validLiveData: MutableLiveData<Pair<BaseRequired, Boolean>>
    ) : BaseRequired(errorLiveData, validLiveData), Observer<String> {
        override fun onChanged(value: String) {
            assignError(if (!value.isBlank()) null else errorMessageResId)
        }
    }

    internal class Custom<TValue>(
        errorLiveData: MutableLiveData<Int?>,
        validLiveData: MutableLiveData<Pair<BaseRequired, Boolean>>,
        private val selector: (TValue?) -> Int?
    ) : BaseRequired(errorLiveData, validLiveData), Observer<TValue?> {
        override fun onChanged(value: TValue?) {
            assignError(selector.invoke(value))
        }
    }

    /**
     * This is the multiplexer. It takes in all the known validity fields and
     * checks if they are all true. If so, it's valid. Otherwise, it's not.
     */
    val valid: LiveData<Boolean> = Transformations.map(_valid) {
        requiredMap[it.first] = it.second
        return@map requiredMap.values.all { v -> v }
    }

    /**
     * Adds a basic validator for a string type which determines if the value is null/blank. For more complex string
     * validations, use addCustom
     */
    fun addRequiredString(liveData: LiveData<String>) {
        val req = RequiredString(0, MutableLiveData<Int?>(), _valid)
        requiredMap[req] = false
        _valid.addSource(liveData, req)
    }

    fun <TValue> addCustom(liveData: LiveData<TValue>, selector: (TValue?) -> Int?) {
        val req = Custom<TValue>(MutableLiveData<Int?>(), _valid, selector)
        requiredMap[req] = false
        _valid.addSource(liveData, req)
    }
}