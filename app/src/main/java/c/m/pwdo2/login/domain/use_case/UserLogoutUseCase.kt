package c.m.pwdo2.login.domain.use_case

import c.m.pwdo2.area_stp_requirement.domain.repository.AreaStpRequirementRepository
import c.m.pwdo2.common.util.SimpleResource
import c.m.pwdo2.login.domain.repository.LoginRepository
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class UserLogoutUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    //private var oxygenRequirementAndAreaRepository: OxygenRequirementAndAreaRepository,
) {
    suspend operator fun invoke(): Flow<SimpleResource> {
//        oxygenRequirementAndAreaRepository.saveInputParameters(
//            inputBodInlet = 0.0,
//            inputBodOutlet = 0.0,
//            inputCodInlet = 0.0,
//            inputCodOutlet = 0.0,
//            inputNhThreeInlet = 0.0,
//            inputNhThreeOutlet = 0.0,
//            inputFlowRate = 0.0,
//            inputPeakHourAssumption = 0.0,
//            inputWaterDepthAboveDiffuser = 0.0,
//        ).collect()
        return loginRepository.setLogoutStatus(isLogin = false)
    }
}