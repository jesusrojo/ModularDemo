package com.jesusrojo.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jesusrojo.local.room.model.UiDataDbo


@Database(entities = [UiDataDbo::class], version = 1, exportSchema = false)
abstract class UiDataDatabase: RoomDatabase() {
    abstract fun myDao(): UiDataDao
}