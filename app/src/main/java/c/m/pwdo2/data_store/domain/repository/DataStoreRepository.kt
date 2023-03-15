package c.m.pwdo2.data_store.domain.repository

interface DataStoreRepository {
    // Set authentication status
    suspend fun setAuthenticationStatus(authenticationStatus: Boolean)

    // Get authentication Status
    suspend fun getAuthenticationStatus(): Result<Boolean>

    // Set DLH Standard Value
    suspend fun setDlhBodOutlet(dlhBodOutlet: Double)
    suspend fun setDlhCodOutlet(dlhCodOutlet: Double)
    suspend fun setDlhNhThreeOutlet(dlhNhThreeOutlet: Double)

    // Set Standard Value for Calculation
    suspend fun setOneKgBodStandard(oneKgBodStandard: Double)
    suspend fun setOneKgCodStandard(oneKgCodStandard: Double)
    suspend fun setOneKgNhThreeStandard(oneKgNhThreeStandard: Double)
    suspend fun setPercentOxygenInAirStandard(percentOxygenInAirStandard: Double)
    suspend fun setDensityOfOxygenStandard(densityOfOxygenStandard: Double)
    suspend fun setDiffuserCapacityTwelveInchStandard(diffuserCapacityTwelveInchStandard: Double)
    suspend fun setDiffuserCapacityNineInchStandard(diffuserCapacityNineInchStandard: Double)
    suspend fun setPercentOxygenTransferEfficiencyStandard(percentOxygenTransferEfficiencyStandard: Double)
    suspend fun setEffectiveSurfaceAreaDiffTwelveInchStandard(
        effectiveSurfaceAreaDiffTwelveInchStandard: Double,
    )

    suspend fun setEffectiveSurfaceAreaDiffNineInchStandard(
        effectiveSurfaceAreaDiffNineInchStandard: Double,
    )

    suspend fun setMinimumMixingAreaStandard(minimumMixingAreaStandard: Double)

    // Get DLH Standard Value
    suspend fun getDlhBodOutlet(): Result<Double>
    suspend fun getDlhCodOutlet(): Result<Double>
    suspend fun getDlhNhThreeOutlet(): Result<Double>

    // Get Standard Value Calculation
    suspend fun getOneKgBodStandard(): Result<Double>
    suspend fun getOneKgCodStandard(): Result<Double>
    suspend fun getOneKgNhThreeStandard(): Result<Double>
    suspend fun getPercentOxygenInAirStandard(): Result<Double>
    suspend fun getDensityOfOxygenStandard(): Result<Double>
    suspend fun getDiffuserCapacityTwelveInchStandard(): Result<Double>
    suspend fun getDiffuserCapacityNineInchStandard(): Result<Double>
    suspend fun getPercentOxygenTransferEfficiencyStandard(): Result<Double>
    suspend fun getEffectiveSurfaceAreaDiffTwelveInchStandard(): Result<Double>
    suspend fun getEffectiveSurfaceAreaDiffNineInchStandard(): Result<Double>
    suspend fun getMinimumMixingAreaStandard(): Result<Double>

    // Set Input Parameter
    suspend fun setInputBodInlet(inputBodInlet: Double)
    suspend fun setInputBodOutlet(inputBodOutlet: Double)
    suspend fun setInputCodInlet(inputCodInlet: Double)
    suspend fun setInputCodOutlet(inputCodOutlet: Double)
    suspend fun setInputNhThreeInlet(inputNhThreeInlet: Double)
    suspend fun setInputNhThreeOutlet(inputNhThreeOutlet: Double)
    suspend fun setInputFlowRate(inputFlowRate: Double)
    suspend fun setInputPeakHourAssumption(inputPeakHourAssumption: Double)
    suspend fun setInputWaterDepthAboveDiffuser(inputWaterDepthAboveDiffuser: Double)

    // Get Input Parameter
    suspend fun getInputBodInlet(): Result<Double>
    suspend fun getInputBodOutlet(): Result<Double>
    suspend fun getInputCodInlet(): Result<Double>
    suspend fun getInputCodOutlet(): Result<Double>
    suspend fun getInputNhThreeInlet(): Result<Double>
    suspend fun getInputNhThreeOutlet(): Result<Double>
    suspend fun getInputFlowRate(): Result<Double>
    suspend fun getInputPeakHourAssumption(): Result<Double>
    suspend fun getInputWaterDepthAboveDiffuser(): Result<Double>

