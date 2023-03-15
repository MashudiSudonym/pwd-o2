package c.m.pwdo2.oxygen_requirement_and_area.domain.use_case

import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculateDiffuserDepthEfficiencyUseCase @Inject constructor(private val oxygenRequirementAndAreaRepository: OxygenRequirementAndAreaRepository) {
    suspend operator fun invoke(
        waterDepthAboveDiffuser: Double,
        oxygenTransferEfficiency: Double,
    ): Flow<SimpleResource> {
        val calculatedDiffuserDepthEfficiency =
            waterDepthAboveDiffuser.times(oxygenTransferEfficiency)

        return oxygenRequirementAndAreaRepository.saveDiffuserDepthEfficiency(
            calculatedDiffuserDepthEfficiency
        )
    }
}