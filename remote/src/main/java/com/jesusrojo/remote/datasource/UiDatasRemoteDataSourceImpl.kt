package com.jesusrojo.remote.datasource

import com.jesusrojo.data.datasource.UiDatasRemoteDataSource
import com.jesusrojo.data.model.RemoteState
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

    override suspend fun fetchDatasRemote(): RemoteState<List<UiData>> {
        l("fetchDatasRemote")
        delay(2000)
        val remoteState = apiService.fetchDatas()

        if (remoteState.isSuccessful) {
            val rawDatas = remoteState.body()

            if(rawDatas != null && rawDatas.isNotEmpty()) {

                val uiDatas = mapper(rawDatas)
                return RemoteState.Success(uiDatas)

            } else {
                return RemoteState.Error("rawDatas null or empty")
            }

        } else {
            return RemoteState.Error(remoteState.message())
        }
    }

    override suspend fun fetchDatasRemoteFlow(): Flow<RemoteState<List<UiData>>> {
        TODO("Not yet implemented")
    }
}