package c.m.pwdo2.oxygen_requirement_and_area.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import c.m.pwdo2.R
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme
import c.m.pwdo2.oxygen_requirement_and_area.presentation.state.OxygenRequirementAndAreaUIState

@Composable
fun CardAreaVolumeRequirement(
    modifier: Modifier = Modifier,
    oxygenRequirementAndAreaUIState: OxygenRequirementAndAreaUIState,
) {
    ElevatedCard(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Area & Volume Requirement",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.area_diffuser_twelve_inch),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Clip,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (oxygenRequirementAndAreaUIState.aerationChamberAreaWithDiffuserTwelveInch != 0.0) {
                    "${oxygenRequirementAndAreaUIState.aerationChamberAreaWithDiffuserTwelveInch} m2"
                } else {
                    "0.0 m2"
                }, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.volume_diffuser_twelve_inch),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Clip,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (oxygenRequirementAndAreaUIState.aerationChamberVolumeWithDiffuserTwelveInch != 0.0) {
                    "${oxygenRequirementAndAreaUIState.aerationChamberVolumeWithDiffuserTwelveInch} m3"
                } else {
                    "0.0 m3"
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
private fun PreviewCardAreaVolumeRequirement() {
    PWDO2Theme {
        Surface {
            CardAreaVolumeRequirement(oxygenRequirementAndAreaUIState = OxygenRequirementAndAreaUIState())
        }
    }
}