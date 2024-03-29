package com.finance2up.authentication.presentation.util

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp

@Composable
@ReadOnlyComposable
fun fontSizeDimensionResource(@DimenRes id: Int) = with(LocalContext.current.resources) {
    (getDimension(id) / displayMetrics.scaledDensity).sp
}
fun formatDuration(timeInSeconds: Int): String {
    val minutes = timeInSeconds / 60
    val seconds = timeInSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}