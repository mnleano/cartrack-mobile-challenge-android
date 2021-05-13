package com.neds.cartrackmobilechallenge.data.remote


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Geo(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
) : Serializable