package c.m.pwdo2.area_stp_requirement.domain.use_case

import c.m.pwdo2.area_stp_requirement.domain.repository.AreaStpRequirementRepository
import c.m.pwdo2.common.util.SimpleResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculateRecycleTankRoomRequirementsUseCase @Inject constructor(private val areaStpRequirementRepository: AreaStpRequirementRepository) {
    suspend operator fun invoke(
        depthAnaerobRoom: Double,
        volumeRecyclerTankRoom: Double,
        lengthRecyclerTankRoom: Double,
    ): Flow<SimpleResource> {
        val totalWidthRecyclerTankRoom =
            volumeRecyclerTankRoom / depthAnaerobRoom / lengthRecyclerTankRoom

        return if (!totalWidthRecyclerTankRoom.isNaN()) {
            if (!totalWidthRecyclerTankRoom.isInfinite()) {
                areaStpRequirementRepository.saveRecycleTankRoomRequirement(
                    volumeRecycleTankRoom = volumeRecyclerTankRoom,
                    lengthRecycleTankRoom = lengthRecyclerTankRoom,
                    widthRecycleTankRoom = totalWidthRecyclerTankRoom,
                    depthRecycleTankRoom = depthAnaerobRoom,
                )
            } else {
                areaStpRequirementRepository.saveRecycleTankRoomRequirement(
                    volumeRecycleTankRoom = volumeRecyclerTankRoom,
                    lengthRecycleTankRoom = lengthRecyclerTankRoom,
                    widthRecycleTankRoom = 0.0,
                    depthRecycleTankRoom = depthAnaerobRoom,
                )
            }
        } else {
            areaStpRequirementRepository.saveRecycleTankRoomRequirement(
                volumeRecycleTankRoom = volumeRecyclerTankRoom,
                lengthRecycleTankRoom = lengthRecyclerTankRoom,
                widthRecycleTankRoom = 0.0,
                depthRecycleTankRoom = depthAnaerobRoom,
            )
        }
    }
}