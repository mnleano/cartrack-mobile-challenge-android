package com.neds.cartrackmobilechallenge.data.local

class AccountPrefStore(private val prefs: LocalPreferences) {

    var isFirstTime: Boolean
        get() = prefs.getBoolean(IS_FIRST_TIME, true)
        set(value) = prefs.putBoolean(IS_FIRST_TIME, value)

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(IS_LOGGED_IN, false)
        set(value) = prefs.putBoolean(IS_LOGGED_IN, value)

    companion object {
        private const val CLASS_NAME = "AccountPrefStore"
        private const val IS_FIRST_TIME = "$CLASS_NAME.isFirsTime"
        private const val IS_LOGGED_IN = "$CLASS_NAME.isLoggedIn"
    }
}