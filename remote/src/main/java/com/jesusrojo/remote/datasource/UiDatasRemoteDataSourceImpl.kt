package com.jesusrojo.remote.datasource

import com.jesusrojo.data.datasource.UiDatasRemoteDataSource
import com.jesusrojo.data.model.RepoState
import com.jesusrojo.data.model.UiData
import com.jesusrojo.remote.api.RawDatasApiService
import com.jesusrojo.remote.mapper.RawDataToUiDataMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import java.util.logging.Level
import java.util.logging.Logger

class UiDatasRemoteDataSourceImpl(
    private val apiService: RawDatasApiService,
    private val mapper: RawDataToUiDataMapper
) : UiDatasRemoteDataSource
{

    private val isDebug = true
    private val logger = Logger.getLogger(UiDatasRemoteDataSourceImpl::class.java.simpleName)
    private fun l(msg: String) { if(isDebug)logger.log(Level.INFO, "$msg ##") }

    override suspend fun fetchDatasRemote(): RepoState<List<UiData>> {
        l("fetchDatasRemote")
        delay(2000)
        val repoState = apiService.fetchDatasApi()

        return if (repoState.isSuccessful) {
            val rawDatas = repoState.body()

            if(rawDatas != null && rawDatas.isNotEmpty()) {

                val uiDatas = mapper(rawDatas)
                RepoState.Success(uiDatas, "remote")

            } else {
                RepoState.Error("rawDatas null or empty")
            }

        } else {
            RepoState.Error(repoState.message())
        }
    }

    override suspend fun fetchDatasRemoteFlow(): Flow<RepoState<List<UiData>>> {
        TODO("Not yet implemented")
    }
}