package com.mooooong.fastwash.network.request

import com.google.gson.annotations.SerializedName

data class AssignMeRequest(
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("time") val time: String,
    @SerializedName("washerId") val washerId: Int,
)
