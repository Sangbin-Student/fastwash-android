package com.mooooong.fastwash.features.main.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.RoomDatabase
import com.mooooong.fastwash.features.main.mvi.MainSideEffect
import com.mooooong.fastwash.features.main.mvi.MainState
import com.mooooong.fastwash.local.FastWashDatabase
import com.mooooong.fastwash.network.RetrofitClient
import com.mooooong.fastwash.network.request.AssignBluetoothIdRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class MainViewModel @Inject constructor() : ContainerHost<MainState, MainSideEffect>, ViewModel() {

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

    fun getIsReserved(application: Application) = intent {
        val fastWashDatabase = FastWashDatabase.getInstance(application)
        val reserveDao = fastWashDatabase?.reserveDao() ?: return@intent

        kotlin.runCatching {
            reserveDao.getIsReserve()
        }.onSuccess {
            it?.let {
                if (it.time == LocalDate.now().toString()) {
                    // 지난 시간 저장이 현재 날짜와 같은 경우.. -> 예약이 되어 있는 경우
                    reduce {
                        state.copy(
                            isReserved = true
                        )
                    }
                } else {
                    // 예약이 되어 있지 않는 경우
                    reduce {
                        state.copy(
                            isReserved = false
                        )
                    }
                }
            } ?: reduce {
                state.copy(
                    isReserved = false
                )
            }

        }.onFailure {
            postSideEffect(MainSideEffect.OnFailEvent(it))
        }
    }
}