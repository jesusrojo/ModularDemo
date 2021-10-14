package com.jesusrojo.remote.model


import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("bs") val bs: String,
    @SerializedName("catchPhrase") val catchPhrase: String,
    @SerializedName("name") val companyName: String
){
    override fun toString(): String {
        return "COMPANY:\n" +
                "\t\tBS: $bs\n" +
                "\t\tCATCH PHRASE: $catchPhrase\n" +
                "\t\tNAME: $companyName\n"
    }
}