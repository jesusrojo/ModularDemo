package com.jesusrojo.data.mapper

import com.jesusrojo.data.model.UiData
import com.jesusrojo.data.model.UiDataEntity
import javax.inject.Inject


class UiDataEntityToUiDataMapper @Inject constructor() : Function1<List<UiDataEntity>, List<UiData>> {

    override fun invoke(entityDatas: List<UiDataEntity>): List<UiData> {
        return entityDatas.map {
            var dataId: Int= 0
            var name = ""

            val dataIdRaw = it?.dataId
            if (dataIdRaw != null) dataId = dataIdRaw

            var raw: String?
            raw = it?.name
            if (raw != null) name = raw

         
            UiData(dataId, name)
        }
    }
}