package c.m.pwdo2.oxygen_requirement_and_area.domain.model

data class InputParameters(
    val inputBodInlet: Double = 0.0,
    val inputBodOutlet: Double = 0.0,
    val inputCodInlet: Double = 0.0,
    val inputCodOutlet: Double = 0.0,
    val inputNhThreeInlet: Double = 0.0,
    val inputNhThreeOutlet: Double = 0.0,
    val inputFlowRate: Double = 0.0,
    val inputPeakHourAssumption: Double = 0.0,
    val inputWaterDepthAboveDiffuser: Double = 0.0,
)
