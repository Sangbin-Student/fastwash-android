package com.mooooong.fastwash.features.main.vm

import androidx.lifecycle.ViewModel
import com.mooooong.fastwash.features.main.mvi.MainSideEffect
import com.mooooong.fastwash.features.main.mvi.MainState
import com.mooooong.fastwash.network.RetrofitClient
import com.mooooong.fastwash.network.request.AssignBluetoothIdRequest
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel : ContainerHost<MainState, MainSideEffect>, ViewModel() {

    override val container = container<MainState, MainSideEffect>(MainState())

    fun assignBluetoothId(bluetoothId: String) = intent {
        kotlin.runCatching {
            RetrofitClient.assignmentService.assignBluetoothId(
                AssignBluetoothIdRequest(bluetoothId)
            )
        }.onSuccess {
            postSideEffect(MainSideEffect.OnSuccessEvent)
        }.onFailure {
            postSideEffect(MainSideEffect.OnFailEvent(it))
        }
    }
}