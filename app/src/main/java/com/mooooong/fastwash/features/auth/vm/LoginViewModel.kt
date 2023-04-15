package com.mooooong.fastwash.features.auth.vm

import androidx.lifecycle.ViewModel
import com.mooooong.fastwash.features.auth.mvi.LoginSideEffect
import com.mooooong.fastwash.features.auth.mvi.LoginState
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class LoginViewModel : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {

    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())


}
