package c.m.pwdo2.settings.presentation.event

sealed class InputSettingsEvent {
    data class BodOutletFieldChange(val bodOutlet: String) : InputSettingsEvent()
    data class CodOutletFieldChange(val codOutlet: String) : InputSettingsEvent()
    data class NhThreeOutletFieldChange(val nhThreeOutlet: String) : InputSettingsEvent()
}
