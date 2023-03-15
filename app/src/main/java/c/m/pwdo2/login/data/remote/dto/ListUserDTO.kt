package c.m.pwdo2.login.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ListUserDTO(
    @SerializedName("user")
    val user: List<UserDTO>,
)
