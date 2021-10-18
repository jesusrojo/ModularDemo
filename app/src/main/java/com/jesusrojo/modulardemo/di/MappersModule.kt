package com.jesusrojo.modulardemo.di



import com.jesusrojo.data.mapper.UiDataEntityToUiDataMapper
import com.jesusrojo.local.room.mapper.UiDataDboToUiDataMapper
import com.jesusrojo.local.room.mapper.UiDataToDboMapper
import com.jesusrojo.remote.uidata.mapper.RawDataToUiDataMapper
import com.jesusrojo.remote.jsonplaceholder.mapper.UserRawDataToUiDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MappersModule {

    @Singleton
    @Provides
    fun provideUiDataEntityToUiDataMapper():
            UiDataEntityToUiDataMapper = UiDataEntityToUiDataMapper()

    @Singleton
    @Provides
    fun provideRawDataToUiDataMapper(): RawDataToUiDataMapper =
        RawDataToUiDataMapper()

    @Singleton
    @Provides
    fun provideUiDataToDboMapper(): UiDataToDboMapper =
        UiDataToDboMapper()

    @Singleton
    @Provides
    fun provideUiDataDboToUiDataMapper(): UiDataDboToUiDataMapper =
        UiDataDboToUiDataMapper()

    @Singleton
    @Provides
    fun provideUserRawDataToUiDataMapper(): UserRawDataToUiDataMapper =
        UserRawDataToUiDataMapper()
}