package c.m.pwdo2.common.presentation.component.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ActivatedConditionColor(isEnable: Boolean, enabledColor: Color, disabledColor: Color): Color =
    if (isEnable) {
        enabledColor
    } else {
        disabledColor
    }