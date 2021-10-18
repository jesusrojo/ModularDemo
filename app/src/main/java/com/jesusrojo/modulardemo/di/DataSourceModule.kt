package com.jesusrojo.modulardemo.di

import com.jesusrojo.data.datasource.UiDatasLocalDataSource
import com.jesusrojo.data.datasource.UiDatasRemoteDataSource
import com.jesusrojo.data.datasource.UsersRemoteDataSource
import com.jesusrojo.local.datasource.UiDatasLocalDataSourceImpl
import com.jesusrojo.local.room.UiDataDao
import com.jesusrojo.local.room.mapper.UiDataDboToUiDataMapper
import com.jesusrojo.local.room.mapper.UiDataToDboMapper
import com.jesusrojo.remote.uidata.api.RawDatasApiService
import com.jesusrojo.remote.jsonplaceholder.api.UsersApiService
import com.jesusrojo.remote.uidata.datasource.UiDatasRemoteDataSourceImpl
import com.jesusrojo.remote.jsonplaceholder.datasource.UsersRemoteDataSourceImpl
import com.jesusrojo.remote.uidata.mapper.RawDataToUiDataMapper
import com.jesusrojo.remote.jsonplaceholder.mapper.UserRawDataToUiDataMapper


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {


    @Singleton
    @Provides
    fun provideUiDatasLocalDataSource(
       dao: UiDataDao,
       mapper: UiDataDboToUiDataMapper,
       mapperUiDataToDbo: UiDataToDboMapper
    ): UiDatasLocalDataSource = UiDatasLocalDataSourceImpl(dao, mapper, mapperUiDataToDbo)


    @Singleton
    @Provides
    fun provideUiDatasRemoteDataSource(
        apiService: RawDatasApiService,
        mapper: RawDataToUiDataMapper
    ): UiDatasRemoteDataSource = UiDatasRemoteDataSourceImpl(apiService, mapper)


    @Singleton
    @Provides
    fun provideUsersRemoteDataSource(
        apiService: UsersApiService,
        mapper: UserRawDataToUiDataMapper
    ): UsersRemoteDataSource = UsersRemoteDataSourceImpl(apiService, mapper)

}