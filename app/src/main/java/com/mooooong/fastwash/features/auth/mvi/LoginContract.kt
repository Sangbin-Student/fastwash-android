package com.mooooong.fastwash.features.auth.mvi

data class LoginState(
    val loading: Boolean = false,
)

sealed class LoginSideEffect {
    data class OnFailEvent(val exception: Throwable) : LoginSideEffect()
    object OnSuccessLoginEvent : LoginSideEffect()
}
