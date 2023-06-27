package com.mooooong.fastwash.network.response

import com.google.gson.annotations.SerializedName

data class WasherResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)
