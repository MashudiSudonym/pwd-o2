package c.m.pwdo2.login.data.repository

import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.data_store.domain.repository.DataStoreRepository
import c.m.pwdo2.login.data.remote.LoginAPI
import c.m.pwdo2.login.domain.model.AuthenticationStatus
import c.m.pwdo2.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginAPI: LoginAPI,
    private val dataStoreRepository: DataStoreRepository,
) : LoginRepository {
    override suspend fun setAuthenticationStatus(
        username: String,
        password: String,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val findUserData = loginAPI.getUsers().user.find { u ->
                u.username == username
            }

            if ((findUserData?.username == username) && (findUserData.password == password)) {
                emit(Resource.Loading(isLoading = false))

                val saveLoginStatus = dataStoreRepository.setAuthenticationStatus(true)

                emit(Resource.Success(saveLoginStatus))
            } else {
                emit(Resource.Loading(isLoading = false))

                val saveLoginStatus = dataStoreRepository.setAuthenticationStatus(false)

                emit(Resource.Success(saveLoginStatus))
            }
        }
    }

    override suspend fun getAuthenticationStatus(): Flow<Resource<AuthenticationStatus>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))

            val readLoginStatus = dataStoreRepository.getAuthenticationStatus().getOrDefault(false)

            emit(Resource.Success(AuthenticationStatus(isLogin = readLoginStatus)))
        }
    }

    override suspend fun setLogoutStatus(isLogin: Boolean): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))

            val saveLoginStatus = dataStoreRepository.setAuthenticationStatus(false)

            emit(Resource.Success(saveLoginStatus))
        }
    }
}