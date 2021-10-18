package com.jesusrojo.remote.api

import com.jesusrojo.remote.model.RawData
import com.jesusrojo.remote.model.UserRawData
import retrofit2.Response
import retrofit2.http.GET

interface UsersApiService {

    @GET("users")
    suspend fun fetchUsersApi(): Response<List<UserRawData>>
}