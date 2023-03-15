package c.m.pwdo2.oxygen_requirement_and_area.domain.use_case

import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculateTotalInfluentLoadingPerHourUseCase @Inject constructor(private val oxygenRequirementAndAreaRepository: OxygenRequirementAndAreaRepository) {
    suspend operator fun invoke(
        inputBodInlet: Double,
        inputBodOutlet: Double,
        inputCodInlet: Double,
        inputCodOutlet: Double,
        inputNhThreeInlet: Double,
        inputNhThreeOutlet: Double,
        inputFlowRate: Double,
        inputPeakHourAssumption: Double,
    ): Flow<SimpleResource> {
        val bodInletKgM3 = inputBodInlet.div(1000.0)
        val bodOutletKgM3 = inputBodOutlet.div(1000.0)
        val totalBodInlet =
            (bodInletKgM3.minus(bodOutletKgM3)).times(
                (inputFlowRate.div(
                    inputPeakHourAssumption
                ))
            )
        val codInletKgM3 = inputCodInlet.div(1000.0)
        val codOutletKgM3 = inputCodOutlet.div(1000.0)
        val totalCodInlet =
            (codInletKgM3.minus(codOutletKgM3)).times(
                (inputFlowRate.div(
                    inputPeakHourAssumption
                ))
            )
        val nhThreeInletKgM3 = inputNhThreeInlet.div(1000.0)
        val nhThreeOutletKgM3 = inputNhThreeOutlet.div(1000.0)
        val totalNhThreeInlet = (nhThreeInletKgM3.minus(nhThreeOutletKgM3)).times(
            (inputFlowRate.div(inputPeakHourAssumption))
        )



        return if (!totalBodInlet.isNaN() && !totalCodInlet.isNaN() && !totalNhThreeInlet.isNaN()) {
            if (!totalBodInlet.isInfinite() && !totalCodInlet.isInfinite() && !totalNhThreeInlet.isInfinite()) {
                oxygenRequirementAndAreaRepository.saveTotalInfluentLoadingPerHour(
                    totalBodInlet = totalBodInlet,
                    totalCodInlet = totalCodInlet,
                    totalNhThreeInlet = totalNhThreeInlet,
                )
            } else {
                oxygenRequirementAndAreaRepository.saveTotalInfluentLoadingPerHour(
                    0.0,
                    0.0,
                    0.0,
                )
            }
        } else {
            oxygenRequirementAndAreaRepository.saveTotalInfluentLoadingPerHour(
                0.0,
                0.0,
                0.0,
            )
        }
    }
}