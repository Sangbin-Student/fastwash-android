package com.mooooong.fastwash.network.service

import com.mooooong.fastwash.network.request.AssignBluetoothIdRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AssignmentService {

    @POST("assignments/me/bluetooth-id")
    suspend fun assignBluetoothId(
        @Body assignBluetoothIdRequest: AssignBluetoothIdRequest
    )
}