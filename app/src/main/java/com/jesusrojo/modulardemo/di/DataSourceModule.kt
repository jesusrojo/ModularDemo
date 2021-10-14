package com.jesusrojo.modulardemo.di

import com.jesusrojo.data.datasource.UiDatasLocalDataSource
import com.jesusrojo.data.datasource.UiDatasRemoteDataSource
import com.jesusrojo.local.datasource.UiDatasLocalDataSourceImpl
import com.jesusrojo.local.room.UiDataDao
import com.jesusrojo.local.room.mapper.UiDataDboToUiDataMapper
import com.jesusrojo.local.room.mapper.UiDataToDboMapper
import com.jesusrojo.remote.api.RawDatasApiService
import com.jesusrojo.remote.datasource.UiDatasRemoteDataSourceImpl
import com.jesusrojo.remote.mapper.RawDataToUiDataMapper


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

}