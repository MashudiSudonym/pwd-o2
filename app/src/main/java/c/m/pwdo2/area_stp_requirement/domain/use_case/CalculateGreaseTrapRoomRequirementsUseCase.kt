package c.m.pwdo2.area_stp_requirement.domain.use_case

import c.m.pwdo2.area_stp_requirement.domain.repository.AreaStpRequirementRepository
import c.m.pwdo2.common.util.SimpleResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculateGreaseTrapRoomRequirementsUseCase @Inject constructor(private val areaStpRequirementRepository: AreaStpRequirementRepository) {
    suspend operator fun invoke(
        retentionTimeGreaseTrap: Double,
        totalChamberGreaseTrap: Double,
        inputFlowRate: Double,
        inputPeakHourAssumption: Double,
        lengthGreaseTrapRoom: Double,
        depthGreaseTrapRoom: Double,
    ): Flow<SimpleResource> {
        val totalVolumeGreaseTrapRoom =
            inputFlowRate / inputPeakHourAssumption / 60 * retentionTimeGreaseTrap * totalChamberGreaseTrap
        val totalWidthGreaseTrapRoom =
            totalVolumeGreaseTrapRoom / depthGreaseTrapRoom / lengthGreaseTrapRoom

        return if (!totalVolumeGreaseTrapRoom.isNaN() && !totalWidthGreaseTrapRoom.isNaN()) {
            if (!totalVolumeGreaseTrapRoom.isInfinite() && !totalWidthGreaseTrapRoom.isInfinite()) {
                areaStpRequirementRepository.saveGreaseTrapRoomRequirement(
                    volumeGreaseTrapRoom = totalVolumeGreaseTrapRoom,
                    lengthGreaseTrapRoom = lengthGreaseTrapRoom,
                    widthGreaseTrapRoom = totalWidthGreaseTrapRoom,
                    depthGreaseTrapRoom = depthGreaseTrapRoom,
                )
            } else if (!totalVolumeGreaseTrapRoom.isInfinite()) {
                areaStpRequirementRepository.saveGreaseTrapRoomRequirement(
                    volumeGreaseTrapRoom = totalVolumeGreaseTrapRoom,
                    lengthGreaseTrapRoom = lengthGreaseTrapRoom,
                    widthGreaseTrapRoom = 0.0,
                    depthGreaseTrapRoom = depthGreaseTrapRoom,
                )
            } else {
                areaStpRequirementRepository.saveGreaseTrapRoomRequirement(
                    volumeGreaseTrapRoom = 0.0,
                    lengthGreaseTrapRoom = lengthGreaseTrapRoom,
                    widthGreaseTrapRoom = 0.0,
                    depthGreaseTrapRoom = depthGreaseTrapRoom,
                )
            }
        } else {
            areaStpRequirementRepository.saveGreaseTrapRoomRequirement(
                volumeGreaseTrapRoom = 0.0,
                lengthGreaseTrapRoom = lengthGreaseTrapRoom,
                widthGreaseTrapRoom = 0.0,
                depthGreaseTrapRoom = depthGreaseTrapRoom,
            )
        }
    }
}