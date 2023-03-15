package c.m.pwdo2.oxygen_requirement_and_area.domain.use_case

import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetInputParametersUseCase @Inject constructor(private val oxygenRequirementAndAreaRepository: OxygenRequirementAndAreaRepository) {
    suspend operator fun invoke(
        inputBodInlet: Double,
        inputBodOutlet: Double,
        inputCodInlet: Double,
        inputCodOutlet: Double,
        inputNhThreeInlet: Double,
        inputNhThreeOutlet: Double,
        inputFlowRate: Double,
        inputPeakHourAssumption: Double,
        inputWaterDepthAboveDiffuser: Double,
    ): Flow<SimpleResource> {
        return oxygenRequirementAndAreaRepository.saveInputParameters(
            inputBodInlet = inputBodInlet,
            inputBodOutlet = inputBodOutlet,
            inputCodInlet = inputCodInlet,
            inputCodOutlet = inputCodOutlet,
            inputNhThreeInlet = inputNhThreeInlet,
            inputNhThreeOutlet = inputNhThreeOutlet,
            inputFlowRate = inputFlowRate,
            inputPeakHourAssumption = inputPeakHourAssumption,
            inputWaterDepthAboveDiffuser = inputWaterDepthAboveDiffuser,
        )
    }
}