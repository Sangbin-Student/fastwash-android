package com.mooooong.fastwash.features.reserve.mvi

import com.mooooong.fastwash.network.response.UserResponse
import com.mooooong.fastwash.network.response.WasherResponse

data class ReserveState(
    val isGetMyAssignLoading: Boolean = false,
    val seed: Int = 0,
    val time: String = "",
    val users: List<UserResponse> = emptyList(),
    val washer: WasherResponse? = null,
)

sealed class ReserveSideEffect {
    data class OnFailEvent(val throwable: Throwable) : ReserveSideEffect()
}