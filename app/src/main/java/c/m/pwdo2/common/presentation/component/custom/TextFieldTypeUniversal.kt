package c.m.pwdo2.common.presentation.component.custom

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import c.m.pwdo2.common.presentation.component.util.AdaptiveThemeColor
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldTypeUniversal(
    modifier: Modifier,
    textValue: String? = null,
    textValueChange: (String) -> Unit = {},
    placeholderText: String,
    singleLine: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    imeAction: ImeAction = ImeAction.Default,
    keyboardType: KeyboardType = KeyboardType.Text,
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
    TextField(
        value = textValue ?: "",
        onValueChange = textValueChange,
        placeholder = { Text(text = placeholderText) },
        enabled = enabled,
        singleLine = singleLine,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType,
        ),
        keyboardActions = keyboardActions,
        readOnly = readOnly,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    text = errorMessage ?: "",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        },
        //shape = CircleShape,
        colors = colors,
    )
}

@Preview(
    showBackground = true, name = "enable text field",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun TextFieldTypeUniversalPreview() {
    PWDO2Theme() {
        Surface {
            TextFieldTypeUniversal(
                modifier = Modifier,
                placeholderText = "Outline Text Placeholder",
            )
        }
    }
}

@Preview(
    showBackground = true, name = "dark mode enable text field",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun TextFieldTypeUniversalDarkModePreview() {
    TextFieldTypeUniversalPreview()
}

@Preview(
    showBackground = true, name = "enable text field error",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun TextFieldTypeUniversalErrorPreview() {
    PWDO2Theme() {
        Surface {
            TextFieldTypeUniversal(
                modifier = Modifier,
                textValue = "Text content",
                placeholderText = "Outline Text Placeholder",
                isError = true,
                errorMessage = "Error Message!",
            )
        }
    }
}

@Preview(
    showBackground = true, name = "dark mode enable text field error",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun TextFieldTypeUniversalErrorDarkModePreview() {
    TextFieldTypeUniversalErrorPreview()
}