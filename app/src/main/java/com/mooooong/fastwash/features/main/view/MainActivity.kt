package com.mooooong.fastwash.features.main.view

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.mooooong.fastwash.features.main.mvi.MainSideEffect
import com.mooooong.fastwash.features.main.mvi.MainState
import com.mooooong.fastwash.features.main.vm.MainViewModel
import com.mooooong.fastwash.ui.theme.FastwashTheme
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.observe(lifecycleOwner = this, state = ::render, sideEffect = ::handleSideEffect)
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        setContent {
            FastwashTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (ActivityCompat.checkSelfPermission(
                                this@MainActivity,
                                Manifest.permission.BLUETOOTH_CONNECT
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            Text(text = bluetoothAdapter.name ?: "응애")
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = {
                            mainViewModel.assignBluetoothId(bluetoothAdapter.name ?: "응애")
                        }) {
                            Text(text = "ㄱㄱㄱ")
                        }
                    }
                }
            }
        }
    }

    private fun render(state: MainState) {
        state.isReserved?.let {

        }
    }

    private fun handleSideEffect(sideEffect: MainSideEffect) {
        when (sideEffect) {
            is MainSideEffect.OnSuccessEvent -> {
                Toast.makeText(this, "성공했습니다.", Toast.LENGTH_SHORT).show()
            }
            is MainSideEffect.OnFailEvent -> {
                Toast.makeText(this, sideEffect.exception.stackTraceToString(), Toast.LENGTH_SHORT).show()
                Log.e("ErrorTrace", sideEffect.exception.stackTraceToString())
            }
        }
    }
}
