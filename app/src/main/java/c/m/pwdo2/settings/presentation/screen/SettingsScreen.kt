package c.m.pwdo2.settings.presentation.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import c.m.pwdo2.R
import c.m.pwdo2.common.presentation.component.custom.TextFieldTypeUniversal
import c.m.pwdo2.common.presentation.component.util.AdaptiveThemeColor
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme
import c.m.pwdo2.settings.presentation.event.InputSettingsEvent
import c.m.pwdo2.settings.presentation.event.SettingsUIEvent
import c.m.pwdo2.settings.presentation.view_model.SettingsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@ExperimentalMaterial3Api
@RootNavGraph
@Destination
@Composable
fun SettingsScreen(
    navigator: DestinationsNavigator,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scopeSettingScreen = rememberCoroutineScope()
    val settingsUIState by settingsViewModel.settingsUIState.collectAsStateWithLifecycle()
    val settingsUIEvent = settingsViewModel.settingsUIEvent

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable { navigator.popBackStack() }
                    )
                },
                title = { Text(text = stringResource(id = R.string.settings)) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            )
        }
    ) { _ ->
        scopeSettingScreen.launch {
            settingsUIEvent.collect { event ->
                when (event) {
                    SettingsUIEvent.SuccessSaved -> {
                        snackbarHostState.showSnackbar(
                            message = "Success Saved Setting Data",
                            duration = SnackbarDuration.Short,
                        )
                    }
                }
            }
        }

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = settingsUIState.isRefresh),
            onRefresh = { settingsViewModel.readSavedSettings() }) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp, end = 16.dp, top = 64.dp)
                    .imePadding()
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                when {
                    settingsUIState.isLoading -> {
                        CircularProgressIndicator()
                    }

                    settingsUIState.isError -> {
                        Text(
                            text = "Something wrong, restart application",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }

                    settingsUIState.isSuccess -> {
                        Text(
                            text = "Nilai Standar Syarat DLH ",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "BOD OUTLET",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            TextFieldTypeUniversal(
                                modifier = Modifier.fillMaxWidth(fraction = 0.85F),
                                placeholderText = "0",
                                textValue = if (settingsUIState.dlhBodOutlet == "0.0") {
                                    ""
                                } else {
                                    settingsUIState.dlhBodOutlet
                                },
                                textValueChange = {
                                    settingsViewModel.onInputFieldEvent(
                                        InputSettingsEvent.BodOutletFieldChange(it)
                                    )
                                },
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                            Text(text = "ppm", fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "COD OUTLET",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            TextFieldTypeUniversal(
                                modifier = Modifier.fillMaxWidth(fraction = 0.85F),
                                placeholderText = "0",
                                textValue = if (settingsUIState.dlhCodOutlet == "0.0") {
                                    ""
                                } else {
                                    settingsUIState.dlhCodOutlet
                                },
                                textValueChange = {
                                    settingsViewModel.onInputFieldEvent(
                                        InputSettingsEvent.CodOutletFieldChange(it)
                                    )
                                },
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                            Text(text = "ppm", fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "NH3 OUTLET",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            TextFieldTypeUniversal(
                                modifier = Modifier.fillMaxWidth(fraction = 0.85F),
                                placeholderText = "0",
                                textValue = if (settingsUIState.dlhNhThreeOutlet == "0.0") {
                                    ""
                                } else {
                                    settingsUIState.dlhNhThreeOutlet
                                },
                                textValueChange = {
                                    settingsViewModel.onInputFieldEvent(
                                        InputSettingsEvent.NhThreeOutletFieldChange(it)
                                    )
                                },
                                keyboardType = KeyboardType.Number,
                                keyboardActions = KeyboardActions(onDone = { settingsViewModel.saveSettings() }),
                                imeAction = ImeAction.Done
                            )
                            Text(text = "ppm", fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.weight(1F))
                        ElevatedButton(
                            onClick = {
                                settingsViewModel.saveSettings()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AdaptiveThemeColor(
                                    isDarkTheme = isSystemInDarkTheme(),
                                    onDark = Color.White,
                                    onLight = Color.Black,
                                ),
                                contentColor = AdaptiveThemeColor(
                                    isDarkTheme = isSystemInDarkTheme(),
                                    onDark = Color.Black,
                                    onLight = Color.White,
                                ),
                            )
                        ) {
                            Text(text = "SAVE", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true, name = "enable text field",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun PreviewSettingsScreen() {
    PWDO2Theme {
        Surface {
            SettingsScreen(navigator = EmptyDestinationsNavigator)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true, name = "enable text field",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun PreviewSettingsScreenDark() {
    PWDO2Theme {
        Surface {
            SettingsScreen(navigator = EmptyDestinationsNavigator)
        }
    }
}