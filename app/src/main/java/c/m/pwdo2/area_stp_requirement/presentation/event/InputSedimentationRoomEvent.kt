package c.m.pwdo2.area_stp_requirement.presentation.event

sealed class InputSedimentationRoomEvent {
    data class LengthSedimentationRoomFieldChange(val lengthSedimentationRoom: String) : InputSedimentationRoomEvent()
}
