package c.m.pwdo2.oxygen_requirement_and_area.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import c.m.pwdo2.R
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme
import c.m.pwdo2.oxygen_requirement_and_area.presentation.state.OxygenRequirementAndAreaUIState

@Composable
fun CardDiffuserRequirement(
    modifier: Modifier = Modifier,
    oxygenRequirementAndAreaUIState: OxygenRequirementAndAreaUIState,
) {
    ElevatedCard(modifier = modifier) {
        Text(
            text = "Diffuser Requirement",
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
                text = stringResource(id = R.string.diffuser_twelve_inch),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.diffuserTwelveUnit != 0.0) {
                    "${oxygenRequirementAndAreaUIState.diffuserTwelveUnit}"
                } else {
                    "0.0"
                },
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Unit", fontSize = 14.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.diffuserTwelveUnitRoundUp != 0.0) {
                    "${oxygenRequirementAndAreaUIState.diffuserTwelveUnitRoundUp}"
                } else {
                    "0.0"
                },
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Unit", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.diffuser_nine_inch),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.diffuserNineUnit != 0.0) {
                    "${oxygenRequirementAndAreaUIState.diffuserNineUnit}"
                } else {
                    "0.0"
                },
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Unit", fontSize = 14.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = if (oxygenRequirementAndAreaUIState.diffuserNineUnitRoundUp != 0.0) {
                    "${oxygenRequirementAndAreaUIState.diffuserNineUnitRoundUp}"
                } else {
                    "0.0"
                },
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Unit", fontSize = 14.sp)
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
private fun PreviewCardDiffuserRequirement() {
    PWDO2Theme {
        Surface {
            CardDiffuserRequirement(oxygenRequirementAndAreaUIState = OxygenRequirementAndAreaUIState())
        }
    }
}