package com.neds.cartrackmobilechallenge.data.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class AppUser(
    @Id var id: Long = 0,
    var userId: Long,
    var fullName: String,
    var phoneNumber: String,
    var emailAddress: String,
    var password: String
)