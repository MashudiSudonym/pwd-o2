package c.m.pwdo2.login.domain.use_case

import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(
        username: String,
        password: String,
    ): Flow<SimpleResource> {
        return loginRepository.setAuthenticationStatus(username, password)
    }
}