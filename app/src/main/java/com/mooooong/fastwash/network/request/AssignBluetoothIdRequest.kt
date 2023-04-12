package com.mooooong.fastwash.network.request

import com.google.gson.annotations.SerializedName

data class AssignBluetoothIdRequest(
    @field:SerializedName("identifier") val id: String,
)
