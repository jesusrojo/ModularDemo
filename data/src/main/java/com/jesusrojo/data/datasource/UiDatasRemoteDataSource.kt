package com.jesusrojo.data.datasource

import com.jesusrojo.data.model.RemoteState
import com.jesusrojo.data.model.UiData
import kotlinx.coroutines.flow.Flow


interface UiDatasRemoteDataSource {
    suspend fun fetchDatasRemote(): RemoteState<List<UiData>>
    suspend fun fetchDatasRemoteFlow(): Flow<RemoteState<List<UiData>>>
}