    // Set Result Calculation Parameter
    suspend fun setPercentDiffuserDepthEfficiency(percentDiffuserDepthEfficiency: Double)

    // Get Result Calculation Parameter
    suspend fun getPercentDiffuserDepthEfficiency(): Result<Double>

    // Set Total influent Loading BOD, COD, & NH3 per hour
    suspend fun setTotalBodInlet(totalBodInlet: Double)
    suspend fun setTotalCodInlet(totalCodInlet: Double)
    suspend fun setTotalNhThreeInlet(totalNhThreeInlet: Double)

    // Get Total influent Loading BOD, COD, & NH3 per hour
    suspend fun getTotalBodInlet(): Result<Double>
    suspend fun getTotalCodInlet(): Result<Double>
    suspend fun getTotalNhThreeInlet(): Result<Double>

    // Set Air Requirement Per Minute and Per Hour
    suspend fun setForBodPerMinute(forBodPerMinute: Double)
    suspend fun setForCodPerMinute(forCodPerMinute: Double)
    suspend fun setForNhThreePerMinute(forNhThreePerMinute: Double)
    suspend fun setTotalAirRequirementPerMinute(totalAirRequirementPerMinute: Double)
    suspend fun setForBodPerHour(forBodPerHour: Double)
    suspend fun setForCodPerHour(forCodPerHour: Double)
    suspend fun setForNhThreePerHour(forNhThreePerHour: Double)
    suspend fun setTotalAirRequirementPerHour(totalAirRequirementPerHour: Double)

    // Get Air Requirement Per Minute and Per Hour
    suspend fun getForBodPerMinute(): Result<Double>
    suspend fun getForCodPerMinute(): Result<Double>
    suspend fun getForNhThreePerMinute(): Result<Double>
    suspend fun getTotalAirRequirementPerMinute(): Result<Double>
    suspend fun getForBodPerHour(): Result<Double>
    suspend fun getForCodPerHour(): Result<Double>
    suspend fun getForNhThreePerHour(): Result<Double>
    suspend fun getTotalAirRequirementPerHour(): Result<Double>

    // Set Diffuser Requirement
    suspend fun setDiffuserTwelveInchUnit(diffuserTwelveUnit: Double)
    suspend fun setDiffuserTwelveInchUnitRoundUp(diffuserTwelveUnitRoundUp: Double)
    suspend fun setDiffuserNineInchUnit(diffuserNineUnit: Double)
    suspend fun setDiffuserNineInchUnitRoundUp(diffuserNineUnitRoundUp: Double)

    // Get Diffuser Requirement
    suspend fun getDiffuserTwelveInchUnit(): Result<Double>
    suspend fun getDiffuserTwelveInchUnitRoundUp(): Result<Double>
    suspend fun getDiffuserNineInchUnit(): Result<Double>
    suspend fun getDiffuserNineInchUnitRoundUp(): Result<Double>

    // Set Blower Capacity Requirement Per Minute and Per Hour
    suspend fun setBlowerCapacityRequirementPerMinute(blowerCapacityRequirementPerMinute: Double)
    suspend fun setBlowerCapacityRequirementPerHour(blowerCapacityRequirementPerHour: Double)

    // Get Blower Capacity Requirement Per Minute and Per Hour
    suspend fun getBlowerCapacityRequirementPerMinute(): Result<Double>
    suspend fun getBlowerCapacityRequirementPerHour(): Result<Double>

    // Set Area & Volume Requirement
    suspend fun setAerationChamberAreaWithDiffuserTwelveInch(
        aerationChamberAreaWithDiffuserTwelveInch: Double,
    )

    suspend fun setAerationChamberVolumeWithDiffuserTwelveInch(
        aerationChamberVolumeWithDiffuserTwelveInch: Double,
    )

    // Get Area & Volume Requirement
    suspend fun getAerationChamberAreaWithDiffuserTwelveInch(): Result<Double>
    suspend fun getAerationChamberVolumeWithDiffuserTwelveInch(): Result<Double>

