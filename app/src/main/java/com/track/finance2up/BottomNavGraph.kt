package com.track.finance2up

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.track.account.AccountScreen
import com.track.more.MoreScreen
import com.track.stats.StatsScreen
import com.track.trans.TransScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Trans.route
    ) {
        composable(route = BottomBarScreen.Trans.route) {
            TransScreen()
        }
        composable(route = BottomBarScreen.Stats.route) {
            StatsScreen()
        }
        composable(route = BottomBarScreen.Account.route) {
            AccountScreen()
        }
        composable(route = BottomBarScreen.More.route) {
            MoreScreen()
        }
    }
}
