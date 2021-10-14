package com.jesusrojo.data.mapper

import com.jesusrojo.data.model.UiData
import com.jesusrojo.data.model.UiDataEntity
import javax.inject.Inject


class UiDataEntityToUiDataMapper @Inject constructor() : Function1<List<UiDataEntity>, List<UiData>> {

    override fun invoke(entityDatas: List<UiDataEntity>): List<UiData> {
        return entityDatas.map {
            var dataId: Int= 0
            var title = ""
            var description = ""
            var imgUrl = ""

            val dataIdRaw = it?.dataId
            if (dataIdRaw != null) dataId = dataIdRaw

            var raw: String?
            raw = it?.title
            if (raw != null) title = raw


            raw = it?.description
            if (raw != null) description = raw

            raw = it?.imgUrl
            if (raw != null) imgUrl = raw

            UiData(dataId, title,description, imgUrl)
        }
    }
}