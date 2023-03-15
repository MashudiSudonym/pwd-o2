package c.m.pwdo2.settings.data.respository

import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.data_store.domain.repository.DataStoreRepository
import c.m.pwdo2.settings.domain.model.DlhStandardValue
import c.m.pwdo2.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    SettingsRepository {
    override suspend fun saveDlhStandardValue(
        dlhBodOutlet: Double,
        dlhCodOutlet: Double,
        dlhNhThreeOutlet: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            emit(Resource.Success(dataStoreRepository.setDlhBodOutlet(dlhBodOutlet)))
            emit(Resource.Success(dataStoreRepository.setDlhCodOutlet(dlhCodOutlet)))
            emit(Resource.Success(dataStoreRepository.setDlhNhThreeOutlet(dlhNhThreeOutlet)))
        }
    }

    override suspend fun readDlhStandardValue(): Flow<Resource<DlhStandardValue>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val getDlhBodOutlet = dataStoreRepository.getDlhBodOutlet().getOrDefault(0.0)
            val getDlhCodOutlet = dataStoreRepository.getDlhCodOutlet().getOrDefault(0.0)
            val getDlhNhThreeOutlet = dataStoreRepository.getDlhNhThreeOutlet().getOrDefault(0.0)

            emit(
                Resource.Success(
                    DlhStandardValue(
                        dlhBodOutlet = getDlhBodOutlet,
                        dlhCodOutlet = getDlhCodOutlet,
                        dlhNhThreeOutlet = getDlhNhThreeOutlet,
                    )
                )
            )
        }
    }
}