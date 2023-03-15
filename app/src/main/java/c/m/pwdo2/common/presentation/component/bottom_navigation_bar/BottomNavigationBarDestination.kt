package c.m.pwdo2.common.presentation.component.bottom_navigation_bar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.RadioButtonChecked
import androidx.compose.ui.graphics.vector.ImageVector
import c.m.pwdo2.R
import c.m.pwdo2.destinations.AreaStpRequirementScreenDestination
import c.m.pwdo2.destinations.OxygenRequirementAndAreaScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomNavigationBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int,
) {
    OxygenRequirementAndAreaScreen(
        OxygenRequirementAndAreaScreenDestination,
        Icons.Rounded.RadioButtonChecked,
        R.string.oxygen_requirement_and_area,
    ),
    AreaStpRequirementScreen(
        AreaStpRequirementScreenDestination,
        Icons.Rounded.RadioButtonChecked,
        R.string.area_stp_requirement,
    ),
}