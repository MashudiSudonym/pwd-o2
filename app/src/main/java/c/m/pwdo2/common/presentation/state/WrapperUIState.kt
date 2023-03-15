package c.m.pwdo2.common.presentation.state

data class WrapperUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val isLogin: Boolean = false,
    val isCheckLoginError: Boolean = false,
    // Standard Value
    val oneKgBodStandard: Double = 0.0,
    val oneKgCodStandard: Double = 0.0,
    val oneKgNhThreeStandard: Double = 0.0,
    val percentOxygenInAirStandard: Double = 0.0,
    val densityOfOxygenStandard: Double = 0.0,
    val diffuserCapacityTwelveInchStandard: Double = 0.0,
    val diffuserCapacityNineInchStandard: Double = 0.0,
    val percentOxygenTransferEfficiencyStandard: Double = 0.0,
    val effectiveSurfaceAreaDiffTwelveInchStandard: Double = 0.0,
    val effectiveSurfaceAreaDiffNineInchStandard: Double = 0.0,
    val minimumMixingAreaStandard: Double = 0.0,
)