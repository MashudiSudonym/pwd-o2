package c.m.pwdo2.area_stp_requirement.domain.use_case

import c.m.pwdo2.area_stp_requirement.domain.repository.AreaStpRequirementRepository
import c.m.pwdo2.common.util.SimpleResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculateChlorinationRoomRequirementsUseCase @Inject constructor(private val areaStpRequirementRepository: AreaStpRequirementRepository) {
    suspend operator fun invoke(
        retentionTimeChlorination: Double,
        inputPeakHourAssumption: Double,
        inputFlowRate: Double,
        lengthChlorinationRoom: Double,
        depthChlorinationRoom: Double,
    ): Flow<SimpleResource> {
        val totalVolumeChlorinationRoom =
            inputFlowRate / inputPeakHourAssumption / 60 * retentionTimeChlorination
        val totalWidthChlorinationRoom =
            totalVolumeChlorinationRoom / depthChlorinationRoom / lengthChlorinationRoom

        return if (!totalVolumeChlorinationRoom.isNaN() && !totalWidthChlorinationRoom.isNaN()) {
            if (!totalVolumeChlorinationRoom.isInfinite() && !totalWidthChlorinationRoom.isInfinite()) {
                areaStpRequirementRepository.saveChlorinationRoomRequirement(
                    volumeChlorinationRoom = totalVolumeChlorinationRoom,
                    lengthChlorinationRoom = lengthChlorinationRoom,
                    widthChlorinationRoom = totalWidthChlorinationRoom,
                    depthChlorinationRoom = depthChlorinationRoom,
                )
            }else if (!totalVolumeChlorinationRoom.isInfinite()) {
                areaStpRequirementRepository.saveChlorinationRoomRequirement(
                    volumeChlorinationRoom = totalVolumeChlorinationRoom,
                    lengthChlorinationRoom = lengthChlorinationRoom,
                    widthChlorinationRoom = 0.0,
                    depthChlorinationRoom = depthChlorinationRoom,
                )
            } else {
                areaStpRequirementRepository.saveChlorinationRoomRequirement(
                    volumeChlorinationRoom = 0.0,
                    lengthChlorinationRoom = lengthChlorinationRoom,
                    widthChlorinationRoom = 0.0,
                    depthChlorinationRoom = depthChlorinationRoom,
                )
            }
        } else {
            areaStpRequirementRepository.saveChlorinationRoomRequirement(
                volumeChlorinationRoom = 0.0,
                lengthChlorinationRoom = lengthChlorinationRoom,
                widthChlorinationRoom = 0.0,
                depthChlorinationRoom = depthChlorinationRoom,
            )
        }
    }
}