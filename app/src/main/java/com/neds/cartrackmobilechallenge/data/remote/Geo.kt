package com.neds.cartrackmobilechallenge.data.remote


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Geo(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
) : Serializable