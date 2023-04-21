package com.mooooong.fastwash.features.reserve.mvi

data class ReserveState(
    val isLoading: Boolean = false,
)

sealed class ReserveSideEffect {

}