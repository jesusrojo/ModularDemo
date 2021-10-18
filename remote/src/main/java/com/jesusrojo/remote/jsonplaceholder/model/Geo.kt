package com.jesusrojo.remote.jsonplaceholder.model


import com.google.gson.annotations.SerializedName

data class Geo(
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String
){
    override fun toString(): String {
        return "GEO:\n\t\tLAT: $lat\n\t\tLNG: $lng"
    }
}