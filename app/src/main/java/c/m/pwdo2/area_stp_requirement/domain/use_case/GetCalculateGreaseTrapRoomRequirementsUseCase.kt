package c.m.pwdo2.area_stp_requirement.domain.use_case

import c.m.pwdo2.area_stp_requirement.domain.model.GreaseTrapRoomRequirements
import c.m.pwdo2.area_stp_requirement.domain.repository.AreaStpRequirementRepository
import c.m.pwdo2.common.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCalculateGreaseTrapRoomRequirementsUseCase @Inject constructor(private val areaStpRequirementRepository: AreaStpRequirementRepository) {
    suspend operator fun invoke(): Flow<Resource<GreaseTrapRoomRequirements>> {
        return areaStpRequirementRepository.readGreaseTrapRoomRequirement()
    }
}