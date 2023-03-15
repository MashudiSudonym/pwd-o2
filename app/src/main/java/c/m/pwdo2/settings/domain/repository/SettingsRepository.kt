package c.m.pwdo2.settings.domain.repository

import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.settings.domain.model.DlhStandardValue
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SettingsRepository {
    suspend fun saveDlhStandardValue(
        dlhBodOutlet: Double,
        dlhCodOutlet: Double,
        dlhNhThreeOutlet: Double,
    ): Flow<SimpleResource>

    suspend fun readDlhStandardValue(): Flow<Resource<DlhStandardValue>>
}