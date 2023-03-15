package c.m.pwdo2.oxygen_requirement_and_area.domain.model

data class AirRequirement(
    val forBodPerMinute: Double = 0.0,
    val forCodPerMinute: Double = 0.0,
    val forNhThreePerMinute: Double = 0.0,
    val totalAirRequirementPerMinute: Double = 0.0,
    val forBodPerHour: Double = 0.0,
    val forCodPerHour: Double = 0.0,
    val forNhThreePerHour: Double = 0.0,
    val totalAirRequirementPerHour: Double = 0.0,
)
