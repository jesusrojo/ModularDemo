package com.jesusrojo.data.repository

import com.jesusrojo.data.datasource.UiDatasLocalDataSource
import com.jesusrojo.data.datasource.UiDatasRemoteDataSource
import com.jesusrojo.data.datasource.UsersRemoteDataSource
import com.jesusrojo.data.model.*

import kotlinx.coroutines.flow.Flow
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject

class UiDatasRepositoryImpl @Inject constructor(
//    private val remote: UiDatasRemoteDataSource,
    private val remote: UsersRemoteDataSource,//todo users
    private val local: UiDatasLocalDataSource,
) : UiDatasRepository {

    private val isDebug = true
    private val logger = Logger.getLogger(UiDatasRepositoryImpl::class.java.simpleName)
    private fun l(msg: String) { if(isDebug)logger.log(Level.INFO, "$msg ##") }

    override suspend fun fetchDatas(shouldUpdate: Boolean): RepoState<List<UiData>> {
        l("fetchDatas $shouldUpdate")
        if(shouldUpdate){
            return fetchFromRemoteAndSaveToDB()
        }
        return fetchFromDBOrRemote()
    }

    private suspend fun fetchFromDBOrRemote(): RepoState<List<UiData>> {
        l("fetchFromDBOrRemote")
        val uiDatas = local.fetchDatasDB()
        if (isNotNullNotEmpty(uiDatas)) {
            return RepoState.Success(uiDatas, "local")
        }
        return fetchFromRemoteAndSaveToDB()
    }

    private suspend fun fetchFromRemoteAndSaveToDB(): RepoState<List<UiData>> {
        l("fetchFromRemoteAndSaveToDB")
        val repoState = remote.fetchDatasRemote()
        return when (repoState) {
            is RepoState.Success -> handleSuccess(repoState)
            is RepoState.Error -> RepoState.Error("Error ${repoState.message}")
        }
    }

    private suspend fun handleSuccess(repoState: RepoState<List<UiData>>): RepoState<List<UiData>> {
        val uiDatas: List<UiData>? = repoState.data
        val msg: String? = repoState.message
        return if (isNotNullNotEmpty(uiDatas)) {
            deleteDBAndSaveToDB(uiDatas!!)
            RepoState.Success(uiDatas, msg)
        } else {
            RepoState.Error("Error datas null or empty. ${repoState.message}")
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

    override suspend fun fetchDatasFlow(): Flow<RepoState<List<UiData>>> {
        TODO("Not yet implemented")
    }
}