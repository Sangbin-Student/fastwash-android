package com.mooooong.fastwash.network.request

import com.google.gson.annotations.SerializedName

data class AssignWasherRequest(
    @SerializedName("label") val label: String,
)
