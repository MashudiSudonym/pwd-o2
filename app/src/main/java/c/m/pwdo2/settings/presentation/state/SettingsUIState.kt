package c.m.pwdo2.settings.presentation.state

data class SettingsUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val isRefresh: Boolean = false,
    val dlhBodOutlet: String = "",
    val dlhCodOutlet: String = "",
    val dlhNhThreeOutlet: String = "",
)
