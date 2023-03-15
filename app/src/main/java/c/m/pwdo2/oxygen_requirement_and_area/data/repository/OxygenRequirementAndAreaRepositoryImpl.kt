package c.m.pwdo2.oxygen_requirement_and_area.data.repository

import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.data_store.domain.repository.DataStoreRepository
import c.m.pwdo2.oxygen_requirement_and_area.domain.model.*
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class OxygenRequirementAndAreaRepositoryImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    OxygenRequirementAndAreaRepository {
    override suspend fun saveStandardValue(
        oneKgBodStandard: Double,
        oneKgCodStandard: Double,
        oneKgNhThreeStandard: Double,
        percentOxygenInAirStandard: Double,
        densityOfOxygenStandard: Double,
        diffuserCapacityTwelveInchStandard: Double,
        diffuserCapacityNineInchStandard: Double,
        percentOxygenTransferEfficiencyStandard: Double,
        effectiveSurfaceAreaDiffTwelveInchStandard: Double,
        effectiveSurfaceAreaDiffNineInchStandard: Double,
        minimumMixingAreaStandard: Double,
        retentionTimeAnaerob: Double,
        percentBwTowardsTotalFlowForHotel: Double,
        percentBwTowardsTotalFlowForIndustry: Double,
        targetBodReductionFromAnaerob: Double,
        retentionTimeAeration: Double,
        targetBodReductionFromAeration: Double,
        targetCorReductionFromAeration: Double,
        targetNhThreeReductionFromAeration: Double,
        retentionTimeGreaseTrap: Double,
        totalChamberGreaseTrap: Double,
        targetFogRemovalFromGreaseTrap: Double,
        retentionTimeSedimentation: Double,
        targetTssRemovalFromGreaseTrap: Double,
        retentionTimeChlorination: Double,
        targetEcoliRemovalFromChlorination: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            emit(Resource.Success(dataStoreRepository.setOneKgBodStandard(oneKgBodStandard)))
            emit(Resource.Success(dataStoreRepository.setOneKgCodStandard(oneKgCodStandard)))
            emit(Resource.Success(dataStoreRepository.setOneKgNhThreeStandard(oneKgNhThreeStandard)))
            emit(
                Resource.Success(
                    dataStoreRepository.setPercentOxygenInAirStandard(
                        percentOxygenInAirStandard
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setDensityOfOxygenStandard(
                        densityOfOxygenStandard
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setDiffuserCapacityTwelveInchStandard(
                        diffuserCapacityTwelveInchStandard
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setDiffuserCapacityNineInchStandard(
                        diffuserCapacityNineInchStandard
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setPercentOxygenTransferEfficiencyStandard(
                        percentOxygenTransferEfficiencyStandard
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setEffectiveSurfaceAreaDiffTwelveInchStandard(
                        effectiveSurfaceAreaDiffTwelveInchStandard
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setEffectiveSurfaceAreaDiffNineInchStandard(
                        effectiveSurfaceAreaDiffNineInchStandard
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setMinimumMixingAreaStandard(
                        minimumMixingAreaStandard
                    )
                )
            )
            emit(Resource.Success(dataStoreRepository.setRetentionTimeAnaerob(retentionTimeAnaerob)))
            emit(
                Resource.Success(
                    dataStoreRepository.setPercentBwTowardsTotalFlowForHotel(
                        percentBwTowardsTotalFlowForHotel
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setPercentBwTowardsTotalFlowForIndustry(
                        percentBwTowardsTotalFlowForIndustry
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setTargetBodReductionFromAnaerob(
                        targetBodReductionFromAnaerob
                    )
                )

            )
            emit(Resource.Success(dataStoreRepository.setRetentionTimeAeration(retentionTimeAeration)))
            emit(
                Resource.Success(
                    dataStoreRepository.setTargetBodReductionFromAeration(
                        targetBodReductionFromAeration
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setTargetCodReductionFromAeration(
                        targetCorReductionFromAeration
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setTargetNhThreeReductionFromAeration(
                        targetNhThreeReductionFromAeration
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setRetentionTimeGreaseTrap(
                        retentionTimeGreaseTrap
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setTotalChamberGreaseTrap(
                        totalChamberGreaseTrap
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setTargetFogRemovalFromGreaseTrap(
                        targetFogRemovalFromGreaseTrap
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setRetentionTimeSedimentation(
                        retentionTimeSedimentation
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setTargetTssRemovalFromGreaseTrap(
                        targetTssRemovalFromGreaseTrap
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setRetentionTimeChlorination(
                        retentionTimeChlorination
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setTargetEcoliRemovalFromChlorination(
                        targetEcoliRemovalFromChlorination
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveInputParameters(
        inputBodInlet: Double,
        inputBodOutlet: Double,
        inputCodInlet: Double,
        inputCodOutlet: Double,
        inputNhThreeInlet: Double,
        inputNhThreeOutlet: Double,
        inputFlowRate: Double,
        inputPeakHourAssumption: Double,
        inputWaterDepthAboveDiffuser: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            emit(Resource.Success(dataStoreRepository.setInputBodInlet(inputBodInlet)))
            emit(Resource.Success(dataStoreRepository.setInputBodOutlet(inputBodOutlet)))
            emit(Resource.Success(dataStoreRepository.setInputCodInlet(inputCodInlet)))
            emit(Resource.Success(dataStoreRepository.setInputCodOutlet(inputCodOutlet)))
            emit(Resource.Success(dataStoreRepository.setInputNhThreeInlet(inputNhThreeInlet)))
            emit(Resource.Success(dataStoreRepository.setInputNhThreeOutlet(inputNhThreeOutlet)))
            emit(Resource.Success(dataStoreRepository.setInputFlowRate(inputFlowRate)))
            emit(
                Resource.Success(
                    dataStoreRepository.setInputPeakHourAssumption(
                        inputPeakHourAssumption
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setInputWaterDepthAboveDiffuser(
                        inputWaterDepthAboveDiffuser
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun readStandardValue(): Flow<Resource<StandardValue>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val getOneKgBodStandard = dataStoreRepository.getOneKgBodStandard().getOrDefault(0.0)
            val getOneKgCodStandard = dataStoreRepository.getOneKgCodStandard().getOrDefault(0.0)
            val getOneKgNhThreeStandard =
                dataStoreRepository.getOneKgNhThreeStandard().getOrDefault(0.0)
            val getPercentOxygenInAirStandard =
                dataStoreRepository.getPercentOxygenInAirStandard().getOrDefault(0.0)
            val getDensityOfOxygenStandard =
                dataStoreRepository.getDensityOfOxygenStandard().getOrDefault(0.0)
            val getDiffuserCapacityTwelveInchStandard =
                dataStoreRepository.getDiffuserCapacityTwelveInchStandard().getOrDefault(0.0)
            val getDiffuserCapacityNineInchStandard =
                dataStoreRepository.getDiffuserCapacityNineInchStandard().getOrDefault(0.0)
            val getPercentOxygenTransferEfficiencyStandard =
                dataStoreRepository.getPercentOxygenTransferEfficiencyStandard().getOrDefault(0.0)
            val getEffectiveSurfaceAreaDiffTwelveInchStandard =
                dataStoreRepository.getEffectiveSurfaceAreaDiffTwelveInchStandard()
                    .getOrDefault(0.0)
            val getEffectiveSurfaceAreaDiffNineInchStandard =
                dataStoreRepository.getEffectiveSurfaceAreaDiffNineInchStandard().getOrDefault(0.0)
            val getMinimumMixingAreaStandard =
                dataStoreRepository.getMinimumMixingAreaStandard().getOrDefault(0.0)
            val getRetentionTimeAnaerob =
                dataStoreRepository.getRetentionTimeAnaerob().getOrDefault(0.0)
            val getPercentBwTowardsTotalFlowForHotel =
                dataStoreRepository.getPercentBwTowardsTotalFlowForHotel().getOrDefault(0.0)
            val getPercentBwTowardsTotalFlowForIndustry =
                dataStoreRepository.getPercentBwTowardsTotalFlowForIndustry().getOrDefault(0.0)
            val getTargetBodReductionFromAnaerob =
                dataStoreRepository.getTargetBodReductionFromAnaerob().getOrDefault(0.0)
            val getRetentionTimeAeration =
                dataStoreRepository.getRetentionTimeAeration().getOrDefault(0.0)
            val getTargetBodReductionFromAeration =
                dataStoreRepository.getTargetBodReductionFromAeration().getOrDefault(0.0)
            val getTargetCodReductionFromAeration =
                dataStoreRepository.getTargetCodReductionFromAeration().getOrDefault(0.0)
            val getTargetNhThreeReductionFromAeration =
                dataStoreRepository.getTargetNhThreeReductionFromAeration().getOrDefault(0.0)
            val getRetentionTimeGreaseTrap =
                dataStoreRepository.getRetentionTimeGreaseTrap().getOrDefault(0.0)
            val getTotalChamberGreaseTrap =
                dataStoreRepository.getTotalChamberGreaseTrap().getOrDefault(0.0)
            val getTargetFogRemovalFromGreaseTrap =
                dataStoreRepository.getTargetFogRemovalFromGreaseTrap().getOrDefault(0.0)
            val getRetentionTimeSedimentation =
                dataStoreRepository.getRetentionTimeSedimentation().getOrDefault(0.0)
            val getTargetTssRemovalFromGreaseTrap =
                dataStoreRepository.getTargetTssRemovalFromGreaseTrap().getOrDefault(0.0)
            val getRetentionTimeChlorination =
                dataStoreRepository.getRetentionTimeChlorination().getOrDefault(0.0)
            val getTargetEcoliRemovalFromChlorination =
                dataStoreRepository.getTargetEcoliRemovalFromChlorination().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    StandardValue(
                        oneKgBodStandard = getOneKgBodStandard,
                        oneKgCodStandard = getOneKgCodStandard,
                        oneKgNhThreeStandard = getOneKgNhThreeStandard,
                        percentOxygenInAirStandard = getPercentOxygenInAirStandard,
                        densityOfOxygenStandard = getDensityOfOxygenStandard,
                        diffuserCapacityTwelveInchStandard = getDiffuserCapacityTwelveInchStandard,
                        diffuserCapacityNineInchStandard = getDiffuserCapacityNineInchStandard,
                        percentOxygenTransferEfficiencyStandard = getPercentOxygenTransferEfficiencyStandard,
                        effectiveSurfaceAreaDiffTwelveInchStandard = getEffectiveSurfaceAreaDiffTwelveInchStandard,
                        effectiveSurfaceAreaDiffNineInchStandard = getEffectiveSurfaceAreaDiffNineInchStandard,
                        minimumMixingAreaStandard = getMinimumMixingAreaStandard,
                        retentionTimeAnaerob = getRetentionTimeAnaerob,
                        percentBwTowardsTotalFlowForHotel = getPercentBwTowardsTotalFlowForHotel,
                        percentBwTowardsTotalFlowForIndustry = getPercentBwTowardsTotalFlowForIndustry,
                        targetBodReductionFromAnaerob = getTargetBodReductionFromAnaerob,
                        retentionTimeAeration = getRetentionTimeAeration,
                        targetBodReductionFromAeration = getTargetBodReductionFromAeration,
                        targetCodReductionFromAeration = getTargetCodReductionFromAeration,
                        targetNhThreeReductionFromAeration = getTargetNhThreeReductionFromAeration,
                        retentionTimeGreaseTrap = getRetentionTimeGreaseTrap,
                        totalChamberGreaseTrap = getTotalChamberGreaseTrap,
                        targetFogRemovalFromGreaseTrap = getTargetFogRemovalFromGreaseTrap,
                        retentionTimeSedimentation = getRetentionTimeSedimentation,
                        targetTssRemovalFromGreaseTrap = getTargetTssRemovalFromGreaseTrap,
                        retentionTimeChlorination = getRetentionTimeChlorination,
                        targetEcoliRemovalFromChlorination = getTargetEcoliRemovalFromChlorination,
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun readInputParameters(): Flow<Resource<InputParameters>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val getInputBodInlet = dataStoreRepository.getInputBodInlet().getOrDefault(0.0)
            val getInputBodOutlet = dataStoreRepository.getInputBodOutlet().getOrDefault(0.0)
            val getInputCodInlet = dataStoreRepository.getInputCodInlet().getOrDefault(0.0)
            val getInputCodOutlet = dataStoreRepository.getInputCodOutlet().getOrDefault(0.0)
            val getInputNhThreeInlet = dataStoreRepository.getInputNhThreeInlet().getOrDefault(0.0)
            val getInputNhThreeOutlet =
                dataStoreRepository.getInputNhThreeOutlet().getOrDefault(0.0)
            val getInputFlowRate = dataStoreRepository.getInputFlowRate().getOrDefault(0.0)
            val getInputPeakHourAssumption =
                dataStoreRepository.getInputPeakHourAssumption().getOrDefault(0.0)
            val getInputWaterDepthAboveDiffuser =
                dataStoreRepository.getInputWaterDepthAboveDiffuser().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    InputParameters(
                        inputBodInlet = getInputBodInlet,
                        inputBodOutlet = getInputBodOutlet,
                        inputCodInlet = getInputCodInlet,
                        inputCodOutlet = getInputCodOutlet,
                        inputNhThreeInlet = getInputNhThreeInlet,
                        inputNhThreeOutlet = getInputNhThreeOutlet,
                        inputFlowRate = getInputFlowRate,
                        inputPeakHourAssumption = getInputPeakHourAssumption,
                        inputWaterDepthAboveDiffuser = getInputWaterDepthAboveDiffuser,
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveDiffuserDepthEfficiency(diffuserDepthEfficiency: Double): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    dataStoreRepository.setPercentDiffuserDepthEfficiency(
                        diffuserDepthEfficiency
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun readDiffuserDepthEfficiency(): Flow<Resource<DiffuserDepthEfficiency>> {
        return flow {
            val getDiffuserDepthEfficiency =
                dataStoreRepository.getPercentDiffuserDepthEfficiency().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = true))
            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    DiffuserDepthEfficiency(
                        diffuserDepthEfficiency = getDiffuserDepthEfficiency,
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveTotalInfluentLoadingPerHour(
        totalBodInlet: Double,
        totalCodInlet: Double,
        totalNhThreeInlet: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            emit(Resource.Success(dataStoreRepository.setTotalBodInlet(totalBodInlet)))
            emit(Resource.Success(dataStoreRepository.setTotalCodInlet(totalCodInlet)))
            emit(Resource.Success(dataStoreRepository.setTotalNhThreeInlet(totalNhThreeInlet)))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun readTotalInfluentLoadingPerHour(): Flow<Resource<TotalInfluentLoadingPerHour>> {
        return flow {
            val getTotalBodInlet = dataStoreRepository.getTotalBodInlet().getOrDefault(0.0)
            val getTotalCodInlet = dataStoreRepository.getTotalCodInlet().getOrDefault(0.0)
            val getTotalNhThreeInlet = dataStoreRepository.getTotalNhThreeInlet().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    TotalInfluentLoadingPerHour(
                        totalBodInlet = getTotalBodInlet,
                        totalCodInlet = getTotalCodInlet,
                        totalNhThreeInlet = getTotalNhThreeInlet,
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveAirRequirement(
        forBodPerMinute: Double,
        forCodPerMinute: Double,
        forNhThreePerMinute: Double,
        totalAirRequirementPerMinute: Double,
        forBodPerHour: Double,
        forCodPerHour: Double,
        forNhThreePerHour: Double,
        totalAirRequirementPerHour: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            emit(Resource.Success(dataStoreRepository.setForBodPerMinute(forBodPerMinute)))
            emit(Resource.Success(dataStoreRepository.setForCodPerMinute(forCodPerMinute)))
            emit(Resource.Success(dataStoreRepository.setForNhThreePerMinute(forNhThreePerMinute)))
            emit(
                Resource.Success(
                    dataStoreRepository.setTotalAirRequirementPerMinute(
                        totalAirRequirementPerMinute
                    )
                )
            )
            emit(Resource.Success(dataStoreRepository.setForBodPerHour(forBodPerHour)))
            emit(Resource.Success(dataStoreRepository.setForCodPerHour(forCodPerHour)))
            emit(Resource.Success(dataStoreRepository.setForNhThreePerHour(forNhThreePerHour)))
            emit(
                Resource.Success(
                    dataStoreRepository.setTotalAirRequirementPerHour(
                        totalAirRequirementPerHour
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun readAirRequirement(): Flow<Resource<AirRequirement>> {
        return flow {
            val getForBodPerMinute = dataStoreRepository.getForBodPerMinute().getOrDefault(0.0)
            val getForCodPerMinute = dataStoreRepository.getForCodPerMinute().getOrDefault(0.0)
            val getForNhThreePerMinute =
                dataStoreRepository.getForNhThreePerMinute().getOrDefault(0.0)
            val getTotalAirRequirementPerMinute =
                dataStoreRepository.getTotalAirRequirementPerMinute().getOrDefault(0.0)
            val getForBodPerHour = dataStoreRepository.getForBodPerHour().getOrDefault(0.0)
            val getForCodPerHour = dataStoreRepository.getForCodPerHour().getOrDefault(0.0)
            val getForNhThreePerHour = dataStoreRepository.getForNhThreePerHour().getOrDefault(0.0)
            val getTotalAirRequirementPerHour =
                dataStoreRepository.getTotalAirRequirementPerHour().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    AirRequirement(
                        forBodPerMinute = getForBodPerMinute,
                        forCodPerMinute = getForCodPerMinute,
                        forNhThreePerMinute = getForNhThreePerMinute,
                        totalAirRequirementPerMinute = getTotalAirRequirementPerMinute,
                        forBodPerHour = getForBodPerHour,
                        forCodPerHour = getForCodPerHour,
                        forNhThreePerHour = getForNhThreePerHour,
                        totalAirRequirementPerHour = getTotalAirRequirementPerHour,
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveDiffuserRequirement(
        diffuserTwelveUnit: Double,
        diffuserTwelveUnitRoundUp: Double,
        diffuserNineUnit: Double,
        diffuserNineUnitRoundUp: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            emit(Resource.Success(dataStoreRepository.setDiffuserTwelveInchUnit(diffuserTwelveUnit)))
            emit(
                Resource.Success(
                    dataStoreRepository.setDiffuserTwelveInchUnitRoundUp(
                        diffuserTwelveUnitRoundUp
                    )
                )
            )
            emit(Resource.Success(dataStoreRepository.setDiffuserNineInchUnit(diffuserNineUnit)))
            emit(
                Resource.Success(
                    dataStoreRepository.setDiffuserNineInchUnitRoundUp(
                        diffuserNineUnitRoundUp
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun readDiffuserRequirement(): Flow<Resource<DiffuserRequirement>> {
        return flow {
            val getDiffuserTwelveInchUnit =
                dataStoreRepository.getDiffuserTwelveInchUnit().getOrDefault(0.0)
            val getDiffuserTwelveInchUnitRoundUp =
                dataStoreRepository.getDiffuserTwelveInchUnitRoundUp().getOrDefault(0.0)
            val getDiffuserNineInchUnit =
                dataStoreRepository.getDiffuserNineInchUnit().getOrDefault(0.0)
            val getDiffuserNineInchUnitRoundUp =
                dataStoreRepository.getDiffuserNineInchUnitRoundUp().getOrDefault(0.0)

            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    DiffuserRequirement(
                        diffuserTwelveUnit = getDiffuserTwelveInchUnit,
                        diffuserTwelveUnitRoundUp = getDiffuserTwelveInchUnitRoundUp,
                        diffuserNineUnit = getDiffuserNineInchUnit,
                        diffuserNineUnitRoundUp = getDiffuserNineInchUnitRoundUp,
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveBlowerCapacityRequirement(
        blowerCapacityRequirementPerMinute: Double,
        blowerCapacityRequirementPerHour: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    dataStoreRepository.setBlowerCapacityRequirementPerMinute(
                        blowerCapacityRequirementPerMinute
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setBlowerCapacityRequirementPerHour(
                        blowerCapacityRequirementPerHour
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun readBlowerCapacityRequirement(): Flow<Resource<BlowerCapacityRequirement>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            val getBlowerCapacityRequirementPerMinute =
                dataStoreRepository.getBlowerCapacityRequirementPerMinute().getOrDefault(0.0)
            val getBlowerCapacityRequirementPerHour =
                dataStoreRepository.getBlowerCapacityRequirementPerHour().getOrDefault(0.0)

            emit(
                Resource.Success(
                    BlowerCapacityRequirement(
                        blowerCapacityRequirementPerMinute = getBlowerCapacityRequirementPerMinute,
                        blowerCapacityRequirementPerHour = getBlowerCapacityRequirementPerHour,
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveAreaVolumeRequirement(
        aerationChamberAreaWithDiffuserTwelveInch: Double,
        aerationChamberVolumeWithDiffuserTwelveInch: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            emit(Resource.Loading(isLoading = false))
            emit(
                Resource.Success(
                    dataStoreRepository.setAerationChamberVolumeWithDiffuserTwelveInch(
                        aerationChamberVolumeWithDiffuserTwelveInch
                    )
                )
            )
            emit(
                Resource.Success(
                    dataStoreRepository.setAerationChamberAreaWithDiffuserTwelveInch(
                        aerationChamberAreaWithDiffuserTwelveInch
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun readAreaVolumeRequirement(): Flow<Resource<AreaVolumeRequirement>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val getAerationChamberAreaWithDiffuserTwelveInch =
                dataStoreRepository.getAerationChamberAreaWithDiffuserTwelveInch().getOrDefault(0.0)
            val getAerationChamberVolumeWithDiffuserTwelveInch =
                dataStoreRepository.getAerationChamberVolumeWithDiffuserTwelveInch()
                    .getOrDefault(0.0)
            emit(Resource.Loading(isLoading = false))

            emit(
                Resource.Success(
                    AreaVolumeRequirement(
                        aerationChamberAreaWithDiffuserTwelveInch = getAerationChamberAreaWithDiffuserTwelveInch,
                        aerationChamberVolumeWithDiffuserTwelveInch = getAerationChamberVolumeWithDiffuserTwelveInch,
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveParametersForAreaStp(
        bodOutletAnaerob: Double,
        bodOutletAerob: Double,
        codOutletAerob: Double,
        nhThreeAerob: Double,
    ): Flow<SimpleResource> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            emit(Resource.Loading(isLoading = false))

            emit(Resource.Success(dataStoreRepository.setBodOutletAnaerob(bodOutletAnaerob)))
            emit(Resource.Success(dataStoreRepository.setBodOutletAerob(bodOutletAerob)))
            emit(Resource.Success(dataStoreRepository.setCodOutletAerob(codOutletAerob)))
            emit(Resource.Success(dataStoreRepository.setNhThreeAerob(nhThreeAerob)))
        }.flowOn(Dispatchers.IO)
    }
}