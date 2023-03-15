package c.m.pwdo2.oxygen_requirement_and_area.domain.use_case

import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculateAirRequirementUseCase @Inject constructor(private val oxygenRequirementAndAreaRepository: OxygenRequirementAndAreaRepository) {
    suspend operator fun invoke(
        oneKgBodStandard: Double,
        densityOfOxygenStandard: Double,
        percentOxygenInAirStandard: Double,
        totalBodInlet: Double,
        oneKgCodStandard: Double,
        totalCodInlet: Double,
        oneKgNhThreeStandard: Double,
        totalNhThreeInlet: Double,
    ): Flow<SimpleResource> {
        val totalForBodHour =
            oneKgBodStandard / densityOfOxygenStandard / percentOxygenInAirStandard * totalBodInlet
        val totalForBodMinute = totalForBodHour / 60
        val totalForCodHour =
            oneKgCodStandard / densityOfOxygenStandard / percentOxygenInAirStandard * totalCodInlet
        val totalForCodMinute = totalForCodHour / 60
        val totalForNhThreeHour =
            oneKgNhThreeStandard / densityOfOxygenStandard / percentOxygenInAirStandard * totalNhThreeInlet
        val totalForNhThreeMinute = totalForNhThreeHour / 60
        val totalAirRequirementMinute =
            totalForBodMinute + totalForCodMinute + totalForNhThreeMinute
        val totalAirRequirementHour = totalForBodHour + totalForCodHour + totalForNhThreeHour

        

        return if (!totalForBodHour.isNaN() && !totalForCodHour.isNaN() && !totalForNhThreeHour.isNaN() && !totalForBodMinute.isNaN() && !totalForCodMinute.isNaN() && !totalForNhThreeMinute.isNaN() && !totalAirRequirementMinute.isNaN() && !totalAirRequirementHour.isNaN()) {
            if (!totalForBodHour.isInfinite() && !totalForCodHour.isInfinite() && !totalForNhThreeHour.isInfinite() && !totalForBodMinute.isInfinite() && !totalForCodMinute.isInfinite() && !totalForNhThreeMinute.isInfinite() && !totalAirRequirementMinute.isInfinite() && !totalAirRequirementHour.isInfinite()) {
                oxygenRequirementAndAreaRepository.saveAirRequirement(
                    forBodPerHour = totalForBodHour,
                    forBodPerMinute = totalForBodMinute,
                    forCodPerHour = totalForCodHour,
                    forCodPerMinute = totalForCodMinute,
                    forNhThreePerHour = totalForNhThreeHour,
                    forNhThreePerMinute = totalForNhThreeMinute,
                    totalAirRequirementPerMinute = totalAirRequirementMinute,
                    totalAirRequirementPerHour = totalAirRequirementHour,
                )
            } else {
                oxygenRequirementAndAreaRepository.saveAirRequirement(
                    forBodPerHour = 0.0,
                    forBodPerMinute = 0.0,
                    forCodPerHour = 0.0,
                    forCodPerMinute = 0.0,
                    forNhThreePerHour = 0.0,
                    forNhThreePerMinute = 0.0,
                    totalAirRequirementPerMinute = 0.0,
                    totalAirRequirementPerHour = 0.0,
                )
            }
        } else {
            oxygenRequirementAndAreaRepository.saveAirRequirement(
                forBodPerHour = 0.0,
                forBodPerMinute = 0.0,
                forCodPerHour = 0.0,
                forCodPerMinute = 0.0,
                forNhThreePerHour = 0.0,
                forNhThreePerMinute = 0.0,
                totalAirRequirementPerMinute = 0.0,
                totalAirRequirementPerHour = 0.0,
            )
        }
    }
}