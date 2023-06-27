package com.mooooong.fastwash.network.response

import com.google.gson.annotations.SerializedName

data class AssignResponse(
    @SerializedName("seed") val seed: Int,
    @SerializedName("time") val time: String,
    @SerializedName("users") val users: List<UserResponse>,
    @SerializedName("washer") val washer: WasherResponse,
)
