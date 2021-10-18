package com.jesusrojo.data.repository

import com.jesusrojo.data.datasource.UiDatasLocalDataSource
import com.jesusrojo.data.datasource.UiDatasRemoteDataSource
import com.jesusrojo.data.model.*

import kotlinx.coroutines.flow.Flow
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject

class UiDatasRepositoryImpl @Inject constructor(
    private val remote: UiDatasRemoteDataSource,
    private val local: UiDatasLocalDataSource,
) : UiDatasRepository {

    private val isDebug = true
    private val logger = Logger.getLogger(UiDatasRepositoryImpl::class.java.simpleName)
    private fun l(msg: String) { if(isDebug)logger.log(Level.INFO, "$msg ##") }

    override suspend fun fetchDatas(shouldUpdate: Boolean): RemoteState<List<UiData>> {
        l("fetchDatas $shouldUpdate")
        if(shouldUpdate){
            return fetchFromRemoteAndSaveToDB()
        }
        return fetchFromDBOrRemote()
    }

    private suspend fun fetchFromDBOrRemote(): RemoteState<List<UiData>> {
        l("fetchFromDBOrRemote")
        val uiDatas = local.fetchDatasDB()
        if (isNotNullNotEmpty(uiDatas)) {
            return RemoteState.Success(uiDatas, "local")
        }
        return fetchFromRemoteAndSaveToDB()
    }

    private suspend fun fetchFromRemoteAndSaveToDB(): RemoteState<List<UiData>> {
        l("fetchFromRemoteAndSaveToDB")
        val remoteState = remote.fetchDatasRemote()
        return when (remoteState) {
            is RemoteState.Success -> handleSuccess(remoteState)
            is RemoteState.Error -> RemoteState.Error("Error ${remoteState.message}")
        }
    }

    private suspend fun handleSuccess(remoteState: RemoteState<List<UiData>>): RemoteState<List<UiData>> {
        val uiDatas: List<UiData>? = remoteState.data
        val msg: String? = remoteState.message
        return if (isNotNullNotEmpty(uiDatas)) {
            deleteDBAndSaveToDB(uiDatas!!)
            RemoteState.Success(uiDatas, msg)
        } else {
            RemoteState.Error("Error datas null or empty. ${remoteState.message}")
        }
    }

    private suspend fun deleteDBAndSaveToDB(uiDatas: List<UiData>) {
        deleteAllDB()
        local.saveToDB(uiDatas)
    }

    private fun isNotNullNotEmpty(uiDatas: List<UiData>?) =
        uiDatas != null && uiDatas.isNotEmpty()

    override suspend fun deleteAllDB() {
        local.deleteAllDB()
    }

    override suspend fun fetchDatasFlow(): Flow<RemoteState<List<UiData>>> {
        TODO("Not yet implemented")
    }
}