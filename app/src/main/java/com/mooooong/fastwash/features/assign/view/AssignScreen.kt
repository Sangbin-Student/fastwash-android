package com.mooooong.fastwash.features.assign.view

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mooooong.fastwash.features.assign.mvi.AssignSideEffect
import com.mooooong.fastwash.features.assign.vm.AssignViewModel
import com.mooooong.fastwash.navigation.NavGroup
import com.mooooong.fastwash.ui.components.LoadInFullScreen
import kr.hs.dgsw.smartschool.components.component.basic.input.area.DodamSelectArea
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Title3
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@SuppressLint("MissingPermission")
@Composable
fun AssignScreen(
    navController: NavController,
    assignViewModel: AssignViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val state = assignViewModel.collectAsState().value
    assignViewModel.collectSideEffect { handleSideEffect(navController, context, it) }

    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    val laundryTime = listOf(
        LaundryTime("오전 8시 00분", "08:00"),
        LaundryTime("오전 10시 00분", "10:00"),
        LaundryTime("오후 12시 00분", "12:00"),
        LaundryTime("오후 2시 30분", "14:30"),
        LaundryTime("오후 4시 00분", "16:00"),
        LaundryTime("오후 5시 30분", "17:30"),
        LaundryTime("오후 7시 00분", "19:00"),
        LaundryTime("오후 9시 20분", "21:20"),
        LaundryTime("오후 11시 00분", "23:00"),
    )

    var boxSize by remember { mutableStateOf(100.dp) }

    if (state.isGetWashersLoading || state.assignLoading) {
        LoadInFullScreen()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = DodamDimen.ScreenSidePadding)
            ) {
                Spacer(modifier = Modifier.height(70.dp))
                Text(
                    text = "세탁기 예약",
                    style = DodamTheme.typography.headline1.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.dodamClickable { navController.navigate(NavGroup.Washing.RESERVE) }
                )
                Spacer(modifier = Modifier.height(50.dp))
                Title3(text = "세탁기")
                Spacer(modifier = Modifier.height(3.dp))
                if (state.washers != null) {
                    DodamSelectArea(
                        itemList = state.washers.washers.map { it.name },
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .fillMaxWidth(),
                        focusColor = Color(0xFF4CE2BE)
                    ) { laundry ->
                        assignViewModel.updateLaundry(laundry)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Title3(text = "희망 시간")
                Spacer(modifier = Modifier.height(3.dp))
                DodamSelectArea(
                    itemList = laundryTime.map { it.name },
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .fillMaxWidth(),
                    focusColor = Color(0xFF4CE2BE)
                ) { time ->
                    assignViewModel.updateTime(time)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Title3(text = "세탁물 양")
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Title3(
                        text = when {
                            boxSize.value in 35.0..75.0 -> "중간 양의 세탁물"
                            boxSize.value > 75.0 -> "적은 정도의 세탁물"
                            boxSize.value < 35.0 -> "많은 양의 세탁물"
                            else -> "중간 정도의 세탁물"
                        }, textColor = Color.Magenta, modifier = Modifier.weight(1f)
                    )
                    Column(
                        modifier = Modifier
                            .width(160.dp)
                            .height(200.dp)
                            .background(
                                color = Color(0xFFFAF8F8),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(15.dp)
                                    .background(
                                        color = Color(0xFF636363),
                                        shape = RoundedCornerShape(5.dp)
                                    )
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .background(
                                        color = Color(0xFF636363),
                                        shape = RoundedCornerShape(5.dp)
                                    )
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(0xFF4CE2BE),
                                    shape = CircleShape
                                )
                                .size(120.dp)
                                .align(Alignment.CenterHorizontally)
                                .clip(shape = CircleShape)
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(boxSize)
                                    .pointerInput(Unit) {
                                        detectDragGestures { change, dragAmount ->
                                            if (change.positionChange() != Offset.Zero) change.consume()
                                            // 드래그한 만큼 박스 크기 조절
                                            boxSize += dragAmount.y.dp
                                        }
                                    }
                                    .background(color = Color.White)
                            )
                        }
                    }
                }
                // bluetoothAdapter.name
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = DodamDimen.ScreenSidePadding),
                onClick = {
                    val quantity = when {
                        boxSize.value in 35.0..75.0 -> 50
                        boxSize.value > 75.0 -> 25
                        boxSize.value < 35.0 -> 75
                        else -> 50
                    }

                    val time = laundryTime.find { it.name == state.currentSelectTime }
                    Log.d("AssignTest", "time : ${time.toString()} currentSelectTime : ${state.currentSelectTime}")

                    val washer = state.washers?.washers?.find { it.name == state.currentSelectLaundry }
                    Log.d("AssignTest", washer.toString())

                    assignViewModel.assignMe(
                        quantity = quantity,
                        time = laundryTime.find { it.name == state.currentSelectTime }?.format ?: return@Button,
                        washerId = state.washers?.washers?.find { it.name == state.currentSelectLaundry }?.id ?: return@Button,
                    )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CE2BE)),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                )
            ) {
                Text(
                    text = "배정 신청",
                    color = DodamTheme.color.White,
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

private fun handleSideEffect(navController: NavController, context: Context, sideEffect: AssignSideEffect) {
    when (sideEffect) {
        is AssignSideEffect.OnFailEvent -> {
            Toast.makeText(context, sideEffect.throwable.message, Toast.LENGTH_SHORT).show()
        }
        is AssignSideEffect.OnSuccessAssignEvent -> {
            Log.d("AssignTest", "handleSideEffect: Hello")
            navController.navigate(NavGroup.Washing.RESERVE)
        }
    }
}

data class LaundryTime(
    val name: String,
    val format: String,
)