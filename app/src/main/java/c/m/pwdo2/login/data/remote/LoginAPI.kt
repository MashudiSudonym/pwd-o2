package c.m.pwdo2.login.data.remote

import c.m.pwdo2.login.data.remote.dto.ListUserDTO
import retrofit2.http.GET

interface LoginAPI {
    @GET("user.json")
    suspend fun getUsers(): ListUserDTO
}