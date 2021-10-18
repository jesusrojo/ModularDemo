package com.jesusrojo.remote.jsonplaceholder.api

import com.jesusrojo.remote.jsonplaceholder.model.UserRawData
import retrofit2.Response
import retrofit2.http.GET

interface UsersApiService {

    @GET("users")
    suspend fun fetchUsersApi(): Response<List<UserRawData>>
}