package com.jesusrojo.data.datasource

import com.jesusrojo.data.model.RepoState
import com.jesusrojo.data.model.UiData

interface UsersRemoteDataSource {
    suspend fun fetchDatasRemote(): RepoState<List<UiData>>
}