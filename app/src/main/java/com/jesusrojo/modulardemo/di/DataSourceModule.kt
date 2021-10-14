//package com.jesusrojo.modulardemo.di
//
//import com.jesusrojo.data.datasource.UiDatasLocalDataSource
//import com.jesusrojo.data.datasource.UiDatasRemoteDataSource
//import com.jesusrojo.remote.api.RawDatasApiService
//import com.jesusrojo.remote.datasource.UiDatasRemoteDataSourceImpl
//import com.jesusrojo.remote.mapper.RawDataToUiDataMapper
//
//
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//class DataSourceModule {
//
//    @Singleton
//    @Provides
//    fun provideUiDatasRemoteDataSource(
//        apiService: RawDatasApiService,
//        mapper: RawDataToUiDataMapper
//    ): UiDatasRemoteDataSource = UiDatasRemoteDataSourceImpl(apiService, mapper)
//
//}
//
