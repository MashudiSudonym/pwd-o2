package c.m.pwdo2.login.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
)
