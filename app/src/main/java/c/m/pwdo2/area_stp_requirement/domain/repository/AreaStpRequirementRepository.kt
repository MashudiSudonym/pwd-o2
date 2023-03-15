package c.m.pwdo2.area_stp_requirement.domain.repository

import c.m.pwdo2.area_stp_requirement.domain.model.*
import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.common.util.SimpleResource
import kotlinx.coroutines.flow.Flow

interface AreaStpRequirementRepository {
    suspend fun saveAnaerobRoomRequirement(
        volumeAnaerobRoom: Double,
        lengthAnaerobRoom: Double,
        widthAnaerobRoom: Double,
        depthAnaerobRoom: Double,
    ): Flow<SimpleResource>

    suspend fun readAnaerobRoomRequirement(): Flow<Resource<AnaerobRoomRequirements>>

    suspend fun saveGreaseTrapRoomRequirement(
        volumeGreaseTrapRoom: Double,
        lengthGreaseTrapRoom: Double,
        widthGreaseTrapRoom: Double,
        depthGreaseTrapRoom: Double,
    ): Flow<SimpleResource>

    suspend fun readGreaseTrapRoomRequirement(): Flow<Resource<GreaseTrapRoomRequirements>>

    suspend fun saveAerobRoomRequirement(
        volumeAerobRoom: Double,
        lengthAerobRoom: Double,
        widthAerobRoom: Double,
        depthAerobRoom: Double,
    ): Flow<SimpleResource>

    suspend fun readAerobRoomRequirement(): Flow<Resource<AerobRoomRequirements>>

    suspend fun saveSedimentationRoomRequirement(
        volumeSedimentationRoom: Double,
        lengthSedimentationRoom: Double,
        widthSedimentationRoom: Double,
        depthSedimentationRoom: Double,
    ): Flow<SimpleResource>

    suspend fun readSedimentationRoomRequirement(): Flow<Resource<SedimentationRoomRequirements>>

    suspend fun saveChlorinationRoomRequirement(
        volumeChlorinationRoom: Double,
        lengthChlorinationRoom: Double,
        widthChlorinationRoom: Double,
        depthChlorinationRoom: Double,
    ): Flow<SimpleResource>

    suspend fun readChlorinationRoomRequirement(): Flow<Resource<ChlorinationRoomRequirements>>

    suspend fun saveFiltrationTankRoomRequirement(
        volumeFiltrationTankRoom: Double,
        lengthFiltrationTankRoom: Double,
        widthFiltrationTankRoom: Double,
        depthFiltrationTankRoom: Double,
    ): Flow<SimpleResource>

    suspend fun readFiltrationTankRoomRequirement(): Flow<Resource<FiltrationTankRoomRequirements>>

    suspend fun saveRecycleTankRoomRequirement(
        volumeRecycleTankRoom: Double,
        lengthRecycleTankRoom: Double,
        widthRecycleTankRoom: Double,
        depthRecycleTankRoom: Double,
    ): Flow<SimpleResource>

    suspend fun readRecycleTankRoomRequirement(): Flow<Resource<RecycleTankRoomRequirements>>
}