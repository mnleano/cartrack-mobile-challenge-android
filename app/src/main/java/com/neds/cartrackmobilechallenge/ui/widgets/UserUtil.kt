package com.neds.cartrackmobilechallenge.ui.widgets

import android.text.TextUtils

class UserUtil {
    fun getFirstLastFromString(string: String?): Array<String?> {
        if (string == null || string.isEmpty()) return arrayOf(null, null)
        val names: MutableList<String?> = ArrayList(
            mutableListOf(
                *string.split("\\s+").toTypedArray()
            )
        )
        val l = names.size
        if (l >= 2) {
            // The first element = First Name.
            // The rest is Last Name.
            // Middle Name is not considered - use ChatContact.getFirstMiddleLastLast2FromString().
            val first = names[0]
            try {
                names.removeAt(0)
            } catch (whatever: Exception) {
            }
            return arrayOf(first, TextUtils.join(" ", names))
        } else if (l == 1) {
            return arrayOf(names[0], null)
        }
        return arrayOf(null, null)
    }

    fun getFullNameInitial(firstName: String?, secondName: String?):String {
        val f1 = firstName?.trim()?.firstOrNull()?:""
        val f2 = secondName?.trim()?.firstOrNull()?:""
        return "$f1$f2"
    }
}