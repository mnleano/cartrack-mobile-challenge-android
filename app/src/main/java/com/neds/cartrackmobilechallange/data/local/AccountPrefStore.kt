package com.neds.cartrackmobilechallange.data.local
class AccountPrefStore(private val prefs: LocalPreferences) {

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(IS_LOGGED_IN, false)
        set(value) = prefs.putBoolean(IS_LOGGED_IN, value)

    companion object {
        private const val CLASS_NAME = "AccountPrefStore"
        private const val IS_LOGGED_IN = "$CLASS_NAME.isLoggedIn"
    }
}