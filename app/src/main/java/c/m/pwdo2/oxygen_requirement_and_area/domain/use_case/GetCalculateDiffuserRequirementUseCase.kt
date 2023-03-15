package c.m.pwdo2.oxygen_requirement_and_area.domain.use_case

import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.oxygen_requirement_and_area.domain.model.DiffuserRequirement
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCalculateDiffuserRequirementUseCase @Inject constructor(private val oxygenRequirementAndAreaRepository: OxygenRequirementAndAreaRepository) {
    suspend operator fun invoke(): Flow<Resource<DiffuserRequirement>> {
        return oxygenRequirementAndAreaRepository.readDiffuserRequirement()
    }
}