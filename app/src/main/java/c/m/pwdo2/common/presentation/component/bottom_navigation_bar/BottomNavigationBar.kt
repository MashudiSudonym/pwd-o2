package c.m.pwdo2.common.presentation.component.bottom_navigation_bar

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import c.m.pwdo2.*
import c.m.pwdo2.common.presentation.component.util.AdaptiveThemeColor
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme
import c.m.pwdo2.destinations.Destination
import com.ramcosta.composedestinations.navigation.navigate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentDestination: Destination =
        navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination

    NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
        BottomNavigationBarDestination.values().forEach { destination ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = AdaptiveThemeColor(
                        isDarkTheme = isSystemInDarkTheme(),
                        onDark = Color(0xFF9E9D9E),
                        onLight = Color.LightGray,
                    ),
                ),
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigate(destination.direction) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = stringResource(id = destination.label),
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = destination.label),
                        style = MaterialTheme.typography.labelMedium,
                    )
                },
            )
        }
    }
}

@Preview(
    showBackground = true, name = "enable text field",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun PreviewBottomNavigationBar() {
    PWDO2Theme {
        Surface {
            BottomNavigationBar(navController = rememberNavController())
        }
    }
}