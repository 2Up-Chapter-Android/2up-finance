package com.track.finance2up

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route : String,
    val title : String,
    val icon : ImageVector
) {
    object Trans: BottomBarScreen(
        route = "Trans",
        title = "Trans",
        icon = Icons.Filled.Add
    )
    object Stats: BottomBarScreen(
        route = "Stats",
        title = "Stats",
        icon = Icons.Filled.Build
    )
    object Account : BottomBarScreen(
        route = "Account",
        title = "Account",
        icon = Icons.Filled.AccountBox
    )
    object More: BottomBarScreen(
        route = "More",
        title = "More",
        icon = Icons.Filled.MoreVert
    )
}