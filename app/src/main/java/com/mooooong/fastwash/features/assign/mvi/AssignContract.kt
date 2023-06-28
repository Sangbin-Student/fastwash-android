package com.mooooong.fastwash.features.assign.mvi

import com.mooooong.fastwash.network.response.GetWasherResponse

data class AssignState(
    val isGetWashersLoading: Boolean = false,
    val assignLoading: Boolean = false,
    val washers: GetWasherResponse? = null,
    val currentSelectTime: String = "",
    val currentSelectLaundry: String = "",
)

sealed class AssignSideEffect {
    data class OnFailEvent(val throwable: Throwable) : AssignSideEffect()
    object OnSuccessAssignEvent : AssignSideEffect()
}
