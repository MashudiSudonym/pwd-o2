package c.m.pwdo2.oxygen_requirement_and_area.domain.use_case

import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetStandardValueUseCase @Inject constructor(private val oxygenRequirementAndAreaRepository: OxygenRequirementAndAreaRepository) {
    suspend operator fun invoke(): Flow<SimpleResource> {
        return oxygenRequirementAndAreaRepository.saveStandardValue(
            oneKgBodStandard = 2.0,
            oneKgCodStandard = 1.0,
            oneKgNhThreeStandard = 5.0,
            percentOxygenInAirStandard = 0.21,
            densityOfOxygenStandard = 1.429,
            diffuserCapacityTwelveInchStandard = 7.0,
            diffuserCapacityNineInchStandard = 4.0,
            percentOxygenTransferEfficiencyStandard = 0.065,
            effectiveSurfaceAreaDiffTwelveInchStandard = 0.065,
            effectiveSurfaceAreaDiffNineInchStandard = 0.0375,
            minimumMixingAreaStandard = 0.065,
            retentionTimeAnaerob = 3.0,
            percentBwTowardsTotalFlowForHotel = 0.2,
            percentBwTowardsTotalFlowForIndustry = 1.0,
            targetBodReductionFromAnaerob = 0.3,
            retentionTimeAeration = 24.0,
            targetBodReductionFromAeration = 0.95,
            targetCorReductionFromAeration = 0.95,
            targetNhThreeReductionFromAeration = 0.95,
            retentionTimeGreaseTrap = 30.0,
            totalChamberGreaseTrap = 4.0,
            targetFogRemovalFromGreaseTrap = 0.95,
            retentionTimeSedimentation = 4.0,
            targetTssRemovalFromGreaseTrap = 0.95,
            retentionTimeChlorination = 10.0,
            targetEcoliRemovalFromChlorination = 0.95,
        )
    }
}