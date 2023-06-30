package com.mooooong.fastwash.features.reserve.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mooooong.fastwash.features.reserve.mvi.ReserveSideEffect
import com.mooooong.fastwash.features.reserve.mvi.ReserveState
import com.mooooong.fastwash.network.RetrofitClient
import com.mooooong.fastwash.network.request.AssignBluetoothIdRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class ReserveViewModel @Inject constructor() : ContainerHost<ReserveState, ReserveSideEffect>, ViewModel() {

    override val container: Container<ReserveState, ReserveSideEffect> = container(ReserveState())

    init {
        getMyAssign()
    }

    private fun getMyAssign() = intent {
        reduce {
            state.copy(
                isGetMyAssignLoading = true
            )
        }
        kotlin.runCatching {
            RetrofitClient.assignmentService.getMyAssign()
        }.onSuccess {
            reduce {
                state.copy(
                    isGetMyAssignLoading = false,
                    seed = it.seed,
                    time = it.time,
                    users = it.users,
                    washer = it.washer,
                )
            }
        }.onFailure {
            Log.d("AssignTest", "${it.message}")
            if (it.message == "No such assignment")
                reduce {
                    state.copy(enableOpen = false)
                }
            else
                reduce {
                    state.copy(enableOpen = true)
                }
            postSideEffect(ReserveSideEffect.OnFailEvent(it))
            reduce {
                state.copy(
                    isGetMyAssignLoading = false
                )
            }
        }
    }

    fun sendDeviceName(deviceName: String) = intent {
        kotlin.runCatching {
            RetrofitClient.assignmentService.assignBluetoothId(
                AssignBluetoothIdRequest(deviceName)
            )
        }.onSuccess {
            postSideEffect(ReserveSideEffect.SuccessSendDeviceName)
        }.onFailure {
            postSideEffect(ReserveSideEffect.OnFailEvent(it))
        }
    }
}