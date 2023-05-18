package com.mooooong.fastwash.features.assign.vm

import androidx.lifecycle.ViewModel
import com.mooooong.fastwash.features.assign.mvi.AssignSideEffect
import com.mooooong.fastwash.features.assign.mvi.AssignState
import com.mooooong.fastwash.features.reserve.mvi.ReserveSideEffect
import com.mooooong.fastwash.features.reserve.mvi.ReserveState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class AssignViewModel @Inject constructor() : ContainerHost<AssignState, AssignSideEffect>, ViewModel() {

    override val container: Container<AssignState, AssignSideEffect> = container(AssignState())

}