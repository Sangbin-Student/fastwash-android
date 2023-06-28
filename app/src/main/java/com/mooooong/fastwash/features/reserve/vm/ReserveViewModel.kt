package com.mooooong.fastwash.features.reserve.vm

import androidx.lifecycle.ViewModel
import com.mooooong.fastwash.features.reserve.mvi.ReserveSideEffect
import com.mooooong.fastwash.features.reserve.mvi.ReserveState
import com.mooooong.fastwash.network.RetrofitClient
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
            postSideEffect(ReserveSideEffect.OnFailEvent(it))
            reduce {
                state.copy(
                    isGetMyAssignLoading = false
                )
            }
        }
    }
}