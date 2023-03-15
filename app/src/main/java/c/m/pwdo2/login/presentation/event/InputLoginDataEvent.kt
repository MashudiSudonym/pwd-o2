package c.m.pwdo2.login.presentation.event

sealed class InputLoginDataEvent {
    data class UsernameFieldChange(val username: String) : InputLoginDataEvent()
    data class PasswordFieldChange(val password: String) : InputLoginDataEvent()
}