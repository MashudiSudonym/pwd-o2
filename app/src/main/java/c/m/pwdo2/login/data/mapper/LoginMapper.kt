package c.m.pwdo2.login.data.mapper

import c.m.pwdo2.login.data.remote.dto.ListUserDTO
import c.m.pwdo2.login.data.remote.dto.UserDTO
import c.m.pwdo2.login.domain.model.ListUser
import c.m.pwdo2.login.domain.model.User

fun UserDTO.toUser(): User {
    return User(
        name, username, password
    )
}

fun ListUserDTO.toListUser(): ListUser {
    return ListUser(user.map {
        it.toUser()
    })
}