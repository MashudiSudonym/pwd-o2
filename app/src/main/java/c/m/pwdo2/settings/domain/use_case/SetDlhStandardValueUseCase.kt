package c.m.pwdo2.settings.domain.use_case

import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetDlhStandardValueUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(
        dlhBodOutlet: Double,
        dlhCodOutlet: Double,
        nhThreeOutlet: Double,
    ): Flow<SimpleResource> {
        return settingsRepository.saveDlhStandardValue(dlhBodOutlet, dlhCodOutlet, nhThreeOutlet)
    }
}