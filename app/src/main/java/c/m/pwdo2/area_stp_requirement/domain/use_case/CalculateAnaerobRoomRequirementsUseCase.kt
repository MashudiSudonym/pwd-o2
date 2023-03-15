package c.m.pwdo2.area_stp_requirement.domain.use_case

import c.m.pwdo2.area_stp_requirement.domain.repository.AreaStpRequirementRepository
import c.m.pwdo2.common.util.SimpleResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculateAnaerobRoomRequirementsUseCase @Inject constructor(private val areaStpRequirementRepository: AreaStpRequirementRepository) {
    suspend operator fun invoke(
        retentionTimeAnaerob: Double,
        percentBwTowardsTotalFlowForHotel: Double,
        inputFlowRate: Double,
        lengthAnaerobRoomRequirements: Double,
        inputWaterDepthAboveDiffuser: Double,
    ): Flow<SimpleResource> {
        val totalVolumeAnaerobRoomRequirements =
            inputFlowRate * percentBwTowardsTotalFlowForHotel * retentionTimeAnaerob
        val totalDepthAnaerobRoomRequirements = if (inputWaterDepthAboveDiffuser == 0.0) {
            0.0
        } else {
            inputWaterDepthAboveDiffuser + 0.3
        }
        val totalWidthAnaerobRoomRequirements =
            totalVolumeAnaerobRoomRequirements / totalDepthAnaerobRoomRequirements / lengthAnaerobRoomRequirements

        return if (!totalVolumeAnaerobRoomRequirements.isNaN() && !totalDepthAnaerobRoomRequirements.isNaN() && !totalWidthAnaerobRoomRequirements.isNaN()) {
            if (!totalWidthAnaerobRoomRequirements.isInfinite() && !totalVolumeAnaerobRoomRequirements.isInfinite() && !totalDepthAnaerobRoomRequirements.isInfinite()) {
                areaStpRequirementRepository.saveAnaerobRoomRequirement(
                    volumeAnaerobRoom = totalVolumeAnaerobRoomRequirements,
                    lengthAnaerobRoom = lengthAnaerobRoomRequirements,
                    depthAnaerobRoom = totalDepthAnaerobRoomRequirements,
                    widthAnaerobRoom = totalWidthAnaerobRoomRequirements,
                )
            } else  if (!totalVolumeAnaerobRoomRequirements.isInfinite() && !totalDepthAnaerobRoomRequirements.isInfinite()) {
                areaStpRequirementRepository.saveAnaerobRoomRequirement(
                    volumeAnaerobRoom = totalVolumeAnaerobRoomRequirements,
                    lengthAnaerobRoom = lengthAnaerobRoomRequirements,
                    depthAnaerobRoom = totalDepthAnaerobRoomRequirements,
                    widthAnaerobRoom = 0.0,
                )
            }else {
                areaStpRequirementRepository.saveAnaerobRoomRequirement(
                    volumeAnaerobRoom = totalVolumeAnaerobRoomRequirements,
                    lengthAnaerobRoom = lengthAnaerobRoomRequirements,
                    depthAnaerobRoom = totalDepthAnaerobRoomRequirements,
                    widthAnaerobRoom = 0.0,
                )
            }
        } else {
            areaStpRequirementRepository.saveAnaerobRoomRequirement(
                volumeAnaerobRoom = totalVolumeAnaerobRoomRequirements,
                lengthAnaerobRoom = lengthAnaerobRoomRequirements,
                depthAnaerobRoom = totalDepthAnaerobRoomRequirements,
                widthAnaerobRoom = 0.0,
            )
        }
    }
}