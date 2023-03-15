package c.m.pwdo2.area_stp_requirement.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import c.m.pwdo2.area_stp_requirement.presentation.event.InputAnaerobRoomEvent
import c.m.pwdo2.area_stp_requirement.presentation.state.AreaStpRequirementUIState
import c.m.pwdo2.area_stp_requirement.presentation.view_model.AreaStpRequirementViewModel
import c.m.pwdo2.common.presentation.component.custom.TextFieldTypeUniversal
import c.m.pwdo2.common.presentation.component.util.AdaptiveThemeColor

@Composable
fun AnaerobicRoomRequirementsCard(
    modifier: Modifier = Modifier,
    areaStpRequirementUIState: AreaStpRequirementUIState,
    areaStpRequirementViewModel: AreaStpRequirementViewModel,
) {
    ElevatedCard(
        modifier = modifier,
    ) {
        if (areaStpRequirementUIState.isLoadingCardA) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Kebutuhan Ruangan Anaerob :",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Volume",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        text = if (areaStpRequirementUIState.volumeAnaerobRoom != 0.0) {
                            "${areaStpRequirementUIState.volumeAnaerobRoom}"
                        } else {
                            "0.0"
                        },
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "m3", fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Length",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        text = if (areaStpRequirementUIState.lengthAnaerobRoom != "") {
                            areaStpRequirementUIState.lengthAnaerobRoom
                        } else {
                            "0.0"
                        },
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "m", fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Width",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        text = if (areaStpRequirementUIState.widthAnaerobRoom != 0.0) {
                            "${areaStpRequirementUIState.widthAnaerobRoom}"
                        } else {
                            "0.0"
                        },
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "m", fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Depth",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        text = if (areaStpRequirementUIState.depthAnaerobRoom != 0.0) {
                            "${areaStpRequirementUIState.depthAnaerobRoom}"
                        } else {
                            "0.0"
                        },
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "m", fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ruangan dibagi menjadi 2 chamber",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { areaStpRequirementViewModel.isExpandedCardA() },
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp),
                        text = "Input Parameters",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Icon(
                        modifier = Modifier.padding(vertical = 16.dp),
                        imageVector = if (areaStpRequirementUIState.isExpandedCardA) {
                            Icons.Rounded.ExpandLess
                        } else {
                            Icons.Rounded.ExpandMore
                        },
                        contentDescription = "Expanded"
                    )
                }
                AnimatedVisibility(visible = areaStpRequirementUIState.isExpandedCardA) {
                    Column {
                        Text(
                            text = "Length",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            TextFieldTypeUniversal(modifier = Modifier.fillMaxWidth(fraction = 0.75F),
                                placeholderText = "0.0",
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                                keyboardActions = KeyboardActions(onDone = { areaStpRequirementViewModel.calculateAnaerobRoomRequirement() }),
                                textValue = if (areaStpRequirementUIState.lengthAnaerobRoom == "0.0") {
                                    ""
                                } else {
                                    areaStpRequirementUIState.lengthAnaerobRoom
                                },
                                textValueChange = {
                                    areaStpRequirementViewModel.onAnaerobRoomInputFieldEvent(
                                        InputAnaerobRoomEvent.LengthAnaerobRoomFieldChange(it)
                                    )
                                })
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "m",
                                fontSize = 14.sp,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            TextButton(
                                onClick = {
                                    areaStpRequirementViewModel.clearAnaerobRoomRequirement()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.surface,
                                    contentColor = Color.Red,
                                ),
                            ) {
                                Text(
                                    text = "Clear",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                            ElevatedButton(
                                onClick = {
                                    areaStpRequirementViewModel.calculateAnaerobRoomRequirement()
                                },
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
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}