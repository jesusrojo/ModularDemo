package com.jesusrojo.modulardemo.di



import com.jesusrojo.remote.mapper.RawDataToUiDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MappersModule {

//    @Singleton
//    @Provides
//    fun provideUiDataEntityToUiDataMapper():
//            UiDataEntityToUiDataMapper = UiDataEntityToUiDataMapper()

    @Singleton
    @Provides
    fun provideRawDataToUiDataMapper(): RawDataToUiDataMapper =
        RawDataToUiDataMapper()
}