    // Standard System BW and GW Separated
    // Set Standard System Tab Area STP Requirement
    suspend fun setRetentionTimeAnaerob(retentionTimeAnaerob: Double)
    suspend fun setPercentBwTowardsTotalFlowForHotel(percentBwTowardsTotalFlowForHotel: Double)
    suspend fun setPercentBwTowardsTotalFlowForIndustry(percentBwTowardsTotalFlowForIndustry: Double)
    suspend fun setTargetBodReductionFromAnaerob(targetBodReductionFromAnaerob: Double)
    suspend fun setRetentionTimeAeration(retentionTimeAeration: Double)
    suspend fun setTargetBodReductionFromAeration(targetBodReductionFromAeration: Double)
    suspend fun setTargetCodReductionFromAeration(targetCodReductionFromAeration: Double)
    suspend fun setTargetNhThreeReductionFromAeration(targetNhThreeReductionFromAeration: Double)
    suspend fun setRetentionTimeGreaseTrap(retentionTimeGreaseTrap: Double)
    suspend fun setTotalChamberGreaseTrap(totalChamberGreaseTrap: Double)
    suspend fun setTargetFogRemovalFromGreaseTrap(targetFogRemovalFromGreaseTrap: Double)
    suspend fun setRetentionTimeSedimentation(retentionTimeSedimentation: Double)
    suspend fun setTargetTssRemovalFromGreaseTrap(targetTssRemovalFromGreaseTrap: Double)
    suspend fun setRetentionTimeChlorination(retentionTimeChlorination: Double)
    suspend fun setTargetEcoliRemovalFromChlorination(targetEcoliRemovalFromChlorination: Double)

    // Get Standard System Tab Area STP Requirement
    suspend fun getRetentionTimeAnaerob(): Result<Double>
    suspend fun getPercentBwTowardsTotalFlowForHotel(): Result<Double>
    suspend fun getPercentBwTowardsTotalFlowForIndustry(): Result<Double>
    suspend fun getTargetBodReductionFromAnaerob(): Result<Double>
    suspend fun getRetentionTimeAeration(): Result<Double>
    suspend fun getTargetBodReductionFromAeration(): Result<Double>
    suspend fun getTargetCodReductionFromAeration(): Result<Double>
    suspend fun getTargetNhThreeReductionFromAeration(): Result<Double>
    suspend fun getRetentionTimeGreaseTrap(): Result<Double>
    suspend fun getTotalChamberGreaseTrap(): Result<Double>
    suspend fun getTargetFogRemovalFromGreaseTrap(): Result<Double>
    suspend fun getRetentionTimeSedimentation(): Result<Double>
    suspend fun getTargetTssRemovalFromGreaseTrap(): Result<Double>
    suspend fun getRetentionTimeChlorination(): Result<Double>
    suspend fun getTargetEcoliRemovalFromChlorination(): Result<Double>

    // Set Input Parameters for Area STP Requirement
    suspend fun setBodOutletAnaerob(bodOutletAnaerob: Double)
    suspend fun setBodOutletAerob(bodOutletAerob: Double)
    suspend fun setCodOutletAerob(codOutletAerob: Double)
    suspend fun setNhThreeAerob(nhThreeAerob: Double)

    // Get Input Parameters for Area STP Requirement
    suspend fun getBodOutletAnaerob(): Result<Double>
    suspend fun getBodOutletAerob(): Result<Double>
    suspend fun getCodOutletAerob(): Result<Double>
    suspend fun getNhThreeAerob(): Result<Double>

    // Set Anaerob Room Requirement
    suspend fun setVolumeAnaerobRoom(volumeAnaerobRoom: Double)
    suspend fun setLengthAnaerobRoom(lengthAnaerobRoom: Double)
    suspend fun setWidthAnaerobRoom(widthAnaerobRoom: Double)
    suspend fun setDepthAnaerobRoom(depthAnaerobRoom: Double)

    // Get Anaerob Room Requirement
    suspend fun getVolumeAnaerobRoom(): Result<Double>
    suspend fun getLengthAnaerobRoom(): Result<Double>
    suspend fun getWidthAnaerobRoom(): Result<Double>
    suspend fun getDepthAnaerobRoom(): Result<Double>

