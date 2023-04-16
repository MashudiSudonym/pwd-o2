package c.m.pwdo2.common.presentation.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import c.m.pwdo2.NavGraphs
import c.m.pwdo2.common.presentation.component.bottom_navigation_bar.BottomNavigationBar
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme
import c.m.pwdo2.common.presentation.view_model.WrapperViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import timber.log.Timber

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph
@Destination
@Composable
fun WrapperScreen(wrapperViewModel: WrapperViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val wrapperUIState by wrapperViewModel.wrapperUIState.collectAsState()

    // Check user login status
    when {
        wrapperUIState.isLoading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator()
            }
        }
        wrapperUIState.isError -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "Error! tutup dan buka kembali aplikasi")
            }
        }
        wrapperUIState.isSuccess -> {
            Timber.w("cscsa $currentRoute")

            Scaffold(
                bottomBar = {
                    when (currentRoute) {
                        "oxygen_requirement_and_area_screen" -> {
                            BottomNavigationBar(navController = navController)
                        }
                        "area_stp_requirement_screen" -> {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                },
            ) {
                DestinationsNavHost(
                    navController = navController,
                    navGraph = NavGraphs.root,
                )
            }
        }
    }
}

@Preview(
    showBackground = true, name = "enable text field",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun PreviewRootScreen() {
    PWDO2Theme {
        Surface {
            WrapperScreen()
        }
    }
}
