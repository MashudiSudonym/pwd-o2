package c.m.pwdo2.login.domain.repository

import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.login.domain.model.AuthenticationStatus
import c.m.pwdo2.login.domain.model.ListUser
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun setAuthenticationStatus(username: String, password: String): Flow<SimpleResource>
    suspend fun getAuthenticationStatus(): Flow<Resource<AuthenticationStatus>>
    suspend fun setLogoutStatus(isLogin: Boolean): Flow<SimpleResource>
}