package com.mooooong.fastwash.network.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("bluetoothDeviceName") val bluetoothDeviceName: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("webOtpSeed") val webOtpSeed: Int,
)
