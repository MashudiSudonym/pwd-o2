package c.m.pwdo2.common.presentation.component.util

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationContentWrapperCustom(
    content: @Composable BoxScope.() -> Unit,
) {

    val isKeyboardOpen by keyboardAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = if (isKeyboardOpen) 0.dp else 56.dp),
        content = content,
    )
}

@Composable
fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}