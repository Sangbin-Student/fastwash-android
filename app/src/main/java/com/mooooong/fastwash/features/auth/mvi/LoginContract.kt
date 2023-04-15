package com.mooooong.fastwash.features.auth.mvi

data class LoginState(
    val loading: Boolean = false,
)

sealed class LoginSideEffect {
    data class ShowException(val exception: Throwable): LoginSideEffect()
}
