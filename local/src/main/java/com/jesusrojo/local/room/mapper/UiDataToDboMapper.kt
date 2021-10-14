package com.jesusrojo.local.room.mapper

import com.jesusrojo.data.model.UiData
import com.jesusrojo.local.room.model.UiDataDbo
import javax.inject.Inject


class UiDataToDboMapper @Inject constructor(): Function1<List<UiData>, List<UiDataDbo>> {

    override fun invoke(datas: List<UiData>): List<UiDataDbo> {
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
         
            UiDataDbo(dataId, title, description, url)
        }
    }
}