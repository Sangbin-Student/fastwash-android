package com.mooooong.fastwash.network.response

import com.google.gson.annotations.SerializedName

data class GetWasherScheduleResponse(
    @SerializedName("time") val time: String,
    @SerializedName("users") val users: List<UserResponse>,
)
