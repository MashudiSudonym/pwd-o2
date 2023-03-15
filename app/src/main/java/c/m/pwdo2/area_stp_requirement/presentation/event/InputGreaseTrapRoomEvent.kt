package c.m.pwdo2.area_stp_requirement.presentation.event

sealed class InputGreaseTrapRoomEvent {
    data class LengthGreaseTrapRoomFieldChange(val lengthGreaseTrapRoom: String) :
        InputGreaseTrapRoomEvent()

    data class DepthGreaseTrapRoomFieldChange(val depthGreaseTrapRoom: String) :
        InputGreaseTrapRoomEvent()
}
