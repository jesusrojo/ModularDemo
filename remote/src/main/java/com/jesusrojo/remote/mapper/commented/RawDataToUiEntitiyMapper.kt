//package com.jesusrojo.remote.mapper
//
//import com.jesusrojo.data.model.UiData
//import com.jesusrojo.remote.model.RawData
//import javax.inject.Inject
//
//class RawDataToUiEntitiyMapper @Inject constructor() : Function1<List<RawData>, List<UiData>> {
//
//    override fun invoke(rawDatas: List<RawData>): List<UiData> {
//        return rawDatas.map {
//            var userId: Int = -1
//            var name = ""
//            val description = it.toString()
//            UiData(userId, name, description, "")
////            var username = ""
////            var email = ""
////            var phone = ""
////            var website = ""
////            var address = ""
////            var company = ""
////
////            val userIdRaw = it.userId
////            if (userIdRaw != null) userId = userIdRaw
////
////            val nameRaw = it.name
////            if (nameRaw != null) name = nameRaw + " " + getNextCount()
////
////            val userNameRaw = it.userName
////            if (userNameRaw != null) username = userNameRaw
////
////            val emailRaw = it.email
////            if (emailRaw != null) email = emailRaw
////
////            val phoneRaw = it.phone
////            if (phoneRaw != null) phone = phoneRaw
////
////            val websiteRaw = it.website
////            if (websiteRaw != null) website = websiteRaw
////
////            val addressRaw = it.address?.toString()
////            if (addressRaw != null) address = addressRaw
////
////            val companyRaw = it.company?.toString()
////            if (companyRaw != null) company = companyRaw
//        }
//    }
//
//    private fun getNextCount(): Int {
//        countStatic++
//        return countStatic
//    }
//
//    companion object {
//        var countStatic = 0
//    }
//}