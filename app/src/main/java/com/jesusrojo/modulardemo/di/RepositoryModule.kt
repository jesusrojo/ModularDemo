//package com.jesusrojo.modulardemo.di
//
//import com.jesusrojo.data.datasource.UiDatasLocalDataSource
//import com.jesusrojo.data.datasource.UiDatasRemoteDataSource
//import com.jesusrojo.data.repository.UiDatasRepository
//import com.jesusrojo.data.repository.UiDatasRepositoryImpl
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//class RepositoryModule {
//
//    @Singleton
//    @Provides
//    fun provideUiDatasRepository(
//        remote: UiDatasRemoteDataSource,
////        local: UiDatasLocalDataSource,
////        mapper: UiDataToUiDataEntityMapper
//    ): UiDatasRepository {
//        return UiDatasRepositoryImpl(remote//, local, mapper
//        )
//    }
//}
//
