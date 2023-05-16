package com.mooooong.fastwash.features.main.mvi

data class MainState(
    val isReserved: Boolean? = null,
    val loading: Boolean = false,
)

sealed class MainSideEffect {
    object OnSuccessEvent : MainSideEffect()
    data class OnFailEvent(val exception: Throwable) : MainSideEffect()
}
