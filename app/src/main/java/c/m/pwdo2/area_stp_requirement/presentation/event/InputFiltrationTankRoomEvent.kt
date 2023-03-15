package c.m.pwdo2.area_stp_requirement.presentation.event

sealed class InputFiltrationTankRoomEvent {
    data class LengthFiltrationTankRoomFieldChange(val lengthFiltrationTankRoom: String) : InputFiltrationTankRoomEvent()
}
