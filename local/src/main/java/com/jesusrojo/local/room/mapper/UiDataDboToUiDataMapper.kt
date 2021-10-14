package com.jesusrojo.local.room.mapper

import com.jesusrojo.data.model.UiData
import com.jesusrojo.local.room.model.UiDataDbo
import javax.inject.Inject


class UiDataDboToUiDataMapper @Inject constructor(): Function1<List<UiDataDbo>, List<UiData>> {

    override fun invoke(datas: List<UiDataDbo>): List<UiData> {
        return datas.map {
            var dataId: Int = 0
            var title = ""
            var description = ""
            var url = ""

            val dataIdRaw = it?.dataId
            if (dataIdRaw != null) dataId = dataIdRaw

            var raw: String?
            raw = it?.title
            if (raw != null) title = raw

            raw = it?.description
            if (raw != null) description = raw

            raw = it?.imgUrl
            if (raw != null) url = raw
         
            UiData(dataId, title, description, url)
        }
    }
}