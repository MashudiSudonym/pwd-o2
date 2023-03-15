package c.m.pwdo2.common.presentation.component.custom

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import c.m.pwdo2.common.presentation.component.util.AdaptiveThemeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldTypePassword(
    modifier: Modifier,
    textValue: String? = null,
    textValueChange: (String) -> Unit = {},
    placeholderText: String,
    isError: Boolean = false,
    errorMessage: String? = null,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    readOnly: Boolean = false,
    enabled: Boolean = true,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = AdaptiveThemeColor(
            isDarkTheme = isSystemInDarkTheme(),
            onDark = Color.White,
            onLight = Color.Black,
        ),
        placeholderColor = Color.LightGray,
        focusedBorderColor = AdaptiveThemeColor(
            isDarkTheme = isSystemInDarkTheme(),
            onDark = Color.White,
            onLight = Color.Black,
        ),
        unfocusedBorderColor = AdaptiveThemeColor(
            isDarkTheme = isSystemInDarkTheme(),
            onDark = Color.White,
            onLight = Color.Black,
        ),
        errorBorderColor = Color.Red,
        errorSupportingTextColor = Color.Red,
        cursorColor = AdaptiveThemeColor(
            isDarkTheme = isSystemInDarkTheme(),
            onDark = Color.White,
            onLight = Color.Black,
        ),
    ),
) {
    val showPassword = remember { mutableStateOf(false) }
    val colorIcon = AdaptiveThemeColor(
        isDarkTheme = isSystemInDarkTheme(),
        onDark = Color.White,
        onLight = Color.Black,
    )

    TextField(
        value = textValue ?: "",
        onValueChange = textValueChange,
        placeholder = { Text(text = placeholderText) },
        enabled = enabled,
        singleLine = true,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = KeyboardType.Password,
        ),
        keyboardActions = keyboardActions,
        readOnly = readOnly,
        isError = isError,
        trailingIcon = {
            val (icon, iconColor) = if (showPassword.value) {
                Pair(
                    Icons.Filled.VisibilityOff,
                    colorIcon,
                )
            } else {
                Pair(Icons.Filled.Visibility, colorIcon)
            }

            IconButton(onClick = { showPassword.value = !showPassword.value }) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = iconColor
                )
            }
        },
        supportingText = {
            if (isError) {
                Text(
                    text = errorMessage ?: "",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        },
        shape = ShapeDefaults.ExtraLarge,
        colors = colors,
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
    )
}
