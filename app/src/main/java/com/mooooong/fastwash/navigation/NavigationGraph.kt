package com.mooooong.fastwash.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mooooong.fastwash.features.assign.view.AssignScreen
import com.mooooong.fastwash.features.reserve.view.ReserveScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = NavGroup.Washing.RESERVE) {

        composable(NavGroup.Washing.ASSIGN) {
            AssignScreen(navController = navController)
        }

        composable(NavGroup.Washing.RESERVE) {
            ReserveScreen(navController = navController)
        }
    }
}