package dev.vdbroek.pepijn98.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Converts dp to px using LocalDensity.
 */
val Int.px: Float @Composable get() = with(LocalDensity.current) { this@px.dp.toPx() }

val PaddingValues.top: Dp get() = calculateTopPadding()
val PaddingValues.end: Dp @Composable get() = calculateEndPadding(LocalLayoutDirection.current)
val PaddingValues.bottom: Dp get() = calculateBottomPadding()
val PaddingValues.start: Dp @Composable get() = calculateStartPadding(LocalLayoutDirection.current)

@Composable
fun PaddingValues.copy(
    start: Dp = this.start,
    top: Dp =this.top,
    end: Dp = this.end,
    bottom: Dp = this.bottom
): PaddingValues = PaddingValues(start, top, end, bottom)
