package com.jesusrojo.remote.model


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city") val city: String,
    @SerializedName("geo") val geo: Geo,
    @SerializedName("street") val street: String,
    @SerializedName("suite") val suite: String,
    @SerializedName("zipcode") val zipcode: String
){
    override fun toString(): String {
        return "ADDRESS:" +
                "\n\tCITY: $city" +
                "\n\t$geo" +
                "\n\tSTREET: $street" +
                "\n\tSUITE: $suite" +
                "\n\tZIPCODE: $zipcode"
    }
}