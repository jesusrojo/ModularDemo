package com.jesusrojo.remote.uidata.api

import com.jesusrojo.remote.uidata.model.RawData
import retrofit2.Response
import retrofit2.http.GET

interface RawDatasApiService {

    @GET("users")
    suspend fun fetchDatasApi(): Response<List<RawData>>
}