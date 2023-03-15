package c.m.pwdo2.oxygen_requirement_and_area.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme
import c.m.pwdo2.oxygen_requirement_and_area.presentation.state.OxygenRequirementAndAreaUIState

@Composable
fun CardBlowerCapacityRequirement(
    modifier: Modifier = Modifier,
    oxygenRequirementAndAreaUIState: OxygenRequirementAndAreaUIState,
) {
    ElevatedCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Blower Capacity Requirement",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (oxygenRequirementAndAreaUIState.blowerCapacityRequirementPerHour != 0.0) {
                    "${oxygenRequirementAndAreaUIState.blowerCapacityRequirementPerHour} m3 Air/hour"
                } else {
                    "0.0 m3 Air/hour"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (oxygenRequirementAndAreaUIState.blowerCapacityRequirementPerMinute != 0.0) {
                    "${oxygenRequirementAndAreaUIState.blowerCapacityRequirementPerMinute} m3 Air/min"
                } else {
                    "0.0 m3 Air/min"
                }, fontSize = 14.sp
            )
        }
    }
}

@Preview(
    showBackground = true, name = "enable text field",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    device = "id:Samsung A51"
)
@Composable
private fun PreviewCardBlowerCapacityRequirement() {
    PWDO2Theme {
        Surface {
            CardBlowerCapacityRequirement(oxygenRequirementAndAreaUIState = OxygenRequirementAndAreaUIState())
        }
    }
}