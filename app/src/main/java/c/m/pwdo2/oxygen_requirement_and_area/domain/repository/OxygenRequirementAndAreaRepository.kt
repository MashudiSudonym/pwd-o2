package c.m.pwdo2.oxygen_requirement_and_area.domain.repository

import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.oxygen_requirement_and_area.domain.model.*
import kotlinx.coroutines.flow.Flow

interface OxygenRequirementAndAreaRepository {
    suspend fun saveStandardValue(
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
    ): Flow<SimpleResource>

    suspend fun saveInputParameters(
        inputBodInlet: Double,
        inputBodOutlet: Double,
        inputCodInlet: Double,
        inputCodOutlet: Double,
        inputNhThreeInlet: Double,
        inputNhThreeOutlet: Double,
        inputFlowRate: Double,
        inputPeakHourAssumption: Double,
        inputWaterDepthAboveDiffuser: Double,
    ): Flow<SimpleResource>

    suspend fun saveDiffuserDepthEfficiency(diffuserDepthEfficiency: Double): Flow<SimpleResource>

    suspend fun saveTotalInfluentLoadingPerHour(
        totalBodInlet: Double,
        totalCodInlet: Double,
        totalNhThreeInlet: Double,
    ): Flow<SimpleResource>

    suspend fun saveAirRequirement(
        forBodPerMinute: Double,
        forCodPerMinute: Double,
        forNhThreePerMinute: Double,
        totalAirRequirementPerMinute: Double,
        forBodPerHour: Double,
        forCodPerHour: Double,
        forNhThreePerHour: Double,
        totalAirRequirementPerHour: Double,
    ): Flow<SimpleResource>

    suspend fun saveDiffuserRequirement(
        diffuserTwelveUnit: Double,
        diffuserTwelveUnitRoundUp: Double,
        diffuserNineUnit: Double,
        diffuserNineUnitRoundUp: Double,
    ): Flow<SimpleResource>

    suspend fun saveBlowerCapacityRequirement(
        blowerCapacityRequirementPerMinute: Double,
        blowerCapacityRequirementPerHour: Double,
    ): Flow<SimpleResource>

    suspend fun saveAreaVolumeRequirement(
        aerationChamberAreaWithDiffuserTwelveInch: Double = 0.0,
        aerationChamberVolumeWithDiffuserTwelveInch: Double = 0.0,
    ): Flow<SimpleResource>

    suspend fun saveParametersForAreaStp(
        bodOutletAnaerob: Double,
        bodOutletAerob: Double,
        codOutletAerob: Double,
        nhThreeAerob: Double,
    ): Flow<SimpleResource>

    suspend fun readStandardValue(): Flow<Resource<StandardValue>>
    suspend fun readInputParameters(): Flow<Resource<InputParameters>>
    suspend fun readDiffuserDepthEfficiency(): Flow<Resource<DiffuserDepthEfficiency>>
    suspend fun readTotalInfluentLoadingPerHour(): Flow<Resource<TotalInfluentLoadingPerHour>>
    suspend fun readAirRequirement(): Flow<Resource<AirRequirement>>
    suspend fun readDiffuserRequirement(): Flow<Resource<DiffuserRequirement>>
    suspend fun readBlowerCapacityRequirement(): Flow<Resource<BlowerCapacityRequirement>>
    suspend fun readAreaVolumeRequirement(): Flow<Resource<AreaVolumeRequirement>>
}