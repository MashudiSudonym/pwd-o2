package c.m.pwdo2.oxygen_requirement_and_area.presentation.event

sealed class InputParametersEvent {
    data class BodInletFieldChange(val bodInlet: String) : InputParametersEvent()
    data class BodOutletFieldChange(val bodOutlet: String) : InputParametersEvent()
    data class CodInletFieldChange(val codInlet: String) : InputParametersEvent()
    data class CodOutletFieldChange(val codOutlet: String) : InputParametersEvent()
    data class NhThreeInletFieldChange(val nhThreeInlet: String) : InputParametersEvent()
    data class NhThreeOutletFieldChange(val nhThreeOutlet: String) : InputParametersEvent()
    data class FlowRateFieldChange(val flowRate: String) : InputParametersEvent()
    data class PeakHourAssumption(val peakHourAssumption: String) : InputParametersEvent()
    data class WaterDepthAboveDiffuser(val waterDepthAboveDiffuser: String) : InputParametersEvent()
}
