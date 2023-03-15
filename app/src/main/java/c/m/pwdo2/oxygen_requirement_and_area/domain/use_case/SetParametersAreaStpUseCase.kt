package c.m.pwdo2.oxygen_requirement_and_area.domain.use_case

import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetParametersAreaStpUseCase @Inject constructor(private val oxygenRequirementAndAreaRepository: OxygenRequirementAndAreaRepository) {
    suspend operator fun invoke(
        inputBodInlet: Double,
        inputCodInlet: Double,
        inputNhThreeInlet: Double,
        targetBodReductionFromAnaerob: Double,
        targetBodReductionFromAeration: Double,
        targetCodReductionFromAeration: Double,
        targetNhThreeReductionFromAeration: Double,
    ): Flow<SimpleResource> {
        val totalBodOutletAnaerob = inputBodInlet - (inputBodInlet * targetBodReductionFromAnaerob)
        val totalBodOutletAerob =
            totalBodOutletAnaerob - (totalBodOutletAnaerob * targetBodReductionFromAeration)
        val totalCodOutletAerob = inputCodInlet - (inputCodInlet * targetCodReductionFromAeration)
        val totalNhThreeOutletAerob =
            inputNhThreeInlet - (inputNhThreeInlet * targetNhThreeReductionFromAeration)

        delay(500)

        return if (!totalBodOutletAnaerob.isNaN() && !totalBodOutletAerob.isNaN() && !totalCodOutletAerob.isNaN() && !totalNhThreeOutletAerob.isNaN()) {
            if (!totalBodOutletAnaerob.isInfinite() && !totalBodOutletAerob.isInfinite() && !totalCodOutletAerob.isInfinite() && !totalNhThreeOutletAerob.isInfinite()) {
                oxygenRequirementAndAreaRepository.saveParametersForAreaStp(
                    bodOutletAnaerob = totalBodOutletAnaerob,
                    bodOutletAerob = totalBodOutletAerob,
                    codOutletAerob = totalCodOutletAerob,
                    nhThreeAerob = totalNhThreeOutletAerob,
                )
            } else {
                oxygenRequirementAndAreaRepository.saveParametersForAreaStp(
                    bodOutletAnaerob = 0.0,
                    bodOutletAerob = 0.0,
                    codOutletAerob = 0.0,
                    nhThreeAerob = 0.0,
                )
            }
        } else {
            oxygenRequirementAndAreaRepository.saveParametersForAreaStp(
                bodOutletAnaerob = 0.0,
                bodOutletAerob = 0.0,
                codOutletAerob = 0.0,
                nhThreeAerob = 0.0,
            )
        }
    }
}