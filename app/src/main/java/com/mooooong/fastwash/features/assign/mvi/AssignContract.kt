package com.mooooong.fastwash.features.assign.mvi

data class AssignState(
    val isLoading: Boolean = false,
)

sealed class AssignSideEffect {

}