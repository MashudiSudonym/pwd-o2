package c.m.pwdo2.common.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import c.m.pwdo2.common.presentation.state.WrapperUIState
import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.login.domain.use_case.CheckLoginStatusUseCase
import c.m.pwdo2.oxygen_requirement_and_area.domain.use_case.SetStandardValueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WrapperViewModel @Inject constructor(
    private val setStandardValueUseCase: SetStandardValueUseCase,
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
) :
    ViewModel() {
    private val _wrapperUIState = MutableStateFlow(WrapperUIState())
    val wrapperUIState: StateFlow<WrapperUIState> = _wrapperUIState.asStateFlow()

    init {
        saveStandardValue()
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            checkLoginStatusUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _wrapperUIState.update {
                            it.copy(isCheckLoginError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _wrapperUIState.update {
                            it.copy(isCheckLoginError = false, isLoading = true)
                        }
                    }
                    is Resource.Success -> {
                        _wrapperUIState.update {
                            it.copy(isCheckLoginError = false, isLoading = false, isLogin = result.data?.isLogin ?: false)
                        }
                    }
                }
            }
        }
    }

    private fun saveStandardValue() {
        viewModelScope.launch {
            setStandardValueUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _wrapperUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        delay(1500)
                        _wrapperUIState.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Resource.Success -> {
                        _wrapperUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                            )
                        }
                    }
                }
            }
        }
    }
}