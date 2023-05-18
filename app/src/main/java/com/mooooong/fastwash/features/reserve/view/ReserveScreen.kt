package com.mooooong.fastwash.features.reserve.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mooooong.fastwash.features.reserve.vm.ReserveViewModel

@Composable
fun ReserveScreen(
    navController: NavController,
    reserveViewModel: ReserveViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.error),
    ) {

    }
}