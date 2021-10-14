package com.jesusrojo.local.datasource


import com.jesusrojo.data.datasource.UiDatasLocalDataSource
import com.jesusrojo.data.model.UiData
import com.jesusrojo.data.repository.UiDatasRepositoryImpl
import com.jesusrojo.local.room.UiDataDao
import com.jesusrojo.local.room.model.UiDataDbo
import com.jesusrojo.local.room.mapper.UiDataDboToUiDataMapper
import com.jesusrojo.local.room.mapper.UiDataToDboMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject

class UiDatasLocalDataSourceImpl @Inject constructor(
    private val dao: UiDataDao,
    private val mapperUiDataDboToUiData: UiDataDboToUiDataMapper,
    private val mapperUiDataToDbo: UiDataToDboMapper
): UiDatasLocalDataSource {


    private val logger = Logger.getLogger(UiDatasRepositoryImpl::class.java.simpleName)
    private fun l(msg: String) = logger.log(Level.INFO, "$msg ##")

    override suspend fun fetchDatasDB(): List<UiData> {
        l("fetchDatasDB")

        val uiDataDbos =  dao.fetchAllDB()

        var results: List<UiData> = emptyList()
        if(uiDataDbos != null && uiDataDbos.isNotEmpty()) {
            results = mapperUiDataDboToUiData(uiDataDbos)
        }
        return results
    }

    override suspend fun fetchDatasDBFlow(): Flow<List<UiData>> = flow {
        delay(2000)
   //     emit(FakeUiData.getFakeUiDatas(10, "local"))
    }

    override suspend fun saveToDB(uiDatas: List<UiData>){
        l("saveToDB")
        val uiDataDbos: List<UiDataDbo> =  mapperUiDataToDbo(uiDatas)
        dao.saveAllDB(uiDataDbos)
//        for (uiDataDbo in uiDataDbos) {
//            dao.saveOneDB(uiDataDbo)
//        }
    }

    override suspend fun deleteAllDB() {
        l("deleteAllDB")
        dao.deleteAllDB()
    }
}