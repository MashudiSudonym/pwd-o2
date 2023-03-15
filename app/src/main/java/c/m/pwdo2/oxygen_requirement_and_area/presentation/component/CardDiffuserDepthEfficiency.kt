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
fun CardDiffuserDepthEfficiency(
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
                text = "Diffuser Depth Efficiency",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (oxygenRequirementAndAreaUIState.diffuserDepthEfficiency != 0.0) {
                    "${oxygenRequirementAndAreaUIState.diffuserDepthEfficiency} %"
                } else {
                    "0.0 %"
                },
                fontSize = 14.sp
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
private fun PreviewCardDiffuserDepthEfficiency() {
    PWDO2Theme {
        Surface {
            CardDiffuserDepthEfficiency(oxygenRequirementAndAreaUIState = OxygenRequirementAndAreaUIState())
        }
    }
}