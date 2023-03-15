package c.m.pwdo2.area_stp_requirement.domain.use_case

import c.m.pwdo2.area_stp_requirement.domain.repository.AreaStpRequirementRepository
import c.m.pwdo2.common.util.SimpleResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculateSedimentationRoomRequirementsUseCase @Inject constructor(private val areaStpRequirementRepository: AreaStpRequirementRepository) {
    suspend operator fun invoke(
        retentionTimeSedimentation: Double,
        inputFlowRate: Double,
        inputPeakHourAssumption: Double,
        lengthSedimentationRoom: Double,
        depthAnaerob: Double,
    ): Flow<SimpleResource> {
        val totalVolumeSedimentationRoom =
            inputFlowRate / inputPeakHourAssumption * retentionTimeSedimentation
        val totalWidthSedimentationRoom =
            totalVolumeSedimentationRoom / depthAnaerob / lengthSedimentationRoom

        return if (!totalVolumeSedimentationRoom.isNaN() && !totalWidthSedimentationRoom.isNaN()) {
            if (!totalVolumeSedimentationRoom.isInfinite() && !totalWidthSedimentationRoom.isInfinite()) {
                areaStpRequirementRepository.saveSedimentationRoomRequirement(
                    volumeSedimentationRoom = totalVolumeSedimentationRoom,
                    lengthSedimentationRoom = lengthSedimentationRoom,
                    widthSedimentationRoom = totalWidthSedimentationRoom,
                    depthSedimentationRoom = depthAnaerob,
                )
            } else if (!totalVolumeSedimentationRoom.isInfinite()) {
                areaStpRequirementRepository.saveSedimentationRoomRequirement(
                    volumeSedimentationRoom = totalVolumeSedimentationRoom,
                    lengthSedimentationRoom = lengthSedimentationRoom,
                    widthSedimentationRoom = 0.0,
                    depthSedimentationRoom = depthAnaerob,
                )
            } else {
                areaStpRequirementRepository.saveSedimentationRoomRequirement(
                    volumeSedimentationRoom = 0.0,
                    lengthSedimentationRoom = lengthSedimentationRoom,
                    widthSedimentationRoom = 0.0,
                    depthSedimentationRoom = depthAnaerob,
                )
            }
        } else {
            areaStpRequirementRepository.saveSedimentationRoomRequirement(
                volumeSedimentationRoom = 0.0,
                lengthSedimentationRoom = lengthSedimentationRoom,
                widthSedimentationRoom = 0.0,
                depthSedimentationRoom = depthAnaerob,
            )
        }
    }
}