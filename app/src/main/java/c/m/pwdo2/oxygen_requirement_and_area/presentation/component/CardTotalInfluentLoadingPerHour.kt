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
fun CardTotalInfluentLoadingPerHour(
    modifier: Modifier = Modifier,
    oxygenRequirementAndAreaUIState: OxygenRequirementAndAreaUIState,
) {
    ElevatedCard(modifier = modifier) {

        Text(
            text = "Total Influent Loading BOD, COD, & NH3 per Hour",
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
                text = "BOD Inlet",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.totalBodInlet != 0.0) {
                    "${oxygenRequirementAndAreaUIState.totalBodInlet}"
                } else {
                    "0.0"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "kg BOD/hour", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "COD Inlet",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.totalCodInlet != 0.0) {
                    "${oxygenRequirementAndAreaUIState.totalCodInlet}"
                } else {
                    "0.0"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "kg COD/hour", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "NH3 Inlet",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.totalNhThreeInlet != 0.0) {
                    "${oxygenRequirementAndAreaUIState.totalNhThreeInlet}"
                } else {
                    "0.0"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "kg NH3/hour", fontSize = 14.sp)
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
private fun PreviewCardTotalInfluentLoadingPerHour() {
    PWDO2Theme {
        Surface {
            CardTotalInfluentLoadingPerHour(oxygenRequirementAndAreaUIState = OxygenRequirementAndAreaUIState())
        }
    }
}