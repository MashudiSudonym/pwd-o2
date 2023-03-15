package c.m.pwdo2.oxygen_requirement_and_area.presentation.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import c.m.pwdo2.common.presentation.component.custom.TextFieldTypeUniversal
import c.m.pwdo2.common.presentation.component.util.AdaptiveThemeColor
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme
import c.m.pwdo2.oxygen_requirement_and_area.presentation.event.InputParametersEvent
import c.m.pwdo2.oxygen_requirement_and_area.presentation.state.OxygenRequirementAndAreaUIState
import c.m.pwdo2.oxygen_requirement_and_area.presentation.view_model.OxygenRequirementAndAreaViewModel

@Composable
fun CardInputParameters(
    modifier: Modifier = Modifier,
    oxygenRequirementAndAreaViewModel: OxygenRequirementAndAreaViewModel,
    oxygenRequirementAndAreaUIState: OxygenRequirementAndAreaUIState,
) {
    ElevatedCard(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { oxygenRequirementAndAreaViewModel.isExpandedStatus() },
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Input Parameters",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.weight(1F))
            Icon(
                modifier = Modifier.padding(16.dp),
                imageVector = if (oxygenRequirementAndAreaUIState.isExpandedInputParametersCard) {
                    Icons.Rounded.ExpandLess
                } else {
                    Icons.Rounded.ExpandMore
                }, contentDescription = "Expanded"
            )
        }
        AnimatedVisibility(
            visible = oxygenRequirementAndAreaUIState.isExpandedInputParametersCard
        ) {
            if (oxygenRequirementAndAreaUIState.isLoadingInputParametersCard) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "BOD Inlet",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TextFieldTypeUniversal(
                            modifier = Modifier.fillMaxWidth(fraction = 0.75F),
                            placeholderText = "0.0",
                            textValue = if (oxygenRequirementAndAreaUIState.inputBodInlet == "0.0") {
                                ""
                            } else {
                                oxygenRequirementAndAreaUIState.inputBodInlet
                            },
                            textValueChange = {
                                oxygenRequirementAndAreaViewModel.onInputFieldEvent(
                                    InputParametersEvent.BodInletFieldChange(it)
                                )
                            },
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ppm",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "BOD Outlet",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TextFieldTypeUniversal(
                            modifier = Modifier.fillMaxWidth(fraction = 0.75F),
                            placeholderText = "0.0",
                            textValue = if (oxygenRequirementAndAreaUIState.inputBodOutlet == "0.0") {
                                ""
                            } else {
                                oxygenRequirementAndAreaUIState.inputBodOutlet
                            },
                            textValueChange = {
                                oxygenRequirementAndAreaViewModel.onInputFieldEvent(
                                    InputParametersEvent.BodOutletFieldChange(it)
                                )
                            },
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ppm",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "COD Inlet",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TextFieldTypeUniversal(
                            modifier = Modifier.fillMaxWidth(fraction = 0.75F),
                            placeholderText = "0.0",
                            textValue = if (oxygenRequirementAndAreaUIState.inputCodInlet == "0.0") {
                                ""
                            } else {
                                oxygenRequirementAndAreaUIState.inputCodInlet
                            },
                            textValueChange = {
                                oxygenRequirementAndAreaViewModel.onInputFieldEvent(
                                    InputParametersEvent.CodInletFieldChange(it)
                                )
                            },
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ppm",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "COD Outlet",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TextFieldTypeUniversal(
                            modifier = Modifier.fillMaxWidth(fraction = 0.75F),
                            placeholderText = "0.0",
                            textValue = if (oxygenRequirementAndAreaUIState.inputCodOutlet == "0.0") {
                                ""
                            } else {
                                oxygenRequirementAndAreaUIState.inputCodOutlet
                            },
                            textValueChange = {
                                oxygenRequirementAndAreaViewModel.onInputFieldEvent(
                                    InputParametersEvent.CodOutletFieldChange(it)
                                )
                            },
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ppm",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "NH3 Inlet",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TextFieldTypeUniversal(
                            modifier = Modifier.fillMaxWidth(fraction = 0.75F),
                            placeholderText = "0.0",
                            textValue = if (oxygenRequirementAndAreaUIState.inputNhThreeInlet == "0.0") {
                                ""
                            } else {
                                oxygenRequirementAndAreaUIState.inputNhThreeInlet
                            },
                            textValueChange = {
                                oxygenRequirementAndAreaViewModel.onInputFieldEvent(
                                    InputParametersEvent.NhThreeInletFieldChange(it)
                                )
                            },
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ppm",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "NH3 Outlet",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TextFieldTypeUniversal(
                            modifier = Modifier.fillMaxWidth(fraction = 0.75F),
                            placeholderText = "0.0",
                            textValue = if (oxygenRequirementAndAreaUIState.inputNhThreeOutlet == "0.0") {
                                ""
                            } else {
                                oxygenRequirementAndAreaUIState.inputNhThreeOutlet
                            },
                            textValueChange = {
                                oxygenRequirementAndAreaViewModel.onInputFieldEvent(
                                    InputParametersEvent.NhThreeOutletFieldChange(it)
                                )
                            },
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ppm",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Flow Rate",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TextFieldTypeUniversal(
                            modifier = Modifier.fillMaxWidth(fraction = 0.75F),
                            placeholderText = "0.0",
                            textValue = if (oxygenRequirementAndAreaUIState.inputFlowRate == "0.0") {
                                ""
                            } else {
                                oxygenRequirementAndAreaUIState.inputFlowRate
                            },
                            textValueChange = {
                                oxygenRequirementAndAreaViewModel.onInputFieldEvent(
                                    InputParametersEvent.FlowRateFieldChange(it)
                                )
                            },
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "m3/day",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Peak Hour Assumption",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TextFieldTypeUniversal(
                            modifier = Modifier.fillMaxWidth(fraction = 0.75F),
                            placeholderText = "0.0",
                            textValue = if (oxygenRequirementAndAreaUIState.inputPeakHourAssumption == "0.0") {
                                ""
                            } else {
                                oxygenRequirementAndAreaUIState.inputPeakHourAssumption
                            },
                            textValueChange = {
                                oxygenRequirementAndAreaViewModel.onInputFieldEvent(
                                    InputParametersEvent.PeakHourAssumption(it)
                                )
                            },
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "per day",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Water depth (above diffuser)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TextFieldTypeUniversal(
                            modifier = Modifier.fillMaxWidth(fraction = 0.75F),
                            placeholderText = "0.0",
                            textValue = if (oxygenRequirementAndAreaUIState.inputWaterDepthAboveDiffuser == "0.0") {
                                ""
                            } else {
                                oxygenRequirementAndAreaUIState.inputWaterDepthAboveDiffuser
                            },
                            textValueChange = {
                                oxygenRequirementAndAreaViewModel.onInputFieldEvent(
                                    InputParametersEvent.WaterDepthAboveDiffuser(it)
                                )
                            },
                            keyboardActions = KeyboardActions(onDone = {
                                oxygenRequirementAndAreaViewModel.saveInputParameters()
                                oxygenRequirementAndAreaViewModel.isExpandedStatus()
                            }),
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "m",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    ElevatedButton(
                        onClick = {
                            oxygenRequirementAndAreaViewModel.saveInputParameters()
                            oxygenRequirementAndAreaViewModel.isExpandedStatus()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AdaptiveThemeColor(
                                isDarkTheme = isSystemInDarkTheme(),
                                onDark = Color.White,
                                onLight = Color.Black,
                            ),
                            contentColor = AdaptiveThemeColor(
                                isDarkTheme = isSystemInDarkTheme(),
                                onDark = Color.Black,
                                onLight = Color.White,
                            ),
                        ),
                    ) {
                        Text(
                            text = "Calculate",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TextButton(
                        onClick = {
                            oxygenRequirementAndAreaViewModel.clearInputParameters()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = Color.Red,
                        ),
                    ) {
                        Text(
                            text = "Clear",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview(
    showBackground = true, name = "enable text field",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    device = "id:Samsung A51"
)
@Composable
private fun PreviewCardInputParameters() {
    PWDO2Theme {
        Surface {
            CardInputParameters(
                oxygenRequirementAndAreaViewModel = hiltViewModel(),
                oxygenRequirementAndAreaUIState = OxygenRequirementAndAreaUIState()
            )
        }
    }
}