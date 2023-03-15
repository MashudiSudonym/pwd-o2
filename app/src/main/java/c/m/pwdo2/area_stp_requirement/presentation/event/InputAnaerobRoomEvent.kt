package c.m.pwdo2.area_stp_requirement.presentation.event

sealed class InputAnaerobRoomEvent {
    data class LengthAnaerobRoomFieldChange(val lengthAnaerobRoom: String) : InputAnaerobRoomEvent()
}
