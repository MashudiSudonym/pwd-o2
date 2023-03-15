package c.m.pwdo2.area_stp_requirement.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import c.m.pwdo2.area_stp_requirement.domain.use_case.*
import c.m.pwdo2.area_stp_requirement.presentation.event.*
import c.m.pwdo2.area_stp_requirement.presentation.state.AreaStpRequirementUIState
import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.login.domain.use_case.CheckLoginStatusUseCase
import c.m.pwdo2.login.domain.use_case.UserLogoutUseCase
import c.m.pwdo2.oxygen_requirement_and_area.domain.use_case.GetCalculateAreaVolumeRequirementUseCase
import c.m.pwdo2.oxygen_requirement_and_area.domain.use_case.GetInputParametersUseCase
import c.m.pwdo2.oxygen_requirement_and_area.domain.use_case.GetStandardValueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class AreaStpRequirementViewModel @Inject constructor(
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
    private val getStandardValueUseCase: GetStandardValueUseCase,
    private val getInputParametersUseCase: GetInputParametersUseCase,
    private val calculateAnaerobRoomRequirementsUseCase: CalculateAnaerobRoomRequirementsUseCase,
    private val getCalculateAnaerobRoomRequirementsUseCase: GetCalculateAnaerobRoomRequirementsUseCase,
    private val calculateGreaseTrapRoomRequirementsUseCase: CalculateGreaseTrapRoomRequirementsUseCase,
    private val getCalculateGreaseTrapRoomRequirementsUseCase: GetCalculateGreaseTrapRoomRequirementsUseCase,
    private val getCalculateAreaVolumeRequirementUseCase: GetCalculateAreaVolumeRequirementUseCase,
    private val calculateAerobRoomRequirementsUseCase: CalculateAerobRoomRequirementsUseCase,
    private val getCalculateAerobRoomRequirementsUseCase: GetCalculateAerobRoomRequirementsUseCase,
    private val calculateSedimentationRoomRequirementsUseCase: CalculateSedimentationRoomRequirementsUseCase,
    private val getCalculateSedimentationRoomRequirementsUseCase: GetCalculateSedimentationRoomRequirementsUseCase,
    private val calculateChlorinationRoomRequirementsUseCase: CalculateChlorinationRoomRequirementsUseCase,
    private val getCalculateChlorinationRoomRequirementsUseCase: GetCalculateChlorinationRoomRequirementsUseCase,
    private val calculateFiltrationTankRoomRequirementsUseCase: CalculateFiltrationTankRoomRequirementsUseCase,
    private val getCalculateFiltrationTankRoomRequirementsUseCase: GetCalculateFiltrationTankRoomRequirementsUseCase,
    private val calculateRecycleTankRoomRequirementsUseCase: CalculateRecycleTankRoomRequirementsUseCase,
    private val getCalculateRecycleTankRoomRequirementsUseCase: GetCalculateRecycleTankRoomRequirementsUseCase,
) : ViewModel() {
    private val _areaStpRequirementUIState = MutableStateFlow(AreaStpRequirementUIState())
    val areaStpRequirementUIState: StateFlow<AreaStpRequirementUIState> =
        _areaStpRequirementUIState.asStateFlow()

    init {
        // check authentication
        checkAuthentication()

        // get standard value
        getStandardValue()

        // get input params from oxygen area
        getInputParametersFromOxygenArea()

        // get area volume requirement
        getAreaVolumeRequirement()

        // first loading state
        isRefreshingPage()

        // expanded warning card
        viewModelScope.launch {
            delay(500)
            _areaStpRequirementUIState.update {
                it.copy(isExpanded = true, isSuccess = true)
            }
        }
    }

    fun checkAuthentication() {
        viewModelScope.launch {
            checkLoginStatusUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = false, isLoading = true)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
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

    private fun getAreaVolumeRequirement() {
        viewModelScope.launch {
            getCalculateAreaVolumeRequirementUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        isRefreshingPage()
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                aerationChamberVolumeWithDiffuserTwelveInch = result.data?.aerationChamberVolumeWithDiffuserTwelveInch?.toBigDecimal()
                                    ?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getInputParametersFromOxygenArea() {
        viewModelScope.launch {
            getInputParametersUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        isRefreshingPage()
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                inputBodInlet = result.data?.inputBodInlet ?: 0.0,
                                inputCodInlet = result.data?.inputCodInlet ?: 0.0,
                                inputNhThreeInlet = result.data?.inputNhThreeInlet ?: 0.0,
                                inputFlowRate = result.data?.inputFlowRate ?: 0.0,
                                inputPeakHourAssumption = result.data?.inputPeakHourAssumption
                                    ?: 0.0,
                                inputWaterDepthAboveDiffuser = result.data?.inputWaterDepthAboveDiffuser
                                    ?: 0.0,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getStandardValue() {
        viewModelScope.launch {
            getStandardValueUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        isRefreshingPage()
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                retentionTimeAnaerob = result.data?.retentionTimeAnaerob ?: 0.0,
                                percentBwTowardsTotalFlowForHotel = result.data?.percentBwTowardsTotalFlowForHotel
                                    ?: 0.0,
                                percentBwTowardsTotalFlowForIndustry = result.data?.percentBwTowardsTotalFlowForIndustry
                                    ?: 0.0,
                                targetBodReductionFromAnaerob = result.data?.targetBodReductionFromAnaerob
                                    ?: 0.0,
                                retentionTimeAeration = result.data?.retentionTimeAeration ?: 0.0,
                                targetBodReductionFromAeration = result.data?.targetBodReductionFromAeration
                                    ?: 0.0,
                                targetCodReductionFromAeration = result.data?.targetCodReductionFromAeration
                                    ?: 0.0,
                                targetNhThreeReductionFromAeration = result.data?.targetNhThreeReductionFromAeration
                                    ?: 0.0,
                                retentionTimeGreaseTrap = result.data?.retentionTimeGreaseTrap
                                    ?: 0.0,
                                totalChamberGreaseTrap = result.data?.totalChamberGreaseTrap ?: 0.0,
                                targetFogRemovalFromGreaseTrap = result.data?.targetFogRemovalFromGreaseTrap
                                    ?: 0.0,
                                retentionTimeSedimentation = result.data?.retentionTimeSedimentation
                                    ?: 0.0,
                                targetTssRemovalFromGreaseTrap = result.data?.targetTssRemovalFromGreaseTrap
                                    ?: 0.0,
                                retentionTimeChlorination = result.data?.retentionTimeChlorination
                                    ?: 0.0,
                                targetEcoliRemovalFromChlorination = result.data?.targetEcoliRemovalFromChlorination
                                    ?: 0.0,
                            )
                        }
                    }
                }
            }
        }
    }

    fun isExpandedCardA() {
        _areaStpRequirementUIState.update {
            it.copy(isExpandedCardA = !_areaStpRequirementUIState.value.isExpandedCardA)
        }
    }

    fun isExpandedCardB() {
        _areaStpRequirementUIState.update {
            it.copy(isExpandedCardB = !_areaStpRequirementUIState.value.isExpandedCardB)
        }
    }

    fun isExpandedCardC() {
        _areaStpRequirementUIState.update {
            it.copy(isExpandedCardC = !_areaStpRequirementUIState.value.isExpandedCardC)
        }
    }

    fun isExpandedCardD() {
        _areaStpRequirementUIState.update {
            it.copy(isExpandedCardD = !_areaStpRequirementUIState.value.isExpandedCardD)
        }
    }

    fun isExpandedCardE() {
        _areaStpRequirementUIState.update {
            it.copy(isExpandedCardE = !_areaStpRequirementUIState.value.isExpandedCardE)
        }
    }

    fun isExpandedCardF() {
        _areaStpRequirementUIState.update {
            it.copy(isExpandedCardF = !_areaStpRequirementUIState.value.isExpandedCardF)
        }
    }

    fun isExpandedCardG() {
        _areaStpRequirementUIState.update {
            it.copy(isExpandedCardG = !_areaStpRequirementUIState.value.isExpandedCardG)
        }
    }

    fun isRefreshingPage() {
        viewModelScope.launch {
            _areaStpRequirementUIState.update {
                it.copy(isRefresh = true)
            }

            // load all get data card
            getCalculateAnaerobRoomRequirement()
            calculateAnaerobRoomRequirement()
            delay(1000)
            getCalculateGreaseTrapRoomRequirement()
            calculateGreaseTrapRoomRequirement()
            getCalculateAerobRoomRequirement()
            calculateAerobRoomRequirement()
            getCalculateSedimentationRoomRequirement()
            calculateSedimentationRoomRequirement()
            getCalculateChlorinationRoomRequirement()
            calculateChlorinationRoomRequirement()
            getCalculateFiltrationTankRoomRequirement()
            calculateFiltrationTankRoomRequirement()
            getCalculateRecycleTankRoomRequirement()
            calculateRecycleTankRoomRequirement()

            delay(500)

            _areaStpRequirementUIState.update {
                it.copy(isRefresh = false)
            }
        }
    }

    fun onAnaerobRoomInputFieldEvent(event: InputAnaerobRoomEvent) {
        when (event) {
            is InputAnaerobRoomEvent.LengthAnaerobRoomFieldChange -> {
                _areaStpRequirementUIState.update {
                    it.copy(lengthAnaerobRoom = event.lengthAnaerobRoom.replace(",", "."))
                }
            }
        }
    }

    fun clearAnaerobRoomRequirement() {
        viewModelScope.launch {
            calculateAnaerobRoomRequirementsUseCase(
                inputWaterDepthAboveDiffuser = _areaStpRequirementUIState.value.inputWaterDepthAboveDiffuser,
                inputFlowRate = _areaStpRequirementUIState.value.inputFlowRate,
                retentionTimeAnaerob = _areaStpRequirementUIState.value.retentionTimeAnaerob,
                percentBwTowardsTotalFlowForHotel = _areaStpRequirementUIState.value.percentBwTowardsTotalFlowForHotel,
                lengthAnaerobRoomRequirements = 0.0,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardA = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardA = false,
                            )
                        }

                        getCalculateAnaerobRoomRequirement()
                    }
                }
            }
        }
    }

    fun calculateAnaerobRoomRequirement() {
        viewModelScope.launch {
            // card to loading status
            _areaStpRequirementUIState.update {
                it.copy(isLoadingCardA = true)
            }

            // do calculate process
            val lengthAnaerobRoom =
                _areaStpRequirementUIState.value.lengthAnaerobRoom.ifEmpty { "0" }

            println("input ss $lengthAnaerobRoom")

            calculateAnaerobRoomRequirementsUseCase(
                inputWaterDepthAboveDiffuser = _areaStpRequirementUIState.value.inputWaterDepthAboveDiffuser,
                inputFlowRate = _areaStpRequirementUIState.value.inputFlowRate,
                lengthAnaerobRoomRequirements = lengthAnaerobRoom.replace(Regex("\\.+\\z"), "")
                    .toDouble(),
                retentionTimeAnaerob = _areaStpRequirementUIState.value.retentionTimeAnaerob,
                percentBwTowardsTotalFlowForHotel = _areaStpRequirementUIState.value.percentBwTowardsTotalFlowForHotel,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardA = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardA = false,
                            )
                        }

                        getCalculateAnaerobRoomRequirement()
                    }
                }
            }
        }
    }

    private fun getCalculateAnaerobRoomRequirement() {
        viewModelScope.launch {
            getCalculateAnaerobRoomRequirementsUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardA = true)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardA = false,
                                volumeAnaerobRoom = result.data?.volumeAnaerobRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                lengthAnaerobRoom = result.data?.lengthAnaerobRoom.toString(),
                                widthAnaerobRoom = result.data?.widthAnaerobRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                depthAnaerobRoom = result.data?.depthAnaerobRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                            )
                        }
                    }
                }
            }
        }
    }

    fun onGreaseTrapRoomInputFieldEvent(event: InputGreaseTrapRoomEvent) {
        when (event) {
            is InputGreaseTrapRoomEvent.DepthGreaseTrapRoomFieldChange -> {
                _areaStpRequirementUIState.update {
                    it.copy(depthGreaseTrapRoom = event.depthGreaseTrapRoom.replace(",", "."))
                }
            }
            is InputGreaseTrapRoomEvent.LengthGreaseTrapRoomFieldChange -> {
                _areaStpRequirementUIState.update {
                    it.copy(lengthGreaseTrapRoom = event.lengthGreaseTrapRoom.replace(",", "."))
                }
            }
        }
    }

    fun clearGraceTrapRoomRequirement() {
        viewModelScope.launch {
            calculateGreaseTrapRoomRequirementsUseCase(
                retentionTimeGreaseTrap = _areaStpRequirementUIState.value.retentionTimeGreaseTrap,
                lengthGreaseTrapRoom = 0.0,
                inputFlowRate = _areaStpRequirementUIState.value.inputFlowRate,
                depthGreaseTrapRoom = 0.0,
                totalChamberGreaseTrap = _areaStpRequirementUIState.value.totalChamberGreaseTrap,
                inputPeakHourAssumption = _areaStpRequirementUIState.value.inputPeakHourAssumption,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardB = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardB = false,
                            )
                        }

                        getCalculateGreaseTrapRoomRequirement()
                    }
                }
            }
        }
    }

    fun calculateGreaseTrapRoomRequirement() {
        viewModelScope.launch {
            // card to loading status
            _areaStpRequirementUIState.update {
                it.copy(isLoadingCardB = true)
            }

            // do calculate process
            val lengthGreaseTrapRoom =
                _areaStpRequirementUIState.value.lengthGreaseTrapRoom.ifEmpty { "0" }
            val depthGreaseTrapRoom =
                _areaStpRequirementUIState.value.depthGreaseTrapRoom.ifEmpty { "0" }

            calculateGreaseTrapRoomRequirementsUseCase(
                retentionTimeGreaseTrap = _areaStpRequirementUIState.value.retentionTimeGreaseTrap,
                lengthGreaseTrapRoom = lengthGreaseTrapRoom.replace(Regex("\\.+\\z"), "")
                    .toDouble(),
                inputFlowRate = _areaStpRequirementUIState.value.inputFlowRate,
                depthGreaseTrapRoom = depthGreaseTrapRoom.replace(Regex("\\.+\\z"), "")
                    .toDouble(),
                totalChamberGreaseTrap = _areaStpRequirementUIState.value.totalChamberGreaseTrap,
                inputPeakHourAssumption = _areaStpRequirementUIState.value.inputPeakHourAssumption,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardB = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardB = false,
                            )
                        }

                        getCalculateGreaseTrapRoomRequirement()
                    }
                }
            }
        }
    }

    private fun getCalculateGreaseTrapRoomRequirement() {
        viewModelScope.launch {
            getCalculateGreaseTrapRoomRequirementsUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardB = true)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardB = false,
                                volumeGreaseTrapRoom = result.data?.volumeGreaseTrapRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                lengthGreaseTrapRoom = result.data?.lengthGreaseTrapRoom.toString(),
                                widthGreaseTrapRoom = result.data?.widthGreaseTrapRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                depthGreaseTrapRoom = result.data?.depthGreaseTrapRoom?.toString()
                                    ?: "",
                            )
                        }
                    }
                }
            }
        }
    }

    fun onAerobRoomInputFieldEvent(event: InputAerobRoomEvent) {
        when (event) {
            is InputAerobRoomEvent.LengthAerobRoomFieldChange -> {
                _areaStpRequirementUIState.update {
                    it.copy(
                        lengthAerobRoom = event.lengthAerobRoom.replace(",", ".")
                    )
                }
            }
        }
    }

    fun clearAerobRoomRequirement() {
        viewModelScope.launch {
            calculateAerobRoomRequirementsUseCase(
                aerationChamberVolumeWithDiffuserTwelveInch = _areaStpRequirementUIState.value.aerationChamberVolumeWithDiffuserTwelveInch,
                lengthAerobRoom = 0.0,
                depthAnaerobRoom = _areaStpRequirementUIState.value.depthAnaerobRoom,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardC = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardC = false,
                            )
                        }

                        getCalculateAerobRoomRequirement()
                    }
                }
            }
        }
    }

    fun calculateAerobRoomRequirement() {
        viewModelScope.launch {
            // card to loading status
            _areaStpRequirementUIState.update {
                it.copy(isLoadingCardC = true)
            }

            // do calculate process
            val lengthAerobRoom =
                _areaStpRequirementUIState.value.lengthAerobRoom.ifEmpty { "0" }

            calculateAerobRoomRequirementsUseCase(
                aerationChamberVolumeWithDiffuserTwelveInch = _areaStpRequirementUIState.value.aerationChamberVolumeWithDiffuserTwelveInch,
                lengthAerobRoom = lengthAerobRoom.replace(Regex("\\.+\\z"), "")
                    .toDouble(),
                depthAnaerobRoom = _areaStpRequirementUIState.value.depthAnaerobRoom,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardC = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardC = false,
                            )
                        }

                        getCalculateAerobRoomRequirement()
                    }
                }
            }
        }
    }

    private fun getCalculateAerobRoomRequirement() {
        viewModelScope.launch {
            getCalculateAerobRoomRequirementsUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardC = true)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardC = false,
                                volumeAerobRoom = result.data?.volumeAerobRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                lengthAerobRoom = result.data?.lengthAerobRoom.toString(),
                                widthAerobRoom = result.data?.widthAerobRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                depthAerobRoom = result.data?.depthAerobRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                            )
                        }
                    }
                }
            }
        }
    }

    fun onSedimentationRoomInputFieldEvent(event: InputSedimentationRoomEvent) {
        when (event) {
            is InputSedimentationRoomEvent.LengthSedimentationRoomFieldChange -> {
                _areaStpRequirementUIState.update {
                    it.copy(
                        lengthSedimentationRoom = event.lengthSedimentationRoom.replace(",", ".")
                    )
                }
            }
        }
    }

    fun clearSedimentationRoomRequirement() {
        viewModelScope.launch {
            calculateSedimentationRoomRequirementsUseCase(
                lengthSedimentationRoom = 0.0,
                inputPeakHourAssumption = _areaStpRequirementUIState.value.inputPeakHourAssumption,
                inputFlowRate = _areaStpRequirementUIState.value.inputFlowRate,
                depthAnaerob = _areaStpRequirementUIState.value.depthAnaerobRoom,
                retentionTimeSedimentation = _areaStpRequirementUIState.value.retentionTimeSedimentation,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardD = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardD = false,
                            )
                        }

                        getCalculateSedimentationRoomRequirement()
                    }
                }
            }
        }
    }

    fun calculateSedimentationRoomRequirement() {
        viewModelScope.launch {
            // card to loading status
            _areaStpRequirementUIState.update {
                it.copy(isLoadingCardD = true)
            }

            // do calculate process
            val lengthSedimentationRoom =
                _areaStpRequirementUIState.value.lengthSedimentationRoom.ifEmpty { "0" }

            calculateSedimentationRoomRequirementsUseCase(
                lengthSedimentationRoom = lengthSedimentationRoom.replace(Regex("\\.+\\z"), "")
                    .toDouble(),
                inputPeakHourAssumption = _areaStpRequirementUIState.value.inputPeakHourAssumption,
                inputFlowRate = _areaStpRequirementUIState.value.inputFlowRate,
                depthAnaerob = _areaStpRequirementUIState.value.depthAnaerobRoom,
                retentionTimeSedimentation = _areaStpRequirementUIState.value.retentionTimeSedimentation,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardD = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardD = false,
                            )
                        }

                        getCalculateSedimentationRoomRequirement()
                    }
                }
            }
        }
    }

    private fun getCalculateSedimentationRoomRequirement() {
        viewModelScope.launch {
            getCalculateSedimentationRoomRequirementsUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardD = true)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardD = false,
                                volumeSedimentationRoom = result.data?.volumeSedimentationRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                lengthSedimentationRoom = result.data?.lengthSedimentationRoom.toString(),
                                widthSedimentationRoom = result.data?.widthSedimentationRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                depthSedimentationRoom = result.data?.depthSedimentationRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                            )
                        }
                    }
                }
            }
        }
    }

    fun onChlorinationRoomInputFieldEvent(event: InputChlorinationRoomEvent) {
        when (event) {
            is InputChlorinationRoomEvent.DepthChlorinationRoomFieldChange -> {
                _areaStpRequirementUIState.update {
                    it.copy(depthChlorinationRoom = event.depthChlorinationRoom.replace(",", "."))
                }
            }
            is InputChlorinationRoomEvent.LengthChlorinationRoomFieldChange -> {
                _areaStpRequirementUIState.update {
                    it.copy(lengthChlorinationRoom = event.lengthChlorinationRoom.replace(",", "."))
                }
            }
        }
    }

    fun clearChlorinationRoomRequirement() {
        viewModelScope.launch {
            calculateChlorinationRoomRequirementsUseCase(
                retentionTimeChlorination = _areaStpRequirementUIState.value.retentionTimeChlorination,
                lengthChlorinationRoom = 0.0,
                depthChlorinationRoom = 0.0,
                inputFlowRate = _areaStpRequirementUIState.value.inputFlowRate,
                inputPeakHourAssumption = _areaStpRequirementUIState.value.inputPeakHourAssumption,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardE = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardE = false,
                            )
                        }

                        getCalculateChlorinationRoomRequirement()
                    }
                }
            }
        }
    }

    fun calculateChlorinationRoomRequirement() {
        viewModelScope.launch {
            // card to loading status
            _areaStpRequirementUIState.update {
                it.copy(isLoadingCardE = true)
            }

            // do calculate process
            val lengthChlorinationRoom =
                _areaStpRequirementUIState.value.lengthChlorinationRoom.ifEmpty { "0" }
            val depthChlorinationRoom =
                _areaStpRequirementUIState.value.depthChlorinationRoom.ifEmpty { "0" }

            calculateChlorinationRoomRequirementsUseCase(
                retentionTimeChlorination = _areaStpRequirementUIState.value.retentionTimeChlorination,
                lengthChlorinationRoom = lengthChlorinationRoom.replace(Regex("\\.+\\z"), "")
                    .toDouble(),
                depthChlorinationRoom = depthChlorinationRoom.replace(Regex("\\.+\\z"), "")
                    .toDouble(),
                inputFlowRate = _areaStpRequirementUIState.value.inputFlowRate,
                inputPeakHourAssumption = _areaStpRequirementUIState.value.inputPeakHourAssumption,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardE = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardE = false,
                            )
                        }

                        getCalculateChlorinationRoomRequirement()
                    }
                }
            }
        }
    }

    private fun getCalculateChlorinationRoomRequirement() {
        viewModelScope.launch {
            getCalculateChlorinationRoomRequirementsUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardE = true)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardE = false,
                                volumeChlorinationRoom = result.data?.volumeChlorinationRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                lengthChlorinationRoom = result.data?.lengthChlorinationRoom.toString(),
                                widthChlorinationRoom = result.data?.widthChlorinationRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                depthChlorinationRoom = result.data?.depthChlorinationRoom?.toString()
                                    ?: "",
                            )
                        }
                    }
                }
            }
        }
    }

    fun onFiltrationTankRoomInputFieldEvent(event: InputFiltrationTankRoomEvent) {
        when (event) {
            is InputFiltrationTankRoomEvent.LengthFiltrationTankRoomFieldChange -> {
                _areaStpRequirementUIState.update {
                    it.copy(
                        lengthFiltrationTankRoom = event.lengthFiltrationTankRoom.replace(",", ".")
                    )
                }
            }
        }
    }

    fun clearFiltrationTankRoomRequirement() {
        viewModelScope.launch {
            calculateFiltrationTankRoomRequirementsUseCase(
                inputPeakHourAssumption = _areaStpRequirementUIState.value.inputPeakHourAssumption,
                inputFlowRate = _areaStpRequirementUIState.value.inputFlowRate,
                depthAnaerobRoom = _areaStpRequirementUIState.value.depthAnaerobRoom,
                lengthFiltrationTank = 0.0,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardF = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardF = false,
                            )
                        }

                        getCalculateFiltrationTankRoomRequirement()
                    }
                }
            }
        }
    }

    fun calculateFiltrationTankRoomRequirement() {
        viewModelScope.launch {
            // card to loading status
            _areaStpRequirementUIState.update {
                it.copy(isLoadingCardF = true)
            }

            // do calculate process
            val lengthFiltrationTankRoom =
                _areaStpRequirementUIState.value.lengthFiltrationTankRoom.ifEmpty { "0" }

            calculateFiltrationTankRoomRequirementsUseCase(
                inputPeakHourAssumption = _areaStpRequirementUIState.value.inputPeakHourAssumption,
                inputFlowRate = _areaStpRequirementUIState.value.inputFlowRate,
                depthAnaerobRoom = _areaStpRequirementUIState.value.depthAnaerobRoom,
                lengthFiltrationTank = lengthFiltrationTankRoom.replace(Regex("\\.+\\z"), "")
                    .toDouble()
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardF = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardF = false,
                            )
                        }

                        getCalculateFiltrationTankRoomRequirement()
                    }
                }
            }
        }
    }

    private fun getCalculateFiltrationTankRoomRequirement() {
        viewModelScope.launch {
            getCalculateFiltrationTankRoomRequirementsUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardF = true)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardF = false,
                                volumeFiltrationTankRoom = result.data?.volumeFiltrationTankRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                lengthFiltrationTankRoom = result.data?.lengthFiltrationTankRoom.toString(),
                                widthFiltrationTankRoom = result.data?.widthFiltrationTankRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                depthFiltrationTankRoom = result.data?.depthFiltrationTankRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                            )
                        }
                    }
                }
            }
        }
    }

    fun onRecycleTankRoomInputFieldEvent(event: InputRecyclerTankRoomEvent) {
        when (event) {
            is InputRecyclerTankRoomEvent.LengthRecycleTankRoomFieldChange -> {
                _areaStpRequirementUIState.update {
                    it.copy(lengthRecycleTankRoom = event.lengthRecycleTankRoom.replace(",", "."))
                }
            }
            is InputRecyclerTankRoomEvent.VolumeRecycleTankRoomFieldChange -> {
                _areaStpRequirementUIState.update {
                    it.copy(volumeRecycleTankRoom = event.volumeRecycleTankRoom.replace(",", "."))
                }
            }
        }
    }

    fun clearRecycleTankRoomRequirement() {
        viewModelScope.launch {
            calculateRecycleTankRoomRequirementsUseCase(
                lengthRecyclerTankRoom = 0.0,
                volumeRecyclerTankRoom = 0.0,
                depthAnaerobRoom = _areaStpRequirementUIState.value.depthAnaerobRoom,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardG = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardG = false,
                            )
                        }

                        getCalculateRecycleTankRoomRequirement()
                    }
                }
            }
        }
    }

    fun calculateRecycleTankRoomRequirement() {
        viewModelScope.launch {
            // card to loading status
            _areaStpRequirementUIState.update {
                it.copy(isLoadingCardG = true)
            }

            // do calculate process
            val lengthRecycleTankRoom =
                _areaStpRequirementUIState.value.lengthRecycleTankRoom.ifEmpty { "0" }
            val volumeRecycleTankRoom =
                _areaStpRequirementUIState.value.volumeRecycleTankRoom.ifEmpty { "0" }

            calculateRecycleTankRoomRequirementsUseCase(
                lengthRecyclerTankRoom = lengthRecycleTankRoom.replace(Regex("\\.+\\z"), "")
                    .toDouble(),
                volumeRecyclerTankRoom = volumeRecycleTankRoom.replace(Regex("\\.+\\z"), "")
                    .toDouble(),
                depthAnaerobRoom = _areaStpRequirementUIState.value.depthAnaerobRoom,
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardG = false)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardG = false,
                            )
                        }

                        getCalculateRecycleTankRoomRequirement()
                    }
                }
            }
        }
    }

    private fun getCalculateRecycleTankRoomRequirement() {
        viewModelScope.launch {
            getCalculateRecycleTankRoomRequirementsUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isError = true)
                        }
                    }
                    is Resource.Loading -> {
                        _areaStpRequirementUIState.update {
                            it.copy(isLoadingCardG = true)
                        }
                    }
                    is Resource.Success -> {
                        _areaStpRequirementUIState.update {
                            it.copy(
                                isLoadingCardG = false,
                                volumeRecycleTankRoom = result.data?.volumeRecycleTankRoom.toString(),
                                lengthRecycleTankRoom = result.data?.lengthRecycleTankRoom.toString(),
                                widthRecycleTankRoom = result.data?.widthRecycleTankRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                                depthRecycleTankRoom = result.data?.depthRecycleTankRoom?.toBigDecimal()
                                    ?.setScale(1, RoundingMode.HALF_EVEN)?.toDouble() ?: 0.0,
                            )
                        }
                    }
                }
            }
        }
    }
}