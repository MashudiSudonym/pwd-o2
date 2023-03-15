package c.m.pwdo2.oxygen_requirement_and_area.domain.use_case

import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.oxygen_requirement_and_area.domain.model.TotalInfluentLoadingPerHour
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCalculateTotalInfluentLoadingPerHourUseCase @Inject constructor(private val oxygenRequirementAndAreaRepository: OxygenRequirementAndAreaRepository) {
    suspend operator fun invoke(): Flow<Resource<TotalInfluentLoadingPerHour>> {
        return oxygenRequirementAndAreaRepository.readTotalInfluentLoadingPerHour()
    }
}