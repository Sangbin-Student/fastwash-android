package com.mooooong.fastwash.network.service

import com.mooooong.fastwash.network.request.AssignWasherRequest
import com.mooooong.fastwash.network.response.GetWasherResponse
import com.mooooong.fastwash.network.response.GetWasherScheduleResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface WasherService {

    @GET("washers")
    suspend fun getWashers(
        @Query("page") page: Int,
    ): GetWasherResponse

    @GET("washers/{washer-id}")
    suspend fun getScheduleByWasherId(
        @Path("washer-id") washerId: Int,
    ): GetWasherScheduleResponse

    @POST("washers/{washer-id}/assignment")
    suspend fun assignWasher(
        @Path("washer-id") washerId: Int,
        @Body assignWasherRequest: AssignWasherRequest,
    ): AssignWasherRequest
}