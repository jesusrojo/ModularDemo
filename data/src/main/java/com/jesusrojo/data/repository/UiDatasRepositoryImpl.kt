package com.jesusrojo.data.repository

import com.jesusrojo.data.datasource.UiDatasLocalDataSource
import com.jesusrojo.data.datasource.UiDatasRemoteDataSource
import com.jesusrojo.data.model.*

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject

class UiDatasRepositoryImpl @Inject constructor(
    private val remote: UiDatasRemoteDataSource,
//    private val local: UiDatasLocalDataSource,
//    private val mapper: UiDataToUiDataEntityMapper
) : UiDatasRepository {

    private val isDebug = true
    private val logger = Logger.getLogger(UiDatasRepositoryImpl::class.java.simpleName)
    private fun l(msg: String) { if(isDebug)logger.log(Level.INFO, "$msg ##") }

    private fun shouldUpdate(): Boolean {
        return true //todo
    }

    override suspend fun fetchDatas(): RemoteState<List<UiData>> {
        return fetchFromRemoteAndSaveToDB()
    }

//    //override
//    suspend fun fetchDatas(): RemoteState<List<UiData>> {
//        l("fetchDatas")
//        if(shouldUpdate()){
//            return fetchFromRemoteAndSaveToDB()
//        }
//
//        val uiDatas = local.fetchDatasDB()
//        if (isNotNullNotEmpty(uiDatas)) {
//            //val uiDatasEntity = mapper(uiDatas)
//            return RemoteState.Success(uiDatasEntity)
//        }
//
//        return fetchFromRemoteAndSaveToDB()
//    }

    private suspend fun fetchFromRemoteAndSaveToDB(): RemoteState<List<UiData>> {
        l("fetchFromRemoteAndSaveToDB")
        val remoteState = remote.fetchDatasRemote()
        return handleRemoteState(remoteState)
    }


    private suspend fun handleRemoteState(remoteState: RemoteState<List<UiData>>):
            RemoteState<List<UiData>> {
        l("handleRemoteState")
        return when (remoteState) {
            is RemoteState.Success -> {
                val uiDatas: List<UiData>? = remoteState.data
                if (isNotNullNotEmpty(uiDatas)) {
                    //  local.saveToDB(uiDatas!!)
                    //  val entityDatas: List<UiData> = mapper(uiDatas)
                    //  RemoteState.Success(entityDatas)
                    RemoteState.Success(uiDatas!!)
                } else {
                    RemoteState.Error("Error datas null or empty. ${remoteState.message}")
                }
            }
            is RemoteState.Error -> RemoteState.Error("Error ${remoteState.message}")
        }
    }

    private suspend fun handleRemoteStateSuccess(remoteState: RemoteState<List<UiData>>): RemoteState<List<UiData>> {
        l("handleRemoteStateSuccess")
        val uiDatas: List<UiData>? = remoteState.data
        return if (isNotNullNotEmpty(uiDatas)) {
          //  local.saveToDB(uiDatas!!)
          //  val entityDatas: List<UiData> = mapper(uiDatas)
          //  RemoteState.Success(entityDatas)
            RemoteState.Success(uiDatas!!)
        } else {
            RemoteState.Error("Error datas null or empty. ${remoteState.message}")
        }
    }

    private fun isNotNullNotEmpty(uiDatas: List<UiData>?) =
        uiDatas != null && uiDatas.isNotEmpty()

    override suspend fun deleteAllDB() {
        //local.deleteAllDB()
    }



    override suspend fun fetchDatasFlow(): Flow<RemoteState<List<UiData>>> {
        TODO("Not yet implemented")
    }
}