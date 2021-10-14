package com.jesusrojo.data.datasource

import com.jesusrojo.data.model.UiData
import kotlinx.coroutines.flow.Flow

interface UiDatasLocalDataSource {
    suspend fun fetchDatasDB(): List<UiData>
    suspend fun fetchDatasDBFlow(): Flow<List<UiData>>
    suspend fun saveToDB(uiDatas: List<UiData>)
    suspend fun deleteAllDB()
}