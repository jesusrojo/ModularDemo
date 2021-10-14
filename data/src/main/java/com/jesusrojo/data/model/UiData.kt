package com.jesusrojo.data.model

import java.io.Serializable

//@Keep
data class UiData(
    val dataId: Int,
    val title: String,
    val description: String,
    val imgUrl: String
) : Serializable