package com.mooooong.fastwash.network.service

import com.mooooong.fastwash.network.request.AssignBluetoothIdRequest
import com.mooooong.fastwash.network.request.AssignMeRequest
import com.mooooong.fastwash.network.response.AssignResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AssignmentService {

    @GET("assignments/me")
    suspend fun getMyAssign(): AssignResponse

    // 세탁기 배정을 요청합니다.
    @POST("assignments/me")
    suspend fun assignMe(
        @Body assignMeRequest: AssignMeRequest,
    )

    // 세탁기 배정에 대한 블루투스 아이디를 지정합니다.
    @POST("assignments/me/bluetooth-id")
    suspend fun assignBluetoothId(
        @Body assignBluetoothIdRequest: AssignBluetoothIdRequest
    )
}