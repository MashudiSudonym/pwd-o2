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
import c.m.pwdo2.area_stp_requirement.presentation.event.InputGreaseTrapRoomEvent
import c.m.pwdo2.area_stp_requirement.presentation.state.AreaStpRequirementUIState
import c.m.pwdo2.area_stp_requirement.presentation.view_model.AreaStpRequirementViewModel
import c.m.pwdo2.common.presentation.component.custom.TextFieldTypeUniversal
import c.m.pwdo2.common.presentation.component.util.AdaptiveThemeColor

@Composable
fun GreaseTrapRoomRequirementsCard(
    modifier: Modifier = Modifier,
    areaStpRequirementUIState: AreaStpRequirementUIState,
    areaStpRequirementViewModel: AreaStpRequirementViewModel,
) {
    ElevatedCard(
        modifier = modifier,
    ) {
        if (areaStpRequirementUIState.isLoadingCardB) {
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
                    text = "Kebutuhan Ruangan Grease Trap :", fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Volume",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        text = if (areaStpRequirementUIState.volumeGreaseTrapRoom != 0.0) {
                            "${areaStpRequirementUIState.volumeGreaseTrapRoom}"
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
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Length",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        text = if (areaStpRequirementUIState.lengthGreaseTrapRoom != "") {
                            areaStpRequirementUIState.lengthGreaseTrapRoom
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
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Width",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        text = if (areaStpRequirementUIState.widthGreaseTrapRoom != 0.0) {
                            "${areaStpRequirementUIState.widthGreaseTrapRoom}"
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
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Depth",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        text = if (areaStpRequirementUIState.depthGreaseTrapRoom != "") {
                            areaStpRequirementUIState.depthGreaseTrapRoom
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
                    text = "Ruangan dibagi menjadi minimum 3 chamber",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { areaStpRequirementViewModel.isExpandedCardB() },
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
                        imageVector = if (areaStpRequirementUIState.isExpandedCardB) {
                            Icons.Rounded.ExpandLess
                        } else {
                            Icons.Rounded.ExpandMore
                        }, contentDescription = "Expanded"
                    )
                }
                AnimatedVisibility(visible = areaStpRequirementUIState.isExpandedCardB) {
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
                            TextFieldTypeUniversal(
                                modifier = Modifier.fillMaxWidth(fraction = 0.75F),
                                placeholderText = "0.0",
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next,
                                textValue = if (areaStpRequirementUIState.lengthGreaseTrapRoom == "0.0") {
                                    ""
                                } else {
                                    areaStpRequirementUIState.lengthGreaseTrapRoom
                                },
                                textValueChange = {
                                    areaStpRequirementViewModel.onGreaseTrapRoomInputFieldEvent(
                                        InputGreaseTrapRoomEvent.LengthGreaseTrapRoomFieldChange(it)
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "m",
                                fontSize = 14.sp,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Depth",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            TextFieldTypeUniversal(
                                modifier = Modifier.fillMaxWidth(fraction = 0.75F),
                                placeholderText = "0.0",
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                                keyboardActions = KeyboardActions(onDone = { areaStpRequirementViewModel.calculateGreaseTrapRoomRequirement() }),
                                textValue = if (areaStpRequirementUIState.depthGreaseTrapRoom == "0.0") {
                                    ""
                                } else {
                                    areaStpRequirementUIState.depthGreaseTrapRoom
                                },
                                textValueChange = {
                                    areaStpRequirementViewModel.onGreaseTrapRoomInputFieldEvent(
                                        InputGreaseTrapRoomEvent.DepthGreaseTrapRoomFieldChange(it)
                                    )
                                }
                            )
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
                                    areaStpRequirementViewModel.clearGraceTrapRoomRequirement()
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
                                    areaStpRequirementViewModel.calculateGreaseTrapRoomRequirement()
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