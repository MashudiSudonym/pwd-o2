package c.m.pwdo2.area_stp_requirement.domain.use_case

import c.m.pwdo2.area_stp_requirement.domain.repository.AreaStpRequirementRepository
import c.m.pwdo2.common.util.SimpleResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculateFiltrationTankRoomRequirementsUseCase @Inject constructor(private val areaStpRequirementRepository: AreaStpRequirementRepository) {
    suspend operator fun invoke(
        inputFlowRate: Double,
        inputPeakHourAssumption: Double,
        depthAnaerobRoom: Double,
        lengthFiltrationTank: Double,
    ): Flow<SimpleResource> {
        val totalVolumeFiltrationTankRoom = inputFlowRate / inputPeakHourAssumption
        val totalWidthFiltrationTankRoom =
            totalVolumeFiltrationTankRoom / depthAnaerobRoom / lengthFiltrationTank

        return if (!totalVolumeFiltrationTankRoom.isNaN() && !totalWidthFiltrationTankRoom.isNaN()) {
            if (!totalVolumeFiltrationTankRoom.isInfinite() && !totalWidthFiltrationTankRoom.isInfinite()) {
                areaStpRequirementRepository.saveFiltrationTankRoomRequirement(
                    volumeFiltrationTankRoom = totalVolumeFiltrationTankRoom,
                    lengthFiltrationTankRoom = lengthFiltrationTank,
                    widthFiltrationTankRoom = totalWidthFiltrationTankRoom,
                    depthFiltrationTankRoom = depthAnaerobRoom,
                )
            } else if (!totalVolumeFiltrationTankRoom.isInfinite()) {
                areaStpRequirementRepository.saveFiltrationTankRoomRequirement(
                    volumeFiltrationTankRoom = totalVolumeFiltrationTankRoom,
                    lengthFiltrationTankRoom = lengthFiltrationTank,
                    widthFiltrationTankRoom = 0.0,
                    depthFiltrationTankRoom = depthAnaerobRoom,
                )
            }else {
                areaStpRequirementRepository.saveFiltrationTankRoomRequirement(
                    volumeFiltrationTankRoom = 0.0,
                    lengthFiltrationTankRoom = lengthFiltrationTank,
                    widthFiltrationTankRoom = 0.0,
                    depthFiltrationTankRoom = depthAnaerobRoom,
                )
            }
        } else {
            areaStpRequirementRepository.saveFiltrationTankRoomRequirement(
                volumeFiltrationTankRoom = 0.0,
                lengthFiltrationTankRoom = lengthFiltrationTank,
                widthFiltrationTankRoom = 0.0,
                depthFiltrationTankRoom = depthAnaerobRoom,
            )
        }
    }
}