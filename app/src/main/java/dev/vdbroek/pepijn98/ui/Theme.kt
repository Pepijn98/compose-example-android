package dev.vdbroek.pepijn98.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ThemeState {
    var isDark by mutableStateOf(true)
    var override by mutableStateOf(false)
}

private val DarkColorPalette = darkColors(
    primary = ColorUI.blue200,
    primaryVariant = ColorUI.blue700,
    secondary = ColorUI.orange200,
    background = ColorUI.darkBackground,
    surface = ColorUI.darkSurface
)

private val LightColorPalette = lightColors(
    primary = ColorUI.blue200,
    primaryVariant = ColorUI.blue700,
    secondary = ColorUI.orange200
)

@Composable
fun Pepijn98Theme(content: @Composable () -> Unit) {
    ThemeState.isDark = if (ThemeState.override) ThemeState.isDark else isSystemInDarkTheme()

    val colors = if (ThemeState.isDark) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
