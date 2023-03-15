package c.m.pwdo2.settings.domain.use_case

import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.settings.domain.model.DlhStandardValue
import c.m.pwdo2.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDlhStandardValueUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(): Flow<Resource<DlhStandardValue>> {
        return settingsRepository.readDlhStandardValue()
    }
}