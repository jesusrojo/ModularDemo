package com.jesusrojo.local.room

import androidx.room.*
import com.jesusrojo.local.room.model.UiDataDbo

@Dao
interface UiDataDao {

    @Query("SELECT * FROM uidatadbo_table")
    suspend fun fetchAllDB(): List<UiDataDbo>?

    @Query("DELETE FROM uidatadbo_table")
    suspend fun deleteAllDB()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllDB(datas: List<UiDataDbo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOneDB(data: UiDataDbo)
}