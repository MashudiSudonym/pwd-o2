package c.m.pwdo2.oxygen_requirement_and_area.domain.use_case

import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculateAreaVolumeRequirementUseCase @Inject constructor(private val oxygenRequirementAndAreaRepository: OxygenRequirementAndAreaRepository) {
    suspend operator fun invoke(
        diffuserTwelveUnitRoundUp: Double,
        minimumMixingAreaStandard: Double,
        effectiveSurfaceAreaDiffTwelveInchStandard: Double,
        inputWaterDepthAboveDiffuser: Double,
    ): Flow<Resource<Unit>> {
        val totalAerationChamberAreaWithDiffuserTwelveInch =
            diffuserTwelveUnitRoundUp * effectiveSurfaceAreaDiffTwelveInchStandard / minimumMixingAreaStandard
        val totalAerationChamberVolumeWithDiffuserTwelveInch =
            totalAerationChamberAreaWithDiffuserTwelveInch * (inputWaterDepthAboveDiffuser + 0.3)



        return if (!totalAerationChamberVolumeWithDiffuserTwelveInch.isNaN() && !totalAerationChamberAreaWithDiffuserTwelveInch.isNaN()) {
            if (!totalAerationChamberVolumeWithDiffuserTwelveInch.isInfinite() && !totalAerationChamberAreaWithDiffuserTwelveInch.isInfinite()) {
                oxygenRequirementAndAreaRepository.saveAreaVolumeRequirement(
                    aerationChamberVolumeWithDiffuserTwelveInch = totalAerationChamberVolumeWithDiffuserTwelveInch,
                    aerationChamberAreaWithDiffuserTwelveInch = totalAerationChamberAreaWithDiffuserTwelveInch,
                )
            } else {
                oxygenRequirementAndAreaRepository.saveAreaVolumeRequirement(
                    aerationChamberVolumeWithDiffuserTwelveInch = 0.0,
                    aerationChamberAreaWithDiffuserTwelveInch = 0.0,
                )
            }
        } else {
            oxygenRequirementAndAreaRepository.saveAreaVolumeRequirement(
                aerationChamberVolumeWithDiffuserTwelveInch = 0.0,
                aerationChamberAreaWithDiffuserTwelveInch = 0.0,
            )
        }
    }
}