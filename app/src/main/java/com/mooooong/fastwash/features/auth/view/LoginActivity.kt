package com.mooooong.fastwash.features.auth.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mooooong.fastwash.R
import com.mooooong.fastwash.features.main.view.MainActivity
import com.mooooong.fastwash.ui.theme.FastwashTheme
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMaxWidthButton
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.getCode
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.loginWithDodam
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.settingDAuth

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingDAuth(
            clientId = "4cc81ba00f174bc09340a1d6d75927665b7f7c0ca5a844a98af774648043a857",
            clientSecret = "351f049cf522460a94d461e65348ee712cefc8858766478fa4a608361e4738fb",
            redirectUrl = "http://localhost:3000/"
        )

        setContent {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(R.raw.washing_machine)
            )
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            FastwashTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = DodamDimen.ScreenSidePadding)
                        ) {
                            Spacer(modifier = Modifier.height(70.dp))
                            Text(text = "빨리빨래", style = DodamTheme.typography.headline1.copy(fontWeight = FontWeight.Bold))
                            Spacer(modifier = Modifier.height(7.dp))
                            Label1(text = "대소고 세탁기를 편리하고 간편하게.")
                            Spacer(modifier = Modifier.height(90.dp))
                            LottieAnimation(
                                modifier = Modifier
                                    .size(400.dp)
                                    .align(Alignment.CenterHorizontally),
                                composition = composition,
                                progress = { progress },
                            )
                        }
                        DodamMaxWidthButton(text = "DAuth로 로그인하기", modifier = Modifier.padding(horizontal = DodamDimen.ScreenSidePadding)) {
                            loginWithDodam(
                                context = this@LoginActivity,
                                onSuccess = { tokenResponse ->
                                    getCode(
                                        context = this@LoginActivity,
                                        onSuccess = { code ->

                                        },
                                        onFailure = { throwable ->

                                        }
                                    )
                                },
                                onFailure = { throwable ->

                                }
                            )
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }
    }
}
