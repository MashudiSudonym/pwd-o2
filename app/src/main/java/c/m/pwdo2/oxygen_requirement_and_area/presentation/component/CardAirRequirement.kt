package c.m.pwdo2.oxygen_requirement_and_area.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme
import c.m.pwdo2.oxygen_requirement_and_area.presentation.state.OxygenRequirementAndAreaUIState

@Composable
fun CardAirRequirement(modifier: Modifier = Modifier, oxygenRequirementAndAreaUIState: OxygenRequirementAndAreaUIState) {
    ElevatedCard(modifier = modifier) {
        Text(
            text = "Air Requirement ",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "For BOD",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.forBodPerHour != 0.0) {
                    "${oxygenRequirementAndAreaUIState.forBodPerHour}"
                } else {
                    "0.0"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "m3 Air/hour", fontSize = 14.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.forBodPerMinute != 0.0) {
                    "${
                        oxygenRequirementAndAreaUIState.forBodPerMinute
                    }"
                } else {
                    "0.0"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "m3 Air/min", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "For COD",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.forCodPerHour != 0.0) {
                    "${oxygenRequirementAndAreaUIState.forCodPerHour}"
                } else {
                    "0.0"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "m3 Air/hour", fontSize = 14.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.forCodPerMinute != 0.0) {
                    "${oxygenRequirementAndAreaUIState.forCodPerMinute}"
                } else {
                    "0.0"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "m3 Air/min", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "For NH3",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.forNhThreePerHour != 0.0) {
                    "${oxygenRequirementAndAreaUIState.forNhThreePerHour}"
                } else {
                    "0.0"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "m3 Air/hour", fontSize = 14.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.forNhThreePerMinute != 0.0) {
                    "${oxygenRequirementAndAreaUIState.forNhThreePerMinute}"
                } else {
                    "0.0"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "m3 Air/min", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Total Air Requirement",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.totalAirRequirementPerHour != 0.0) {
                    "${oxygenRequirementAndAreaUIState.totalAirRequirementPerHour}"
                } else {
                    "0.0"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "m3 Air/hour", fontSize = 14.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.totalAirRequirementPerMinute != 0.0) {
                    "${oxygenRequirementAndAreaUIState.totalAirRequirementPerMinute}"
                } else {
                    "0.0"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "m3 Air/min", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(
    showBackground = true, name = "enable text field",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    device = "id:Samsung A51"
)
@Composable
private fun PreviewCardAirRequirement() {
    PWDO2Theme {
        Surface {
            CardAirRequirement(oxygenRequirementAndAreaUIState = OxygenRequirementAndAreaUIState())
        }
    }
}