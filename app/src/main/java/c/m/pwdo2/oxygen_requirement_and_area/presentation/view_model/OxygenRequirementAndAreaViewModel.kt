package c.m.pwdo2.oxygen_requirement_and_area.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.login.domain.use_case.CheckLoginStatusUseCase
import c.m.pwdo2.login.domain.use_case.UserLogoutUseCase
import c.m.pwdo2.oxygen_requirement_and_area.domain.use_case.*
import c.m.pwdo2.oxygen_requirement_and_area.presentation.event.InputParametersEvent
import c.m.pwdo2.oxygen_requirement_and_area.presentation.state.OxygenRequirementAndAreaUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class OxygenRequirementAndAreaViewModel @Inject constructor(
    private val userLogoutUseCase: UserLogoutUseCase,
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
    private val getStandardValueUseCase: GetStandardValueUseCase,
    private val setInputParametersUseCase: SetInputParametersUseCase,
    private val getInputParametersUseCase: GetInputParametersUseCase,
    private val calculateDiffuserDepthEfficiencyUseCase: CalculateDiffuserDepthEfficiencyUseCase,
    private val getCalculateDiffuserDepthEfficiencyUseCase: GetCalculateDiffuserDepthEfficiencyUseCase,
    private val calculateTotalInfluentLoadingPerHourUseCase: CalculateTotalInfluentLoadingPerHourUseCase,
    private val getCalculateTotalInfluentLoadingPerHourUseCase: GetCalculateTotalInfluentLoadingPerHourUseCase,
    private val calculateAirRequirementUseCase: CalculateAirRequirementUseCase,
    private val getCalculateAirRequirementUseCase: GetCalculateAirRequirementUseCase,
    private val calculateDiffuserRequirementUseCase: CalculateDiffuserRequirementUseCase,
    private val getCalculateDiffuserRequirementUseCase: GetCalculateDiffuserRequirementUseCase,
    private val calculateBlowerCapacityRequirementUseCase: CalculateBlowerCapacityRequirementUseCase,
    private val getCalculateBlowerCapacityRequirementUseCase: GetCalculateBlowerCapacityRequirementUseCase,
    private val calculateAreaVolumeRequirementUseCase: CalculateAreaVolumeRequirementUseCase,
    private val getCalculateAreaVolumeRequirementUseCase: GetCalculateAreaVolumeRequirementUseCase,
    private val setParametersAreaStpUseCase: SetParametersAreaStpUseCase,
) : ViewModel() {
    private val _oxygenRequirementAndAreaUIState =
        MutableStateFlow(OxygenRequirementAndAreaUIState())
    val oxygenRequirementAndAreaUIState: StateFlow<OxygenRequirementAndAreaUIState> =
        _oxygenRequirementAndAreaUIState.asStateFlow()

    init {
        autoLogoutByCountDown(3600)
        checkAuthentication()
        getStandardValue()
    }

    private fun autoLogoutByCountDown(start: Int) {
        viewModelScope.launch {
            (start - 1 downTo 0).asFlow().onEach { delay(1000L) }.conflate()
                .collect { remainingSecond ->
                    val hours = remainingSecond.div(3600)
                    val minutes = (remainingSecond.mod(3600)).div(60)
                    val seconds = (remainingSecond.mod(3600)).mod(60)
                    val formatTime = String.format(
                        "%02d:%02d:%02d",
                        hours,
                        minutes,
                        seconds,
                    )

                    Timber.w("count down $formatTime")

                    if (formatTime == "00:00:00") {
                        doLogout()
                    }
                }
        }
    }

    fun doLogout() {
        viewModelScope.launch {
            userLogoutUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = false, isLoading = true)
                        }
                    }
                    is Resource.Success -> {
                        checkAuthentication()
                    }
                }
            }
        }
    }

    fun checkAuthentication() {
        viewModelScope.launch {
            checkLoginStatusUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = false, isLoading = true)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isError = false,
                                isLoading = false,
                                isLogin = result.data?.isLogin ?: false
                            )
                        }
                    }
                }
            }
        }
    }

    fun isExpandedStatus() {
        _oxygenRequirementAndAreaUIState.update {
            it.copy(isExpandedInputParametersCard = !_oxygenRequirementAndAreaUIState.value.isExpandedInputParametersCard)
        }
    }

    fun onInputFieldEvent(event: InputParametersEvent) {
        when (event) {
            is InputParametersEvent.BodInletFieldChange -> {
                _oxygenRequirementAndAreaUIState.update {
                    it.copy(inputBodInlet = event.bodInlet.replace(",", "."))
                }
            }
            is InputParametersEvent.BodOutletFieldChange -> {
                _oxygenRequirementAndAreaUIState.update {
                    it.copy(inputBodOutlet = event.bodOutlet.replace(",", "."))
                }
            }
            is InputParametersEvent.CodInletFieldChange -> {
                _oxygenRequirementAndAreaUIState.update {
                    it.copy(inputCodInlet = event.codInlet.replace(",", "."))
                }
            }
            is InputParametersEvent.CodOutletFieldChange -> {
                _oxygenRequirementAndAreaUIState.update {
                    it.copy(inputCodOutlet = event.codOutlet.replace(",", "."))
                }
            }
            is InputParametersEvent.FlowRateFieldChange -> {
                _oxygenRequirementAndAreaUIState.update {
                    it.copy(inputFlowRate = event.flowRate.replace(",", "."))
                }
            }
            is InputParametersEvent.NhThreeInletFieldChange -> {
                _oxygenRequirementAndAreaUIState.update {
                    it.copy(inputNhThreeInlet = event.nhThreeInlet.replace(",", "."))
                }
            }
            is InputParametersEvent.NhThreeOutletFieldChange -> {
                _oxygenRequirementAndAreaUIState.update {
                    it.copy(inputNhThreeOutlet = event.nhThreeOutlet.replace(",", "."))
                }
            }
            is InputParametersEvent.PeakHourAssumption -> {
                _oxygenRequirementAndAreaUIState.update {
                    it.copy(inputPeakHourAssumption = event.peakHourAssumption.replace(",", "."))
                }
            }
            is InputParametersEvent.WaterDepthAboveDiffuser -> {
                _oxygenRequirementAndAreaUIState.update {
                    it.copy(
                        inputWaterDepthAboveDiffuser = event.waterDepthAboveDiffuser.replace(
                            ",", "."
                        )
                    )
                }
            }
        }
    }

    fun getStandardValue() {
        // update is refresh status
        _oxygenRequirementAndAreaUIState.update {
            it.copy(isLoading = true, isRefresh = true, isExpandedAllCard = false)
        }

        // load data ...
        viewModelScope.launch {
            getStandardValueUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = true, isRefresh = true)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isLoading = false,
                                isError = false,
                                isSuccess = true,
                                // Standard Value
                                oneKgBodStandard = result.data?.oneKgBodStandard ?: 0.0,
                                oneKgCodStandard = result.data?.oneKgCodStandard ?: 0.0,
                                oneKgNhThreeStandard = result.data?.oneKgNhThreeStandard ?: 0.0,
                                percentOxygenInAirStandard = result.data?.percentOxygenInAirStandard
                                    ?: 0.0,
                                densityOfOxygenStandard = result.data?.densityOfOxygenStandard
                                    ?: 0.0,
                                diffuserCapacityTwelveInchStandard = result.data?.diffuserCapacityTwelveInchStandard
                                    ?: 0.0,
                                diffuserCapacityNineInchStandard = result.data?.diffuserCapacityNineInchStandard
                                    ?: 0.0,
                                percentOxygenTransferEfficiencyStandard = result.data?.percentOxygenTransferEfficiencyStandard
                                    ?: 0.0,
                                effectiveSurfaceAreaDiffTwelveInchStandard = result.data?.effectiveSurfaceAreaDiffTwelveInchStandard
                                    ?: 0.0,
                                effectiveSurfaceAreaDiffNineInchStandard = result.data?.effectiveSurfaceAreaDiffNineInchStandard
                                    ?: 0.0,
                                minimumMixingAreaStandard = result.data?.minimumMixingAreaStandard
                                    ?: 0.0,
                                targetBodReductionFromAnaerob = result.data?.targetBodReductionFromAnaerob
                                    ?: 0.0,
                                targetBodReductionFromAeration = result.data?.targetBodReductionFromAeration
                                    ?: 0.0,
                                targetCodReductionFromAeration = result.data?.targetCodReductionFromAeration
                                    ?: 0.0,
                                targetNhThreeReductionFromAeration = result.data?.targetNhThreeReductionFromAeration
                                    ?: 0.0,
                            )
                        }
                        readInputParameters()
                    }
                }
            }
        }
    }

    fun clearInputParameters() {
        viewModelScope.launch {
            val inputBodInlet = "0"
            val inputBodOutlet = "0"
            val inputCodInlet = "0"
            val inputCodOutlet = "0"
            val inputNhThreeInlet = "0"
            val inputNhThreeOutlet = "0"
            val inputFlowRate = "0"
            val inputPeakHourAssumption = "0"
            val inputWaterDepthAboveDiffuser = "0"

            setInputParametersUseCase(
                inputBodInlet.replace(Regex("\\.+\\z"), "").toDouble(),
                inputBodOutlet.replace(Regex("\\.+\\z"), "").toDouble(),
                inputCodInlet.replace(Regex("\\.+\\z"), "").toDouble(),
                inputCodOutlet.replace(Regex("\\.+\\z"), "").toDouble(),
                inputNhThreeInlet.replace(Regex("\\.+\\z"), "").toDouble(),
                inputNhThreeOutlet.replace(Regex("\\.+\\z"), "").toDouble(),
                inputFlowRate.replace(Regex("\\.+\\z"), "").toDouble(),
                inputPeakHourAssumption.replace(Regex("\\.+\\z"), "").toDouble(),
                inputWaterDepthAboveDiffuser.replace(Regex("\\.+\\z"), "").toDouble(),
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isLoadingInputParametersCard = true,
                                isRefresh = true,
                                isLoading = true
                            )
                        }
                    }
                    is Resource.Success -> {
                        readInputParameters()
                    }
                }
            }
        }
    }

    fun saveInputParameters() {
        viewModelScope.launch {
            _oxygenRequirementAndAreaUIState.update {
                it.copy(isLoading = true, isRefresh = true, isExpandedAllCard = false)
            }
            delay(500)

            val inputBodInlet = _oxygenRequirementAndAreaUIState.value.inputBodInlet.ifEmpty { "0" }
            val inputBodOutlet =
                _oxygenRequirementAndAreaUIState.value.inputBodOutlet.ifEmpty { "0" }
            val inputCodInlet = _oxygenRequirementAndAreaUIState.value.inputCodInlet.ifEmpty { "0" }
            val inputCodOutlet =
                _oxygenRequirementAndAreaUIState.value.inputCodOutlet.ifEmpty { "0" }
            val inputNhThreeInlet =
                _oxygenRequirementAndAreaUIState.value.inputNhThreeInlet.ifEmpty { "0" }
            val inputNhThreeOutlet =
                _oxygenRequirementAndAreaUIState.value.inputNhThreeOutlet.ifEmpty { "0" }
            val inputFlowRate = _oxygenRequirementAndAreaUIState.value.inputFlowRate.ifEmpty { "0" }
            val inputPeakHourAssumption =
                _oxygenRequirementAndAreaUIState.value.inputPeakHourAssumption.ifEmpty { "0" }
            val inputWaterDepthAboveDiffuser =
                _oxygenRequirementAndAreaUIState.value.inputWaterDepthAboveDiffuser.ifEmpty { "0" }

            setInputParametersUseCase(
                inputBodInlet.replace(Regex("\\.+\\z"), "").toDouble(),
                inputBodOutlet.replace(Regex("\\.+\\z"), "").toDouble(),
                inputCodInlet.replace(Regex("\\.+\\z"), "").toDouble(),
                inputCodOutlet.replace(Regex("\\.+\\z"), "").toDouble(),
                inputNhThreeInlet.replace(Regex("\\.+\\z"), "").toDouble(),
                inputNhThreeOutlet.replace(Regex("\\.+\\z"), "").toDouble(),
                inputFlowRate.replace(Regex("\\.+\\z"), "").toDouble(),
                inputPeakHourAssumption.replace(Regex("\\.+\\z"), "").toDouble(),
                inputWaterDepthAboveDiffuser.replace(Regex("\\.+\\z"), "").toDouble(),
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = true, isRefresh = true)
                        }
                    }
                    is Resource.Success -> {
                        readInputParameters()
                    }
                }
            }
        }
    }

    private fun readInputParameters() {
        viewModelScope.launch {
            getInputParametersUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoadingInputParametersCard = true)
                        }
                    }
                    is Resource.Success -> {
                        setParametersAreaStpUseCase(
                            inputBodInlet = result.data?.inputBodInlet ?: 0.0,
                            inputCodInlet = result.data?.inputCodInlet ?: 0.0,
                            inputNhThreeInlet = result.data?.inputNhThreeInlet ?: 0.0,
                            targetBodReductionFromAeration = _oxygenRequirementAndAreaUIState.value.targetBodReductionFromAeration,
                            targetBodReductionFromAnaerob = _oxygenRequirementAndAreaUIState.value.targetBodReductionFromAnaerob,
                            targetCodReductionFromAeration = _oxygenRequirementAndAreaUIState.value.targetCodReductionFromAeration,
                            targetNhThreeReductionFromAeration = _oxygenRequirementAndAreaUIState.value.targetNhThreeReductionFromAeration,
                        ).collect()

                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isLoading = false,
                                isError = false,
                                isSuccess = true,
                                isRefresh = false,
                                isLoadingInputParametersCard = false,
                                inputBodInlet = result.data?.inputBodInlet.toString(),
                                inputBodOutlet = result.data?.inputBodOutlet.toString(),
                                inputCodInlet = result.data?.inputCodInlet.toString(),
                                inputCodOutlet = result.data?.inputCodOutlet.toString(),
                                inputNhThreeInlet = result.data?.inputNhThreeInlet.toString(),
                                inputNhThreeOutlet = result.data?.inputNhThreeOutlet.toString(),
                                inputFlowRate = result.data?.inputFlowRate.toString(),
                                inputPeakHourAssumption = result.data?.inputPeakHourAssumption.toString(),
                                inputWaterDepthAboveDiffuser = result.data?.inputWaterDepthAboveDiffuser.toString(),
                            )
                        }

                        doCalculatedDiffuserDepthEfficiency()

                        delay(500)
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isExpandedAllCard = true, isRefresh = false)
                        }
                    }
                }
            }
        }
    }

    private fun doCalculatedDiffuserDepthEfficiency() {
        viewModelScope.launch {
            calculateDiffuserDepthEfficiencyUseCase(
                _oxygenRequirementAndAreaUIState.value.inputWaterDepthAboveDiffuser.toDouble(),
                _oxygenRequirementAndAreaUIState.value.percentOxygenTransferEfficiencyStandard,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                                isRefresh = false,
                            )
                        }

                        resultCalculatedDiffuserDepthEfficiency()
                    }
                }
            }
        }
    }

    private fun resultCalculatedDiffuserDepthEfficiency() {
        viewModelScope.launch {
            getCalculateDiffuserDepthEfficiencyUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                                isRefresh = false,
                                diffuserDepthEfficiency = result.data?.diffuserDepthEfficiency?.times(
                                    100
                                )?.toBigDecimal()?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble()
                                    ?: 0.0,
                            )
                        }


                        doCalculateTotalInfluentLoadingPerHour()
                    }
                }
            }
        }
    }

    private fun doCalculateTotalInfluentLoadingPerHour() {
        viewModelScope.launch {
            calculateTotalInfluentLoadingPerHourUseCase(
                _oxygenRequirementAndAreaUIState.value.inputBodInlet.toDouble(),
                _oxygenRequirementAndAreaUIState.value.inputBodOutlet.toDouble(),
                _oxygenRequirementAndAreaUIState.value.inputCodInlet.toDouble(),
                _oxygenRequirementAndAreaUIState.value.inputCodOutlet.toDouble(),
                _oxygenRequirementAndAreaUIState.value.inputNhThreeInlet.toDouble(),
                _oxygenRequirementAndAreaUIState.value.inputNhThreeOutlet.toDouble(),
                _oxygenRequirementAndAreaUIState.value.inputFlowRate.toDouble(),
                _oxygenRequirementAndAreaUIState.value.inputPeakHourAssumption.toDouble(),
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                                isRefresh = false,
                            )
                        }


                        resultCalculateTotalInfluentLoadingPerHour()
                    }
                }
            }
        }
    }

    private fun resultCalculateTotalInfluentLoadingPerHour() {
        viewModelScope.launch {
            getCalculateTotalInfluentLoadingPerHourUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                                isRefresh = false,

                                totalBodInlet = result.data?.totalBodInlet?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                totalCodInlet = result.data?.totalCodInlet?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                totalNhThreeInlet = result.data?.totalNhThreeInlet?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                            )
                        }


                        doCalculateAirRequirement()
                    }
                }
            }
        }
    }

    private fun doCalculateAirRequirement() {
        viewModelScope.launch {
            calculateAirRequirementUseCase(
                oneKgBodStandard = _oxygenRequirementAndAreaUIState.value.oneKgBodStandard,
                densityOfOxygenStandard = _oxygenRequirementAndAreaUIState.value.densityOfOxygenStandard,
                percentOxygenInAirStandard = _oxygenRequirementAndAreaUIState.value.percentOxygenInAirStandard,
                oneKgCodStandard = _oxygenRequirementAndAreaUIState.value.oneKgCodStandard,
                oneKgNhThreeStandard = _oxygenRequirementAndAreaUIState.value.oneKgNhThreeStandard,
                totalBodInlet = _oxygenRequirementAndAreaUIState.value.totalBodInlet,
                totalCodInlet = _oxygenRequirementAndAreaUIState.value.totalCodInlet,
                totalNhThreeInlet = _oxygenRequirementAndAreaUIState.value.totalNhThreeInlet,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                                isRefresh = false,
                            )
                        }


                        resultCalculateAirRequirement()
                    }
                }
            }
        }
    }

    private fun resultCalculateAirRequirement() {
        viewModelScope.launch {
            getCalculateAirRequirementUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                                isRefresh = false,
                                forBodPerMinute = result.data?.forBodPerMinute?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                forBodPerHour = result.data?.forBodPerHour?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                forCodPerMinute = result.data?.forCodPerMinute?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                forCodPerHour = result.data?.forCodPerHour?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                forNhThreePerMinute = result.data?.forNhThreePerMinute?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                forNhThreePerHour = result.data?.forNhThreePerHour?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                totalAirRequirementPerMinute = result.data?.totalAirRequirementPerMinute?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                totalAirRequirementPerHour = result.data?.totalAirRequirementPerHour?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                            )
                        }


                        doCalculateDiffuserRequirement()
                    }
                }
            }
        }
    }

    private fun doCalculateDiffuserRequirement() {
        viewModelScope.launch {
            calculateDiffuserRequirementUseCase(
                totalAirRequirementPerHour = _oxygenRequirementAndAreaUIState.value.totalAirRequirementPerHour,
                diffuserDepthEfficiency = _oxygenRequirementAndAreaUIState.value.diffuserDepthEfficiency,
                diffuserCapacityTwelveInchStandard = _oxygenRequirementAndAreaUIState.value.diffuserCapacityTwelveInchStandard,
                diffuserCapacityNineInchStandard = _oxygenRequirementAndAreaUIState.value.diffuserCapacityNineInchStandard,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                                isRefresh = false,
                            )
                        }


                        resultCalculateDiffuserRequirement()
                    }
                }
            }
        }
    }

    private fun resultCalculateDiffuserRequirement() {
        viewModelScope.launch {
            getCalculateDiffuserRequirementUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                                isRefresh = false,
                                diffuserTwelveUnit = result.data?.diffuserTwelveUnit?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.UP)?.toDouble() ?: 0.0,
                                diffuserTwelveUnitRoundUp = result.data?.diffuserTwelveUnitRoundUp
                                    ?: 0.0,
                                diffuserNineUnit = result.data?.diffuserNineUnit?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.UP)?.toDouble() ?: 0.0,
                                diffuserNineUnitRoundUp = result.data?.diffuserNineUnitRoundUp
                                    ?: 0.0,
                            )
                        }


                        doCalculateBlowerCapacityRequirement()
                    }
                }
            }
        }
    }

    private fun doCalculateBlowerCapacityRequirement() {
        viewModelScope.launch {
            calculateBlowerCapacityRequirementUseCase(
                diffuserCapacityTwelveInchStandard = _oxygenRequirementAndAreaUIState.value.diffuserCapacityTwelveInchStandard,
                diffuserTwelveUnitRoundUp = _oxygenRequirementAndAreaUIState.value.diffuserTwelveUnitRoundUp,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                                isRefresh = false,
                            )
                        }


                        resultCalculateBlowerCapacityRequirement()
                    }
                }
            }
        }
    }

    private fun resultCalculateBlowerCapacityRequirement() {
        viewModelScope.launch {
            getCalculateBlowerCapacityRequirementUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                                isRefresh = false,
                                blowerCapacityRequirementPerMinute = result.data?.blowerCapacityRequirementPerMinute?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                blowerCapacityRequirementPerHour = result.data?.blowerCapacityRequirementPerHour?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0
                            )
                        }

                        doCalculateAreaVolumeRequirement()
                    }
                }
            }
        }
    }

    private fun doCalculateAreaVolumeRequirement() {
        viewModelScope.launch {
            calculateAreaVolumeRequirementUseCase(
                diffuserTwelveUnitRoundUp = _oxygenRequirementAndAreaUIState.value.diffuserTwelveUnitRoundUp,
                inputWaterDepthAboveDiffuser = _oxygenRequirementAndAreaUIState.value.inputWaterDepthAboveDiffuser.toDouble(),
                minimumMixingAreaStandard = _oxygenRequirementAndAreaUIState.value.minimumMixingAreaStandard,
                effectiveSurfaceAreaDiffTwelveInchStandard = _oxygenRequirementAndAreaUIState.value.effectiveSurfaceAreaDiffTwelveInchStandard,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                                isRefresh = false,
                            )
                        }

                        resultCalculateAreaVolumeRequirement()
                    }
                }
            }
        }
    }

    private fun resultCalculateAreaVolumeRequirement() {
        viewModelScope.launch {
            getCalculateAreaVolumeRequirementUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isError = true, isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        _oxygenRequirementAndAreaUIState.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                isError = false,
                                isRefresh = false,
                                aerationChamberAreaWithDiffuserTwelveInch = result.data?.aerationChamberAreaWithDiffuserTwelveInch?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                aerationChamberVolumeWithDiffuserTwelveInch = result.data?.aerationChamberVolumeWithDiffuserTwelveInch?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                            )
                        }
                    }
                }
            }
        }
    }
}