package c.m.pwdo2.area_stp_requirement.data.repository

import c.m.pwdo2.area_stp_requirement.domain.model.*
import c.m.pwdo2.area_stp_requirement.domain.repository.AreaStpRequirementRepository
import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.data_store.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AreaStpRequirementRepositoryImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    AreaStpRequirementRepository {

    override suspend fun saveAnaerobRoomRequirement(
        volumeAnaerobRoom: Double,
        lengthAnaerobRoom: Double,
        widthAnaerobRoom: Double,
        depthAnaerobRoom: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            emit(Resource.Loading(isLoading = false))
            emit(Resource.Success(dataStoreRepository.setVolumeAnaerobRoom(volumeAnaerobRoom)))
            emit(Resource.Success(dataStoreRepository.setLengthAnaerobRoom(lengthAnaerobRoom)))
            emit(Resource.Success(dataStoreRepository.setWidthAnaerobRoom(widthAnaerobRoom)))
            emit(Resource.Success(dataStoreRepository.setDepthAnaerobRoom(depthAnaerobRoom)))
        }
    }

    override suspend fun readAnaerobRoomRequirement(): Flow<Resource<AnaerobRoomRequirements>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val getVolumeAnaerobRoom = dataStoreRepository.getVolumeAnaerobRoom().getOrDefault(0.0)
            val getLengthAnaerobRoom = dataStoreRepository.getLengthAnaerobRoom().getOrDefault(0.0)
            val getWidthAnaerobRoom = dataStoreRepository.getWidthAnaerobRoom().getOrDefault(0.0)
            val getDepthAnaerobRoom = dataStoreRepository.getDepthAnaerobRoom().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    AnaerobRoomRequirements(
                        volumeAnaerobRoom = getVolumeAnaerobRoom,
                        lengthAnaerobRoom = getLengthAnaerobRoom,
                        widthAnaerobRoom = getWidthAnaerobRoom,
                        depthAnaerobRoom = getDepthAnaerobRoom,
                    )
                )
            )
        }
    }

    override suspend fun saveGreaseTrapRoomRequirement(
        volumeGreaseTrapRoom: Double,
        lengthGreaseTrapRoom: Double,
        widthGreaseTrapRoom: Double,
        depthGreaseTrapRoom: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            emit(Resource.Loading(isLoading = false))
            emit(Resource.Success(dataStoreRepository.setVolumeGreaseTrapRoom(volumeGreaseTrapRoom)))
            emit(Resource.Success(dataStoreRepository.setLengthGreaseTrapRoom(lengthGreaseTrapRoom)))
            emit(Resource.Success(dataStoreRepository.setWidthGreaseTrapRoom(widthGreaseTrapRoom)))
            emit(Resource.Success(dataStoreRepository.setDepthGreaseTrapRoom(depthGreaseTrapRoom)))
        }
    }

    override suspend fun readGreaseTrapRoomRequirement(): Flow<Resource<GreaseTrapRoomRequirements>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val getVolumeGreaseTrapRoom =
                dataStoreRepository.getVolumeGreaseTrapRoom().getOrDefault(0.0)
            val getLengthGreaseTrapRoom =
                dataStoreRepository.getLengthGreaseTrapRoom().getOrDefault(0.0)
            val getWidthGreaseTrapRoom =
                dataStoreRepository.getWidthGreaseTrapRoom().getOrDefault(0.0)
            val getDepthGreaseTrapRoom =
                dataStoreRepository.getDepthGreaseTrapRoom().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    GreaseTrapRoomRequirements(
                        volumeGreaseTrapRoom = getVolumeGreaseTrapRoom,
                        lengthGreaseTrapRoom = getLengthGreaseTrapRoom,
                        widthGreaseTrapRoom = getWidthGreaseTrapRoom,
                        depthGreaseTrapRoom = getDepthGreaseTrapRoom,
                    )
                )
            )
        }
    }

    override suspend fun saveAerobRoomRequirement(
        volumeAerobRoom: Double,
        lengthAerobRoom: Double,
        widthAerobRoom: Double,
        depthAerobRoom: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            emit(Resource.Loading(isLoading = false))
            emit(Resource.Success(dataStoreRepository.setVolumeAerobRoom(volumeAerobRoom)))
            emit(Resource.Success(dataStoreRepository.setLengthAerobRoom(lengthAerobRoom)))
            emit(Resource.Success(dataStoreRepository.setWidthAerobRoom(widthAerobRoom)))
            emit(Resource.Success(dataStoreRepository.setDepthAerobRoom(depthAerobRoom)))
        }
    }

    override suspend fun readAerobRoomRequirement(): Flow<Resource<AerobRoomRequirements>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val getVolumeAerobRoom =
                dataStoreRepository.getVolumeAerobRoom().getOrDefault(0.0)
            val getLengthAerobRoom =
                dataStoreRepository.getLengthAerobRoom().getOrDefault(0.0)
            val getWidthAerobRoom =
                dataStoreRepository.getWidthAerobRoom().getOrDefault(0.0)
            val getDepthAerobRoom =
                dataStoreRepository.getDepthAerobRoom().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    AerobRoomRequirements(
                        volumeAerobRoom = getVolumeAerobRoom,
                        lengthAerobRoom = getLengthAerobRoom,
                        widthAerobRoom = getWidthAerobRoom,
                        depthAerobRoom = getDepthAerobRoom,
                    )
                )
            )
        }
    }

    override suspend fun saveSedimentationRoomRequirement(
        volumeSedimentationRoom: Double,
        lengthSedimentationRoom: Double,
        widthSedimentationRoom: Double,
        depthSedimentationRoom: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    dataStoreRepository.setVolumeSedimentationRoom(
                        volumeSedimentationRoom
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setLengthSedimentationRoom(
                        lengthSedimentationRoom
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setWidthSedimentationRoom(
                        widthSedimentationRoom
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setDepthSedimentationRoom(
                        depthSedimentationRoom
                    )
                )
            )
        }
    }

    override suspend fun readSedimentationRoomRequirement(): Flow<Resource<SedimentationRoomRequirements>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val getVolumeSedimentationRoom =
                dataStoreRepository.getVolumeSedimentationRoom().getOrDefault(0.0)
            val getLengthSedimentationRoom =
                dataStoreRepository.getLengthSedimentationRoom().getOrDefault(0.0)
            val getWidthSedimentationRoom =
                dataStoreRepository.getWidthSedimentationRoom().getOrDefault(0.0)
            val getDepthSedimentationRoom =
                dataStoreRepository.getDepthSedimentationRoom().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    SedimentationRoomRequirements(
                        volumeSedimentationRoom = getVolumeSedimentationRoom,
                        lengthSedimentationRoom = getLengthSedimentationRoom,
                        widthSedimentationRoom = getWidthSedimentationRoom,
                        depthSedimentationRoom = getDepthSedimentationRoom,
                    )
                )
            )
        }
    }

    override suspend fun saveChlorinationRoomRequirement(
        volumeChlorinationRoom: Double,
        lengthChlorinationRoom: Double,
        widthChlorinationRoom: Double,
        depthChlorinationRoom: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    dataStoreRepository.setVolumeChlorinationRoom(
                        volumeChlorinationRoom
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setLengthChlorinationRoom(
                        lengthChlorinationRoom
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setWidthChlorinationRoom(
                        widthChlorinationRoom
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setDepthChlorinationRoom(
                        depthChlorinationRoom
                    )
                )
            )
        }
    }

    override suspend fun readChlorinationRoomRequirement(): Flow<Resource<ChlorinationRoomRequirements>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val getVolumeChlorinationRoom =
                dataStoreRepository.getVolumeChlorinationRoom().getOrDefault(0.0)
            val getLengthChlorinationRoom =
                dataStoreRepository.getLengthChlorinationRoom().getOrDefault(0.0)
            val getWidthChlorinationRoom =
                dataStoreRepository.getWidthChlorinationRoom().getOrDefault(0.0)
            val getDepthChlorinationRoom =
                dataStoreRepository.getDepthChlorinationRoom().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    ChlorinationRoomRequirements(
                        volumeChlorinationRoom = getVolumeChlorinationRoom,
                        lengthChlorinationRoom = getLengthChlorinationRoom,
                        widthChlorinationRoom = getWidthChlorinationRoom,
                        depthChlorinationRoom = getDepthChlorinationRoom,
                    )
                )
            )
        }
    }

    override suspend fun saveFiltrationTankRoomRequirement(
        volumeFiltrationTankRoom: Double,
        lengthFiltrationTankRoom: Double,
        widthFiltrationTankRoom: Double,
        depthFiltrationTankRoom: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    dataStoreRepository.setVolumeFiltrationTankRoom(
                        volumeFiltrationTankRoom
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setLengthFiltrationTankRoom(
                        lengthFiltrationTankRoom
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setWidthFiltrationTankRoom(
                        widthFiltrationTankRoom
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setDepthFiltrationTankRoom(
                        depthFiltrationTankRoom
                    )
                )
            )
        }
    }

    override suspend fun readFiltrationTankRoomRequirement(): Flow<Resource<FiltrationTankRoomRequirements>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val getVolumeFiltrationTankRoom =
                dataStoreRepository.getVolumeFiltrationTankRoom().getOrDefault(0.0)
            val getLengthFiltrationTankRoom =
                dataStoreRepository.getLengthFiltrationTankRoom().getOrDefault(0.0)
            val getWidthFiltrationTankRoom =
                dataStoreRepository.getWidthFiltrationTankRoom().getOrDefault(0.0)
            val getDepthFiltrationTankRoom =
                dataStoreRepository.getDepthFiltrationTankRoom().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    FiltrationTankRoomRequirements(
                        volumeFiltrationTankRoom = getVolumeFiltrationTankRoom,
                        lengthFiltrationTankRoom = getLengthFiltrationTankRoom,
                        widthFiltrationTankRoom = getWidthFiltrationTankRoom,
                        depthFiltrationTankRoom = getDepthFiltrationTankRoom,
                    )
                )
            )
        }
    }

    override suspend fun saveRecycleTankRoomRequirement(
        volumeRecycleTankRoom: Double,
        lengthRecycleTankRoom: Double,
        widthRecycleTankRoom: Double,
        depthRecycleTankRoom: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    dataStoreRepository.setVolumeRecycleTankRoom(
                        volumeRecycleTankRoom
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setLengthRecycleTankRoom(
                        lengthRecycleTankRoom
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setWidthRecycleTankRoom(
                        widthRecycleTankRoom
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setDepthRecycleTankRoom(
                        depthRecycleTankRoom
                    )
                )
            )
        }
    }

    override suspend fun readRecycleTankRoomRequirement(): Flow<Resource<RecycleTankRoomRequirements>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val getVolumeRecycleTankRoom =
                dataStoreRepository.getVolumeRecycleTankRoom().getOrDefault(0.0)
            val getLengthRecycleTankRoom =
                dataStoreRepository.getLengthRecycleTankRoom().getOrDefault(0.0)
            val getWidthRecycleTankRoom =
                dataStoreRepository.getWidthRecycleTankRoom().getOrDefault(0.0)
            val getDepthRecycleTankRoom =
                dataStoreRepository.getDepthRecycleTankRoom().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    RecycleTankRoomRequirements(
                        volumeRecycleTankRoom = getVolumeRecycleTankRoom,
                        lengthRecycleTankRoom = getLengthRecycleTankRoom,
                        widthRecycleTankRoom = getWidthRecycleTankRoom,
                        depthRecycleTankRoom = getDepthRecycleTankRoom,
                    )
                )
            )
        }
    }
}