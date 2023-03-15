package c.m.pwdo2.login.domain.use_case

import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.login.domain.model.AuthenticationStatus
import c.m.pwdo2.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckLoginStatusUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(): Flow<Resource<AuthenticationStatus>> {
        return loginRepository.getAuthenticationStatus()
    }
}