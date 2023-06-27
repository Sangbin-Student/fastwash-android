package com.mooooong.fastwash.network.response

import com.google.gson.annotations.SerializedName

data class GetWasherResponse(
    @SerializedName("pages") val pages: Int,
    @SerializedName("washers") val washers: List<WasherResponse>,
)
