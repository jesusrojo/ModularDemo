package com.jesusrojo.data.datasource

import com.jesusrojo.data.model.RepoState
import com.jesusrojo.data.model.UiData
import kotlinx.coroutines.flow.Flow


interface UiDatasRemoteDataSource {
    suspend fun fetchDatasRemote(): RepoState<List<UiData>>
    suspend fun fetchDatasRemoteFlow(): Flow<RepoState<List<UiData>>>
}