package com.neds.cartrackmobilechallenge.data.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class AppUser(
    @Id var id: Long = 0,
    var userId: Long,
    var fullName: String,
    var email: String,
    var password: String,
    var country: String
) {
    constructor(
        userId: Long,
        fullName: String,
        email: String,
        password: String,
        country: String
    ) : this(0, userId, fullName, email, password, country)
}