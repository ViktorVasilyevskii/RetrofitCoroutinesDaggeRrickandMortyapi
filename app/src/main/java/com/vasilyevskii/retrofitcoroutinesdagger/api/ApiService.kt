package com.vasilyevskii.retrofitcoroutinesdagger.api

import com.vasilyevskii.retrofitcoroutinesdagger.models.ResponseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getAllCHaracter(
        @Query("page") page: Int
    ): Response<ResponseApi>
}