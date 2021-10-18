package com.jesusrojo.remote.datasource

import com.jesusrojo.data.datasource.UsersRemoteDataSource
import com.jesusrojo.data.model.RepoState
import com.jesusrojo.data.model.UiData
import com.jesusrojo.remote.api.UsersApiService
import com.jesusrojo.remote.mapper.UserRawDataToUiDataMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import java.util.logging.Level
import java.util.logging.Logger

class UsersRemoteDataSourceImpl(
    private val apiService: UsersApiService,
    private val mapper: UserRawDataToUiDataMapper
) : UsersRemoteDataSource
{

    private val isDebug = true
    private val logger = Logger.getLogger(UsersRemoteDataSourceImpl::class.java.simpleName)
    private fun l(msg: String) { if(isDebug)logger.log(Level.INFO, "$msg ##") }

    override suspend fun fetchDatasRemote(): RepoState<List<UiData>> {
        l("fetchDatasRemote")
        delay(2000)
        val repoState = apiService.fetchUsersApi()

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
}