package c.m.pwdo2.login.presentation.state

data class LoginUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isLogin: Boolean = false,
    val usernameText: String = "",
    val passwordText: String = "",
)
