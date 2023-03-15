package c.m.pwdo2.oxygen_requirement_and_area.domain.use_case

import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculateBlowerCapacityRequirementUseCase @Inject constructor(private val oxygenRequirementAndAreaRepository: OxygenRequirementAndAreaRepository) {
    suspend operator fun invoke(
        diffuserTwelveUnitRoundUp: Double,
        diffuserCapacityTwelveInchStandard: Double,
    ): Flow<SimpleResource> {
        val totalBlowerCapacityRequirementPerHour =
            diffuserTwelveUnitRoundUp * diffuserCapacityTwelveInchStandard
        val totalBlowerCapacityRequirementPerMinute = totalBlowerCapacityRequirementPerHour / 60



        return if (!totalBlowerCapacityRequirementPerHour.isNaN() && !totalBlowerCapacityRequirementPerMinute.isNaN()) {
            if (!totalBlowerCapacityRequirementPerMinute.isInfinite() && !totalBlowerCapacityRequirementPerHour.isInfinite()) {
                oxygenRequirementAndAreaRepository.saveBlowerCapacityRequirement(
                    blowerCapacityRequirementPerHour = totalBlowerCapacityRequirementPerHour,
                    blowerCapacityRequirementPerMinute = totalBlowerCapacityRequirementPerMinute,
                )
            } else {
                oxygenRequirementAndAreaRepository.saveBlowerCapacityRequirement(
                    blowerCapacityRequirementPerHour = 0.0,
                    blowerCapacityRequirementPerMinute = 0.0,
                )
            }
        } else {
            oxygenRequirementAndAreaRepository.saveBlowerCapacityRequirement(
                blowerCapacityRequirementPerHour = 0.0,
                blowerCapacityRequirementPerMinute = 0.0,
            )
        }
    }
}