    // Set Grease Trap Room Requirement
    suspend fun setVolumeGreaseTrapRoom(volumeGreaseTrapRoom: Double)
    suspend fun setLengthGreaseTrapRoom(lengthGreaseTrapRoom: Double)
    suspend fun setWidthGreaseTrapRoom(widthGreaseTrapRoom: Double)
    suspend fun setDepthGreaseTrapRoom(depthGreaseTrapRoom: Double)

    // Get Grease Trap Room Requirement
    suspend fun getVolumeGreaseTrapRoom(): Result<Double>
    suspend fun getLengthGreaseTrapRoom(): Result<Double>
    suspend fun getWidthGreaseTrapRoom(): Result<Double>
    suspend fun getDepthGreaseTrapRoom(): Result<Double>

    // Set Aerob Room Requirement
    suspend fun setVolumeAerobRoom(volumeAerobRoom: Double)
    suspend fun setLengthAerobRoom(lengthAerobRoom: Double)
    suspend fun setWidthAerobRoom(widthAerobRoom: Double)
    suspend fun setDepthAerobRoom(depthAerobRoom: Double)

    // Get Aerob Room Requirement
    suspend fun getVolumeAerobRoom(): Result<Double>
    suspend fun getLengthAerobRoom(): Result<Double>
    suspend fun getWidthAerobRoom(): Result<Double>
    suspend fun getDepthAerobRoom(): Result<Double>

    // Set Sedimentation Room Requirement
    suspend fun setVolumeSedimentationRoom(volumeSedimentationRoom: Double)
    suspend fun setLengthSedimentationRoom(lengthSedimentationRoom: Double)
    suspend fun setWidthSedimentationRoom(widthSedimentationRoom: Double)
    suspend fun setDepthSedimentationRoom(depthSedimentationRoom: Double)

    // Get Sedimentation Room Requirement
    suspend fun getVolumeSedimentationRoom(): Result<Double>
    suspend fun getLengthSedimentationRoom(): Result<Double>
    suspend fun getWidthSedimentationRoom(): Result<Double>
    suspend fun getDepthSedimentationRoom(): Result<Double>

    // Set Chlorination Room Requirement
    suspend fun setVolumeChlorinationRoom(volumeChlorinationRoom: Double)
    suspend fun setLengthChlorinationRoom(lengthChlorinationRoom: Double)
    suspend fun setWidthChlorinationRoom(widthChlorinationRoom: Double)
    suspend fun setDepthChlorinationRoom(depthChlorinationRoom: Double)

    // Get Chlorination Room Requirement
    suspend fun getVolumeChlorinationRoom(): Result<Double>
    suspend fun getLengthChlorinationRoom(): Result<Double>
    suspend fun getWidthChlorinationRoom(): Result<Double>
    suspend fun getDepthChlorinationRoom(): Result<Double>

    // Set Filtration Tank Room Requirement
    suspend fun setVolumeFiltrationTankRoom(volumeFiltrationTankRoom: Double)
    suspend fun setLengthFiltrationTankRoom(lengthFiltrationTankRoom: Double)
    suspend fun setWidthFiltrationTankRoom(widthFiltrationTankRoom: Double)
    suspend fun setDepthFiltrationTankRoom(depthFiltrationTankRoom: Double)

    // Get Filtration Tank Room Requirement
    suspend fun getVolumeFiltrationTankRoom(): Result<Double>
    suspend fun getLengthFiltrationTankRoom(): Result<Double>
    suspend fun getWidthFiltrationTankRoom(): Result<Double>
    suspend fun getDepthFiltrationTankRoom(): Result<Double>

    // Set Recycle Tank Room Requirement
    suspend fun setVolumeRecycleTankRoom(volumeRecycleTankRoom: Double)
    suspend fun setLengthRecycleTankRoom(lengthRecycleTankRoom: Double)
    suspend fun setWidthRecycleTankRoom(widthRecycleTankRoom: Double)
    suspend fun setDepthRecycleTankRoom(depthRecycleTankRoom: Double)

    // Get Recycle Tank Room Requirement
    suspend fun getVolumeRecycleTankRoom(): Result<Double>
    suspend fun getLengthRecycleTankRoom(): Result<Double>
    suspend fun getWidthRecycleTankRoom(): Result<Double>
    suspend fun getDepthRecycleTankRoom(): Result<Double>
}