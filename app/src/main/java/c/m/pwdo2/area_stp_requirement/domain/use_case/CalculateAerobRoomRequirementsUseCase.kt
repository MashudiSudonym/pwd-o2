package c.m.pwdo2.area_stp_requirement.domain.use_case

import c.m.pwdo2.area_stp_requirement.domain.repository.AreaStpRequirementRepository
import c.m.pwdo2.common.util.SimpleResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculateAerobRoomRequirementsUseCase @Inject constructor(private val areaStpRequirementRepository: AreaStpRequirementRepository) {
    suspend operator fun invoke(
        aerationChamberVolumeWithDiffuserTwelveInch: Double,
        depthAnaerobRoom: Double,
        lengthAerobRoom: Double,
    ): Flow<SimpleResource> {
        val totalWidthAerobRoom =
            aerationChamberVolumeWithDiffuserTwelveInch / depthAnaerobRoom / lengthAerobRoom

        return if (!totalWidthAerobRoom.isNaN()) {
            if (!totalWidthAerobRoom.isInfinite()) {
                areaStpRequirementRepository.saveAerobRoomRequirement(
                    lengthAerobRoom = lengthAerobRoom,
                    widthAerobRoom = totalWidthAerobRoom,
                    volumeAerobRoom = aerationChamberVolumeWithDiffuserTwelveInch,
                    depthAerobRoom = depthAnaerobRoom,
                )
            } else {
                areaStpRequirementRepository.saveAerobRoomRequirement(
                    lengthAerobRoom = lengthAerobRoom,
                    widthAerobRoom = 0.0,
                    volumeAerobRoom = aerationChamberVolumeWithDiffuserTwelveInch,
                    depthAerobRoom = depthAnaerobRoom,
                )
            }
        } else {
            areaStpRequirementRepository.saveAerobRoomRequirement(
                lengthAerobRoom = lengthAerobRoom,
                widthAerobRoom = 0.0,
                volumeAerobRoom = aerationChamberVolumeWithDiffuserTwelveInch,
                depthAerobRoom = depthAnaerobRoom,
            )
        }
    }
}