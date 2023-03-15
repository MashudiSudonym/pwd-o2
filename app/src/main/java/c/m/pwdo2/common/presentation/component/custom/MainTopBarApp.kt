package c.m.pwdo2.common.presentation.component.custom

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import c.m.pwdo2.R
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme
import c.m.pwdo2.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainTopBarApp(navigator: DestinationsNavigator, title: String) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            Icon(
                imageVector = Icons.Rounded.Settings,
                contentDescription = stringResource(id = R.string.settings_menu),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable {
                        navigator.navigate(SettingsScreenDestination)
                    }
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.background)
    )
}

@Preview(
    showBackground = true, name = "enable text field",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun PreviewMainTopBarApp() {
    PWDO2Theme {
        Surface {
            MainTopBarApp(
                navigator = EmptyDestinationsNavigator,
                title = stringResource(id = R.string.oxygen_requirement_and_area)
            )
        }
    }
}