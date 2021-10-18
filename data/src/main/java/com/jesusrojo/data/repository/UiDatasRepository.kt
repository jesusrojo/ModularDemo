package com.jesusrojo.data.repository

import com.jesusrojo.data.model.RemoteState
import com.jesusrojo.data.model.UiData
import kotlinx.coroutines.flow.Flow

interface UiDatasRepository {
    suspend fun fetchDatas(shouldUpdate: Boolean): RemoteState<List<UiData>>
    suspend fun fetchDatasFlow(): Flow<RemoteState<List<UiData>>>
    suspend fun deleteAllDB()
}