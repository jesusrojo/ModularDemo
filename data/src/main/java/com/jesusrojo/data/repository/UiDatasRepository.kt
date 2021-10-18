package com.jesusrojo.data.repository

import com.jesusrojo.data.model.RepoState
import com.jesusrojo.data.model.UiData
import kotlinx.coroutines.flow.Flow

interface UiDatasRepository {
    suspend fun fetchDatas(shouldUpdate: Boolean): RepoState<List<UiData>>
    suspend fun fetchDatasFlow(): Flow<RepoState<List<UiData>>>
    suspend fun deleteAllDB()
}