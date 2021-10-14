package com.jesusrojo.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "uidatadbo_table")
data class UiDataDbo(
    val dataId: Int,
    val title: String,
    val description: String,
    val imgUrl: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
