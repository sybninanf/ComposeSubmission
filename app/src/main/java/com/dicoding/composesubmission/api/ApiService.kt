package com.dicoding.composesubmission.api

import com.dicoding.composesubmission.models.ValoResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/agents?page=list")
    fun getAgentList(): Call<ValoResponse>

    @GET("v1/agents")
    suspend fun getAgentDetails(
        @Query("page") page: String = "details",
        @Query("uuid") uuid: String): Response<ValoResponse>


}