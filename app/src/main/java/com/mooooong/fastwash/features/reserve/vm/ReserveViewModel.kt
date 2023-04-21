package com.mooooong.fastwash.features.reserve.vm

import androidx.lifecycle.ViewModel
import com.mooooong.fastwash.features.reserve.mvi.ReserveSideEffect
import com.mooooong.fastwash.features.reserve.mvi.ReserveState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class ReserveViewModel : ContainerHost<ReserveState, ReserveSideEffect>, ViewModel() {

    override val container: Container<ReserveState, ReserveSideEffect> = container(ReserveState())

}