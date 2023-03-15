package c.m.pwdo2.login.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.login.domain.use_case.CheckLoginStatusUseCase
import c.m.pwdo2.login.domain.use_case.UserLoginUseCase
import c.m.pwdo2.login.presentation.event.InputLoginDataEvent
import c.m.pwdo2.login.presentation.state.LoginUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase,
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
) : ViewModel() {
    private val _loginUIState = MutableStateFlow(LoginUIState())
    val loginUIState: StateFlow<LoginUIState> = _loginUIState.asStateFlow()

    init {
        checkAuthenticationStatus()
    }

    fun onInputLoginDataEvent(event: InputLoginDataEvent) {
        when (event) {
            is InputLoginDataEvent.PasswordFieldChange -> {
                _loginUIState.update {
                    it.copy(passwordText = event.password)
                }
            }
            is InputLoginDataEvent.UsernameFieldChange -> {
                _loginUIState.update {
                    it.copy(usernameText = event.username)
                }
            }
        }
    }

    fun doLogin() {
        viewModelScope.launch {
            userLoginUseCase(
                username = _loginUIState.value.usernameText,
                password = _loginUIState.value.passwordText,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _loginUIState.update { it.copy(isLoading = false, isError = true) }
                    }
                    is Resource.Loading -> {
                        _loginUIState.update { it.copy(isLoading = true, isError = false) }
                    }
                    is Resource.Success -> {
                        checkAuthenticationStatus()
                    }
                }
            }
        }
    }

    private fun checkAuthenticationStatus() {
        viewModelScope.launch {
            checkLoginStatusUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _loginUIState.update { it.copy(isLoading = false, isError = true) }
                    }
                    is Resource.Loading -> {
                        _loginUIState.update { it.copy(isLoading = true, isError = false) }
                    }
                    is Resource.Success -> {
                        _loginUIState.update {
                            it.copy(
                                isLoading = false,
                                isError = false,
                                isLogin = result.data?.isLogin ?: false,
                            )
                        }
                    }
                }
            }
        }
    }
}