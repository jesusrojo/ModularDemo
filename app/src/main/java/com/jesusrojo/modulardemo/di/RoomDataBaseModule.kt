//package com.jesusrojo.modulardemo.di
//
//import android.content.Context
//import androidx.room.Room
//import com.jesusrojo.local.room.UiDataDao
//import com.jesusrojo.local.room.UiDataDatabase
//
//
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//class RoomDataBaseModule {
//
//    @Provides
//    @Singleton
//    fun providesAppDatabase(@ApplicationContext context: Context): UiDataDatabase =
//        Room.databaseBuilder(
//            context,
//            UiDataDatabase::class.java,
//            "uidata_database.db"
//        ).build()
//
//
//    @Provides
//    @Singleton
//    fun providesForecastDao(db: UiDataDatabase): UiDataDao = db.myDao()
//}