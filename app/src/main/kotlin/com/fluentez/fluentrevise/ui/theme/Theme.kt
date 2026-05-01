package com.fluentez.fluentrevise.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = ButtonStartColor,
    onPrimary = Color.White,
    secondary = TermTagColor,
    onSecondary = Color.White,
    background = AppBackground,
    onBackground = TextDark,
    surface = Color.White,
    onSurface = TextDark
)

@Composable
fun FluentReviseTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}
