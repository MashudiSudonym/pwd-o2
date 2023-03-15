package c.m.pwdo2.oxygen_requirement_and_area.domain.use_case

import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.math.ceil

class CalculateDiffuserRequirementUseCase @Inject constructor(private val oxygenRequirementAndAreaRepository: OxygenRequirementAndAreaRepository) {
    suspend operator fun invoke(
        totalAirRequirementPerHour: Double,
        diffuserCapacityTwelveInchStandard: Double,
        diffuserCapacityNineInchStandard: Double,
        diffuserDepthEfficiency: Double,
    ): Flow<SimpleResource> {
        val totalDiffuserTwelveInchUnit =
            totalAirRequirementPerHour / diffuserCapacityTwelveInchStandard / (diffuserDepthEfficiency / 100)
        val totalDiffuserTwelveInchRoundUp =
            if (!totalDiffuserTwelveInchUnit.isNaN() && !totalDiffuserTwelveInchUnit.isInfinite() && totalDiffuserTwelveInchUnit != 0.0) {
                ceil(totalDiffuserTwelveInchUnit)
            } else {
                0.0
            }
        val totalDiffuserNineInchUnit =
            totalAirRequirementPerHour / diffuserCapacityNineInchStandard / (diffuserDepthEfficiency / 100)
        val totalDiffuserNineInchRoundUp =
            if (!totalDiffuserNineInchUnit.isNaN() && !totalDiffuserNineInchUnit.isInfinite() && totalDiffuserNineInchUnit != 0.0) {
                ceil(totalDiffuserNineInchUnit)
            } else {
                0.0
            }



        return if (!totalDiffuserTwelveInchUnit.isNaN() && !totalDiffuserTwelveInchRoundUp.isNaN() && !totalDiffuserNineInchUnit.isNaN() && !totalDiffuserNineInchRoundUp.isNaN()) {
            if (!totalDiffuserTwelveInchUnit.isInfinite() && !totalDiffuserTwelveInchRoundUp.isInfinite() && !totalDiffuserNineInchUnit.isInfinite() && !totalDiffuserNineInchRoundUp.isInfinite()) {
                oxygenRequirementAndAreaRepository.saveDiffuserRequirement(
                    diffuserTwelveUnit = totalDiffuserTwelveInchUnit,
                    diffuserTwelveUnitRoundUp = totalDiffuserTwelveInchRoundUp,
                    diffuserNineUnit = totalDiffuserNineInchUnit,
                    diffuserNineUnitRoundUp = totalDiffuserNineInchRoundUp,
                )
            } else {
                oxygenRequirementAndAreaRepository.saveDiffuserRequirement(
                    diffuserTwelveUnit = 0.0,
                    diffuserTwelveUnitRoundUp = 0.0,
                    diffuserNineUnit = 0.0,
                    diffuserNineUnitRoundUp = 0.0,
                )
            }
        } else {
            oxygenRequirementAndAreaRepository.saveDiffuserRequirement(
                diffuserTwelveUnit = 0.0,
                diffuserTwelveUnitRoundUp = 0.0,
                diffuserNineUnit = 0.0,
                diffuserNineUnitRoundUp = 0.0,
            )
        }
    }
}