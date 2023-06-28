package com.mooooong.fastwash.features.assign.vm

import androidx.lifecycle.ViewModel
import com.mooooong.fastwash.features.assign.mvi.AssignSideEffect
import com.mooooong.fastwash.features.assign.mvi.AssignState
import com.mooooong.fastwash.network.RetrofitClient
import com.mooooong.fastwash.network.request.AssignMeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class AssignViewModel @Inject constructor() : ContainerHost<AssignState, AssignSideEffect>, ViewModel() {

    override val container: Container<AssignState, AssignSideEffect> = container(AssignState())

    init {
        getWashers()
    }

    fun assignMe(quantity: Int, time: String, washerId: Int) = intent {
        reduce {
            state.copy(
                assignLoading = true,
            )
        }
        kotlin.runCatching {
            RetrofitClient.assignmentService.assignMe(
                AssignMeRequest(
                    quantity = quantity,
                    time = time,
                    washerId = washerId,
                )
            )
        }.onSuccess {
            reduce {
                state.copy(
                    assignLoading = false,
                )
            }
            postSideEffect(AssignSideEffect.OnSuccessAssignEvent)
        }.onFailure {
            reduce {
                state.copy(
                    assignLoading = false,
                )
            }
            postSideEffect(AssignSideEffect.OnFailEvent(it))
        }
    }

    private fun getWashers() = intent {
        reduce {
            state.copy(
                isGetWashersLoading = true,
            )
        }
        kotlin.runCatching {
            RetrofitClient.washerService.getWashers(0)
        }.onSuccess {
            reduce {
                state.copy(
                    isGetWashersLoading = false,
                    washers = it
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    isGetWashersLoading = false,
                )
            }
            postSideEffect(AssignSideEffect.OnFailEvent(it))
        }
    }

    fun updateTime(time: String) = intent {
        reduce {
            state.copy(
                currentSelectTime = time
            )
        }
    }

    fun updateLaundry(laundry: String) = intent {
        reduce {
            state.copy(
                currentSelectLaundry = laundry
            )
        }
    }
}
