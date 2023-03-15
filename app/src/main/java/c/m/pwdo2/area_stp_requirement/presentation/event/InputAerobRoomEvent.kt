package c.m.pwdo2.area_stp_requirement.presentation.event

sealed class InputAerobRoomEvent {
    data class LengthAerobRoomFieldChange(val lengthAerobRoom: String) : InputAerobRoomEvent()
}
