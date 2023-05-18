package com.mooooong.fastwash.features.assign.view

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mooooong.fastwash.R
import com.mooooong.fastwash.features.assign.vm.AssignViewModel
import kr.hs.dgsw.smartschool.components.component.basic.input.area.DodamSelectArea
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.theme.Title3
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen

@SuppressLint("MissingPermission")
@Composable
fun AssignScreen(
    navController: NavController,
    assignViewModel: AssignViewModel = hiltViewModel(),
) {
    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    val laundryMachine = listOf(
        "[3층] 우측 세면실 1번",
        "[3층] 우측 세면실 2번",
        "[3층] 우측 세면실 3번",
        "[3층] 좌측 세면실 1번",
        "[3층] 좌측 세면실 2번",
        "[3층] 좌측 세면실 3번",
        "[4층] 우측 세면실 1번",
        "[4층] 우측 세면실 2번",
        "[4층] 우측 세면실 3번",
        "[4층] 좌측 세면실 1번",
        "[4층] 좌측 세면실 2번",
        "[4층] 좌측 세면실 3번",
        "[5층] 우측 세면실 1번",
        "[5층] 우측 세면실 2번",
        "[5층] 우측 세면실 3번",
        "[5층] 좌측 세면실 1번",
        "[5층] 좌측 세면실 2번",
        "[5층] 좌측 세면실 3번",
    )

    val laundryTime = listOf(
        "오후 9시 20분",
        "오후 10시 20분",
        "오후 11시 20분",
    )

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
            Text(text = "세탁기 예약", style = DodamTheme.typography.headline1.copy(fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(50.dp))
            Title3(text = "세탁기")
            Spacer(modifier = Modifier.height(3.dp))
            DodamSelectArea(
                itemList = laundryMachine,
                modifier = Modifier
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth(),
                focusColor = Color(0xFF4CE2BE)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Title3(text = "희망 시간")
            Spacer(modifier = Modifier.height(3.dp))
            DodamSelectArea(
                itemList = laundryTime,
                modifier = Modifier
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth(),
                focusColor = Color(0xFF4CE2BE)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Title3(text = "세탁물 양")
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Label1(text = "중간 정도", textColor = Color.Magenta, modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .width(160.dp)
                        .height(200.dp)
                        .background(color = Color(0xFFFAF8F8), shape = RoundedCornerShape(10.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Box(modifier = Modifier
                            .weight(1f)
                            .height(15.dp)
                            .background(color = Color(0xFF636363), shape = RoundedCornerShape(5.dp))
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(modifier = Modifier
                            .size(30.dp)
                            .background(color = Color(0xFF636363), shape = RoundedCornerShape(5.dp))
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.White,
                                shape = CircleShape
                            )
                            .size(120.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
            // bluetoothAdapter.name
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DodamDimen.ScreenSidePadding),
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CE2BE)),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
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