package com.neds.cartrackmobilechallange.data.local
import com.google.gson.Gson
import com.tencent.mmkv.MMKV

class LocalPreferences (private val mmkv: MMKV, private val gson: Gson) {

    fun <T> get(key: String, clazz: Class<T>) : T? {
        if (mmkv.containsKey(key)) {
            val value = mmkv.getString(key, null) ?: return null
            return gson.fromJson(value, clazz)
        }
        return null
    }

    fun <T> set(key: String, value: T?) {
        if (value == null) {
            mmkv.remove(key).apply()
        } else {
            mmkv.putString(key, gson.toJson(value)).apply()
        }
    }

    fun putString(key: String, value: String?) {
        mmkv.putString(key,value).apply()
    }

    fun getString(key: String, defValue: String?): String? {
        return mmkv.getString(key, defValue)
    }
    fun putInt(key: String, value: Int) {
        mmkv.putInt(key,value).apply()
    }

    fun getInt(key: String, defValue: Int): Int {
        return mmkv.getInt(key, defValue)
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return mmkv.getBoolean(key, defValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        mmkv.putBoolean(key,value).apply()
    }

    fun putLong(key: String, value: Long) {
        mmkv.putLong(key,value).apply()
    }

    fun getLong(key: String, defValue: Long): Long {
        return mmkv.getLong(key, defValue)
    }
}