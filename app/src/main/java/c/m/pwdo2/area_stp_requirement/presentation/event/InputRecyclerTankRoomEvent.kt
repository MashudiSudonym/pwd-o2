package c.m.pwdo2.area_stp_requirement.presentation.event

sealed class InputRecyclerTankRoomEvent {
    data class LengthRecycleTankRoomFieldChange(val lengthRecycleTankRoom: String) :
        InputRecyclerTankRoomEvent()

    data class VolumeRecycleTankRoomFieldChange(val volumeRecycleTankRoom: String) :
        InputRecyclerTankRoomEvent()
}
