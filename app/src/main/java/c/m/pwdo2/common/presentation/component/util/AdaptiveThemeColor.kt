package c.m.pwdo2.common.presentation.component.util


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AdaptiveThemeColor(isDarkTheme: Boolean, onDark: Color, onLight: Color): Color =
    if (isDarkTheme) {
        onDark
    } else {
        onLight
    }