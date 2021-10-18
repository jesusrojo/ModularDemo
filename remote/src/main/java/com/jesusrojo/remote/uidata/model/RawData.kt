package com.jesusrojo.remote.uidata.model

import com.google.gson.annotations.SerializedName
import com.jesusrojo.remote.jsonplaceholder.model.Address
import com.jesusrojo.remote.jsonplaceholder.model.Company


data class RawData(

    @SerializedName("id") val userId: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("username") val userName: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("website") val website: String?,
//
//    @SerializedName("address") val address: Address?,
//    @SerializedName("company") val company: Company?
)