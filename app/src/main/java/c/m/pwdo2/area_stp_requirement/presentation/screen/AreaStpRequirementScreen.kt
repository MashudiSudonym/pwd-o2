package c.m.pwdo2.area_stp_requirement.presentation.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import c.m.pwdo2.NavGraphs
import c.m.pwdo2.R
import c.m.pwdo2.area_stp_requirement.presentation.component.*
import c.m.pwdo2.area_stp_requirement.presentation.view_model.AreaStpRequirementViewModel
import c.m.pwdo2.common.presentation.component.custom.MainTopBarApp
import c.m.pwdo2.common.presentation.component.util.BottomNavigationContentWrapperCustom
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme
import c.m.pwdo2.destinations.LoginScreenDestination
import c.m.pwdo2.destinations.OxygenRequirementAndAreaScreenDestination
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import timber.log.Timber

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph()
@Destination
@Composable
fun AreaStpRequirementScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    navigator: DestinationsNavigator,
    areaStpRequirementViewModel: AreaStpRequirementViewModel = hiltViewModel(),
) {
    val areaStpRequirementUIState by areaStpRequirementViewModel.areaStpRequirementUIState.collectAsStateWithLifecycle()

    DisposableEffect(lifecycleOwner) {
        val observable = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                Timber.w("ON RESTART COMPOSE STP")
                areaStpRequirementViewModel.checkAuthentication()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observable)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observable)
        }
    }

    if (!areaStpRequirementUIState.isLogin) {
        navigator.navigate(LoginScreenDestination) {
            popUpTo(OxygenRequirementAndAreaScreenDestination) {
                inclusive = true
            }
        }
    } else {

        BottomNavigationContentWrapperCustom {
            Scaffold(topBar = {
                MainTopBarApp(
                    navigator = navigator,
                    title = stringResource(id = R.string.area_stp_requirement),
                )
            }) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                        .padding(start = 16.dp, end = 16.dp, top = 64.dp, bottom = 16.dp)
                ) {
                    SwipeRefresh(
                        state = rememberSwipeRefreshState(isRefreshing = areaStpRequirementUIState.isRefresh),
                        onRefresh = { areaStpRequirementViewModel.isRefreshingPage() },
                        indicatorPadding = PaddingValues(top = 176.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 16.dp)
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AnimatedVisibility(visible = areaStpRequirementUIState.isExpanded) {
                                CardWarning(
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            AnimatedVisibility(visible = areaStpRequirementUIState.isExpanded) {
                                AnaerobicRoomRequirementsCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    areaStpRequirementUIState,
                                    areaStpRequirementViewModel,
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            AnimatedVisibility(visible = areaStpRequirementUIState.isExpanded) {
                                GreaseTrapRoomRequirementsCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    areaStpRequirementUIState,
                                    areaStpRequirementViewModel,
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            AnimatedVisibility(visible = areaStpRequirementUIState.isExpanded) {
                                AerobRoomRequirementsCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    areaStpRequirementUIState,
                                    areaStpRequirementViewModel,
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            AnimatedVisibility(visible = areaStpRequirementUIState.isExpanded) {
                                SedimentationRoomRequirementsCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    areaStpRequirementUIState,
                                    areaStpRequirementViewModel,
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            AnimatedVisibility(visible = areaStpRequirementUIState.isExpanded) {
                                ChlorinationRoomRequirementsCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    areaStpRequirementUIState,
                                    areaStpRequirementViewModel,
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            AnimatedVisibility(visible = areaStpRequirementUIState.isExpanded) {
                                FiltrationTankRoomRequirementsCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    areaStpRequirementUIState,
                                    areaStpRequirementViewModel,
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            AnimatedVisibility(visible = areaStpRequirementUIState.isExpanded) {
                                RecycleTankRoomRequirementsCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    areaStpRequirementUIState,
                                    areaStpRequirementViewModel,
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "enable text field",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun PreviewAreaStpRequirementScreen() {
    PWDO2Theme {
        Surface {
            AreaStpRequirementScreen(navigator = EmptyDestinationsNavigator)
        }
    }
}