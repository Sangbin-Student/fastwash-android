package com.mooooong.fastwash.features.auth.vm

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.mooooong.fastwash.features.auth.mvi.LoginSideEffect
import com.mooooong.fastwash.features.auth.mvi.LoginState
import com.mooooong.fastwash.features.main.view.MainActivity
import com.mooooong.fastwash.local.FastWashDatabase
import com.mooooong.fastwash.local.entity.TokenEntity
import com.mooooong.fastwash.network.RetrofitClient
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class LoginViewModel : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {

    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())
    private lateinit var refreshToken: String

    fun loginDodam(context: Context) {
        intent {
            reduce {
                state.copy(
                    loading = true
                )
            }
        }
        DAuth.loginWithDodam(
            context = context,
            onSuccess = { tokenResponse ->
                intent {
                    reduce {
                        state.copy(
                            loading = false
                        )
                    }
                }
                refreshToken = tokenResponse.refreshToken
                signInToken(context, tokenResponse.accessToken)
            },
            onFailure = { throwable ->
                intent {
                    reduce {
                        state.copy(
                            loading = false
                        )
                    }
                    postSideEffect(LoginSideEffect.OnFailEvent(throwable))
                }
            }
        )
    }

    private fun signInToken(context: Context, accessToken: String) = intent {
        reduce {
            state.copy(
                loading = true
            )
        }
        kotlin.runCatching {
            RetrofitClient.authService.signInToken(accessToken)
        }.onSuccess {
            reduce {
                state.copy(
                    loading = false,
                )
            }
            val fastWashDatabase = FastWashDatabase.getInstance(context)
            fastWashDatabase?.tokenDao()?.insert(
                TokenEntity(
                    token = it.accessToken,
                    refreshToken = refreshToken,
                )
            )
            postSideEffect(LoginSideEffect.OnSuccessLoginEvent)
        }.onFailure {
            reduce {
                state.copy(
                    loading = false
                )
            }
        }
    }
}
