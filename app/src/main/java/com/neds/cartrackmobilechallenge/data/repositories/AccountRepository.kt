package com.neds.cartrackmobilechallenge.data.repositories

import com.neds.cartrackmobilechallenge.data.entities.AppUser
import com.neds.cartrackmobilechallenge.data.local.AccountPrefStore
import io.objectbox.Box

class AccountRepository(
    private val appUserBox: Box<AppUser>,
    private val accountPrefStore: AccountPrefStore
) {

    fun populateAppUser() {
        if (!accountPrefStore.isFirstTime)
            return

        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Botswana User",
                "botswana@ctmc.com",
                "Abcd1234!",
                "botswana"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Hong Kong User",
                "hongkong@ctmc.com",
                "Abcd1234!",
                "Hong Kong"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Indonesia User",
                "indonesia@ctmc.com",
                "Abcd1234!",
                "Indonesia"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Kenya User",
                "kenya@ctmc.com",
                "Abcd1234!",
                "Kenya"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Malawi User",
                "malawi@ctmc.com",
                "Abcd1234!",
                "Malawi"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Malaysia User",
                "malaysia@ctmc.com",
                "Abcd1234!",
                "Malaysia"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Mozambique User",
                "mozambique@ctmc.com",
                "Abcd1234!",
                "Mozambique"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Namibia User",
                "namibia@ctmc.com",
                "Abcd1234!",
                "Namibia"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "New Zealand User",
                "newzealand@ctmc.com",
                "Abcd1234!",
                "New Zealand"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Nigeria User",
                "nigeria@ctmc.com",
                "Abcd1234!",
                "Nigeria"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Philippines User",
                "philippines@ctmc.com",
                "Abcd1234!",
                "Philippines"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Poland User",
                "poland@ctmc.com",
                "Abcd1234!",
                "item>Poland"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Portugal User",
                "portugal@ctmc.com",
                "Abcd1234!",
                "Portugal"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Rwanda User",
                "rwanda@ctmc.com",
                "Abcd1234!",
                "Rwanda"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Singapore User",
                "singapore@ctmc.com",
                "Abcd1234!",
                "Singapore"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "South Africa User",
                "southafrica@ctmc.com",
                "Abcd1234!",
                "South Africa"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Spain User",
                "spain@ctmc.com",
                "Abcd1234!",
                "Spain"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Swaziland User",
                "swaziland@ctmc.com",
                "Abcd1234!",
                "Swaziland"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Tanzania User",
                "tanzania@ctmc.com",
                "Abcd1234!",
                "Tanzania"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Thailand User",
                "thailand@ctmc.com",
                "Abcd1234!",
                "Thailand"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "United Arab Emirates User",
                "unitedarabemirates@ctmc.com",
                "Abcd1234!",
                "United Arab Emirates"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "USA User",
                "usa@ctmc.com",
                "Abcd1234!",
                "USA"
            )
        )
        appUserBox.put(
            AppUser(
                System.currentTimeMillis(),
                "Zimbabwe User",
                "zimbabwe@ctmc.com",
                "Abcd1234!",
                "Zimbabwe"
            )
        )
        accountPrefStore.isFirstTime = false
    }

    fun isLoggedIn() = accountPrefStore.isLoggedIn
    fun logOut() {
        accountPrefStore.isLoggedIn = false
    }
}