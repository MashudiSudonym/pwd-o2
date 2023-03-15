package c.m.pwdo2.login.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import c.m.pwdo2.R
import c.m.pwdo2.common.presentation.component.custom.TextFieldTypePassword
import c.m.pwdo2.common.presentation.component.custom.TextFieldTypeUniversal
import c.m.pwdo2.common.presentation.component.util.AdaptiveThemeColor
import c.m.pwdo2.destinations.LoginScreenDestination
import c.m.pwdo2.destinations.OxygenRequirementAndAreaScreenDestination
import c.m.pwdo2.destinations.WrapperScreenDestination
import c.m.pwdo2.login.presentation.event.InputLoginDataEvent
import c.m.pwdo2.login.presentation.view_model.LoginViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@ExperimentalMaterial3Api
@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val loginUIState by loginViewModel.loginUIState.collectAsState()

    Scaffold {
        when {
            loginUIState.isLoading -> {
                CircularProgressIndicator()
            }
            loginUIState.isError -> {
                Text(
                    text = "error!!! restart application",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            loginUIState.isLogin -> {
                navigator.navigate(OxygenRequirementAndAreaScreenDestination) {
                    popUpTo(LoginScreenDestination) {
                        inclusive = true
                    }
                }
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .imePadding()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pwdo2),
                        contentDescription = null,
                        modifier = Modifier
                            .size(128.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(64.dp))

                    Text(text = "username", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldTypeUniversal(
                        modifier = Modifier.fillMaxWidth(),
                        placeholderText = "input username",
                        imeAction = ImeAction.Next,
                        textValue = if (loginUIState.usernameText == "") {
                            ""
                        } else {
                            loginUIState.usernameText
                        },
                        textValueChange = {
                            loginViewModel.onInputLoginDataEvent(
                                InputLoginDataEvent.UsernameFieldChange(it)
                            )
                        },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "password", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldTypePassword(
                        modifier = Modifier.fillMaxWidth(),
                        placeholderText = "input password",
                        imeAction = ImeAction.Done,
                        keyboardActions = KeyboardActions(onDone = { loginViewModel.doLogin() }),
                        textValue = if (loginUIState.passwordText == "") {
                            ""
                        } else {
                            loginUIState.passwordText
                        },
                        textValueChange = {
                            loginViewModel.onInputLoginDataEvent(
                                InputLoginDataEvent.PasswordFieldChange(it)
                            )
                        },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ElevatedButton(
                        onClick = { loginViewModel.doLogin() },
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
                        Text(text = "LOGIN", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}