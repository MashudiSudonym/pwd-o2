package c.m.pwdo2.oxygen_requirement_and_area.presentation.screen

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
import c.m.pwdo2.R
import c.m.pwdo2.common.presentation.component.custom.MainTopBarApp
import c.m.pwdo2.common.presentation.component.util.BottomNavigationContentWrapperCustom
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme
import c.m.pwdo2.destinations.LoginScreenDestination
import c.m.pwdo2.destinations.OxygenRequirementAndAreaScreenDestination
import c.m.pwdo2.oxygen_requirement_and_area.presentation.component.*
import c.m.pwdo2.oxygen_requirement_and_area.presentation.view_model.OxygenRequirementAndAreaViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import timber.log.Timber

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph
@Destination
@Composable
fun OxygenRequirementAndAreaScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    navigator: DestinationsNavigator,
    oxygenRequirementAndAreaViewModel: OxygenRequirementAndAreaViewModel = hiltViewModel(),
) {
    val oxygenRequirementAndAreaUIState by oxygenRequirementAndAreaViewModel.oxygenRequirementAndAreaUIState.collectAsStateWithLifecycle()

    DisposableEffect(lifecycleOwner) {
        val observable = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                Timber.w("ON RESUME COMPOSE OXY")
                oxygenRequirementAndAreaViewModel.checkAuthentication()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observable)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observable)
        }
    }

    if (!oxygenRequirementAndAreaUIState.isLogin) {
        navigator.navigate(LoginScreenDestination) {
            popUpTo(OxygenRequirementAndAreaScreenDestination) {
                inclusive = true
            }
        }
    } else {
        BottomNavigationContentWrapperCustom {
            Scaffold(
                topBar = {
                    MainTopBarApp(
                        navigator = navigator,
                        title = stringResource(id = R.string.oxygen_requirement_and_area)
                    )
                },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                        .padding(start = 16.dp, end = 16.dp, top = 64.dp, bottom = 16.dp)
                ) {
                    AnimatedVisibility(visible = oxygenRequirementAndAreaUIState.isExpandedAllCard) {
                        CardInputParameters(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    bottom = 16.dp
                                ),
                            oxygenRequirementAndAreaViewModel,
                            oxygenRequirementAndAreaUIState,
                        )
                    }
                    SwipeRefresh(
                        state = rememberSwipeRefreshState(isRefreshing = oxygenRequirementAndAreaUIState.isRefresh),
                        onRefresh = {
                            oxygenRequirementAndAreaViewModel.getStandardValue()
                        },
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            when {
                                oxygenRequirementAndAreaUIState.isLoading -> {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    // Do nothing, just using swipe refresh indicator loading.
                                }
                                oxygenRequirementAndAreaUIState.isError -> {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(text = "Error! tutup dan buka kembali aplikasi")
                                }
                                oxygenRequirementAndAreaUIState.isSuccess -> {
                                    AnimatedVisibility(visible = oxygenRequirementAndAreaUIState.isExpandedAllCard) {
                                        CardDiffuserDepthEfficiency(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            oxygenRequirementAndAreaUIState,
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(16.dp))
                                    AnimatedVisibility(visible = oxygenRequirementAndAreaUIState.isExpandedAllCard) {
                                        CardTotalInfluentLoadingPerHour(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            oxygenRequirementAndAreaUIState,
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(16.dp))
                                    AnimatedVisibility(visible = oxygenRequirementAndAreaUIState.isExpandedAllCard) {
                                        CardAirRequirement(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            oxygenRequirementAndAreaUIState,
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(16.dp))
                                    AnimatedVisibility(visible = oxygenRequirementAndAreaUIState.isExpandedAllCard) {
                                        CardDiffuserRequirement(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            oxygenRequirementAndAreaUIState,
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(16.dp))
                                    AnimatedVisibility(visible = oxygenRequirementAndAreaUIState.isExpandedAllCard) {
                                        CardBlowerCapacityRequirement(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            oxygenRequirementAndAreaUIState,
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(16.dp))
                                    AnimatedVisibility(visible = oxygenRequirementAndAreaUIState.isExpandedAllCard) {
                                        CardAreaVolumeRequirement(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            oxygenRequirementAndAreaUIState,
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
    }
}

@Preview(
    showBackground = true, name = "enable text field",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    device = "id:Samsung A51"
)
@Composable
private fun PreviewOxygenRequirementAndAreaScreen() {
    PWDO2Theme {
        Surface {
            OxygenRequirementAndAreaScreen(navigator = EmptyDestinationsNavigator)
        }
    }
}