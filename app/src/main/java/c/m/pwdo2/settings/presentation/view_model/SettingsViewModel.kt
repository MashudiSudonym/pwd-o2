package c.m.pwdo2.settings.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.settings.domain.use_case.GetDlhStandardValueUseCase
import c.m.pwdo2.settings.domain.use_case.SetDlhStandardValueUseCase
import c.m.pwdo2.settings.presentation.event.InputSettingsEvent
import c.m.pwdo2.settings.presentation.event.SettingsUIEvent
import c.m.pwdo2.settings.presentation.state.SettingsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val setDlhStandardValueUseCase: SetDlhStandardValueUseCase,
    private val getDlhStandardValueUseCase: GetDlhStandardValueUseCase,
) : ViewModel() {
    private val _settingsUIState = MutableStateFlow(SettingsUIState())
    val settingsUIState: StateFlow<SettingsUIState> = _settingsUIState.asStateFlow()

    private val settingsUIEventChannel = Channel<SettingsUIEvent>()
    val settingsUIEvent = settingsUIEventChannel.receiveAsFlow()

    init {
        readSavedSettings()
    }

    fun onInputFieldEvent(event: InputSettingsEvent) {
        when (event) {
            is InputSettingsEvent.BodOutletFieldChange -> {
                _settingsUIState.update {
                    it.copy(dlhBodOutlet = event.bodOutlet.replace(",", "."))
                }
            }
            is InputSettingsEvent.CodOutletFieldChange -> {
                _settingsUIState.update {
                    it.copy(dlhCodOutlet = event.codOutlet.replace(",", "."))
                }
            }
            is InputSettingsEvent.NhThreeOutletFieldChange -> {
                _settingsUIState.update {
                    it.copy(dlhNhThreeOutlet = event.nhThreeOutlet.replace(",", "."))
                }
            }
        }
    }

    fun saveSettings() {
        viewModelScope.launch {
            val dlhBodOutlet = _settingsUIState.value.dlhBodOutlet.ifEmpty { "0" }
            val dlhCodOutlet = _settingsUIState.value.dlhCodOutlet.ifEmpty { "0" }
            val dlhNhThreeOutlet = _settingsUIState.value.dlhNhThreeOutlet.ifEmpty { "0" }

            setDlhStandardValueUseCase(
                dlhBodOutlet.replace(Regex("\\.+\\z"), "").toDouble(),
                dlhCodOutlet.replace(Regex("\\.+\\z"), "").toDouble(),
                dlhNhThreeOutlet.replace(Regex("\\.+\\z"), "").toDouble(),
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _settingsUIState.update {
                            it.copy(isLoading = false, isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _settingsUIState.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Resource.Success -> {
                        readSavedSettings()
                    }
                }
            }

            if (!_settingsUIState.value.isLoading && !_settingsUIState.value.isError) {
                settingsUIEventChannel.send(SettingsUIEvent.SuccessSaved)
            }
        }
    }

    fun readSavedSettings() {
        viewModelScope.launch {
            getDlhStandardValueUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _settingsUIState.update {
                            it.copy(isLoading = false, isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _settingsUIState.update {
                            it.copy(isLoading = true, isRefresh = true)
                        }
                    }
                    is Resource.Success -> {
                        _settingsUIState.update {
                            it.copy(
                                isLoading = false,
                                isError = false,
                                isSuccess = true,
                                isRefresh = false,
                                dlhBodOutlet = result.data?.dlhBodOutlet?.toString() ?: "",
                                dlhCodOutlet = result.data?.dlhCodOutlet?.toString() ?: "",
                                dlhNhThreeOutlet = result.data?.dlhNhThreeOutlet?.toString() ?: "",
                            )
                        }
                    }
                }
            }
        }
    }
}