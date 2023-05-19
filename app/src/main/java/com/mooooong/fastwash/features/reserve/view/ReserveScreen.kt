package com.mooooong.fastwash.features.reserve.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomDrawer
import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mooooong.fastwash.R
import com.mooooong.fastwash.features.reserve.vm.ReserveViewModel
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.Body1
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Headline1
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.theme.Title3
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReserveScreen(
    navController: NavController,
    reserveViewModel: ReserveViewModel = hiltViewModel(),
) {

    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.bluetooth)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    BottomDrawer(
        modifier = Modifier.fillMaxWidth(),
        drawerState = drawerState,
        drawerShape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp),
        drawerContent = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp)
                    .size(25.dp)
                    .align(Alignment.End)
                    .dodamClickable(rippleEnable = false) {
                        scope.launch { drawerState.close() }
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Headline1(text = "Discovery", textColor = Color(0xFF3784F6), modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(30.dp))
            LottieAnimation(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally),
                composition = composition,
                progress = { progress },
            )
            Spacer(modifier = Modifier.height(15.dp))
            Body1(text = "스마트폰을 키오스크 가까이\n가져다 대면 인식됩니다", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(15.dp))
        }
    ) {
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
                    text = "배정된 세탁기",
                    style = DodamTheme.typography.headline1.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(50.dp))

                Title3(text = "세탁기")
                Spacer(modifier = Modifier.height(3.dp))
                Label1(text = "[5층] 좌측 세면실 1번")

                Spacer(modifier = Modifier.height(20.dp))

                Title3(text = "시간대")
                Spacer(modifier = Modifier.height(3.dp))
                Label1(text = "오후 9시 20분 ~ 9시 30분")

                Spacer(modifier = Modifier.height(20.dp))

                Title3(text = "새탁 크루")
                Spacer(modifier = Modifier.height(3.dp))
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 5.dp)
                ) {
                    items(listOf("이승민", "강성훈", "한상빈", "최민재")) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.width(5.dp))
                            Box(
                                modifier = Modifier.size(4.dp)
                                    .background(color = Color.Black, shape = CircleShape)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Body1(text = it)
                        }
                    }
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = DodamDimen.ScreenSidePadding),
                onClick = { scope.launch { drawerState.open() } },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CE2BE)),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                )
            ) {
                Text(
                    text = "세탁기 문 열기",
                    color = DodamTheme.color.White,
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}