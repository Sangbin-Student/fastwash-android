package com.mooooong.fastwash.features.main.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.mooooong.fastwash.features.main.mvi.MainSideEffect
import com.mooooong.fastwash.features.main.mvi.MainState
import com.mooooong.fastwash.features.main.vm.MainViewModel
import com.mooooong.fastwash.navigation.NavigationGraph
import com.mooooong.fastwash.ui.theme.FastwashTheme
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.getIsReserved(application)
        mainViewModel.observe(lifecycleOwner = this, state = ::render, sideEffect = ::handleSideEffect)
    }

    private fun render(state: MainState) {
        state.isReserved?.let {
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                setContent {
                    val navController = rememberNavController()
                    FastwashTheme {
                        Box {
                            NavigationGraph(navController = navController, isReserve = it)
                        }
                    }
                }
            }
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


/*

val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

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
*/
