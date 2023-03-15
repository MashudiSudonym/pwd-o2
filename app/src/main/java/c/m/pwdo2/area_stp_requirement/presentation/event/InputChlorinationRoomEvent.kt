package c.m.pwdo2.area_stp_requirement.presentation.event

sealed class InputChlorinationRoomEvent {
    data class LengthChlorinationRoomFieldChange(val lengthChlorinationRoom: String) :
        InputChlorinationRoomEvent()

    data class DepthChlorinationRoomFieldChange(val depthChlorinationRoom: String) :
        InputChlorinationRoomEvent()
}
