package com.track.finance2up

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

open class BottomBarScreen(
    val route : String,
    val title : String,
    val icon : ImageVector
) {
    object Trans: BottomBarScreen(
        route = "Trans",
        title = "Trans",
        icon = Icons.Default.Add
    )
    object Stats: BottomBarScreen(
        route = "Stats",
        title = "Stats",
        icon = Icons.Default.Build
    )
    object Account : BottomBarScreen(
        route = "Account",
        title = "Account",
        icon = Icons.Default.AccountBox
    )
    object More: BottomBarScreen(
        route = "More",
        title = "More",
        icon = Icons.Default.MoreVert
    )
}