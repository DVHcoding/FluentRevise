package com.fluentez.fluentrevise.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.fluentez.fluentrevise.ui.theme.ButtonStartColor
import com.fluentez.fluentrevise.ui.theme.TextDark

@Composable
fun FilterDialog(
    onDismissRequest: () -> Unit,
    onOkClick: () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
            ) {
                Text(
                    text = "Settings",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    modifier = Modifier.padding(bottom = 16.dp),
                )

                // Settings Rows
                SettingSwitchRow(title = "Hiển thị thuật ngữ đầu tiên", defaultChecked = true)
                SettingSwitchRow(title = "Đọc nội dung", defaultChecked = false)

                // Language Selector Row
                var isLanguageMenuExpanded by remember { mutableStateOf(false) }
                val languages =
                    listOf(
                        "🇺🇸 Tiếng Anh",
                        "🇨🇳 Tiếng Trung",
                        "🇯🇵 Tiếng Nhật",
                        "🇰🇷 Tiếng Hàn",
                        "🇫🇷 Tiếng Pháp",
                        "🇩🇪 Tiếng Đức",
                        "🇪🇸 Tiếng Tây Ban Nha",
                        "🇷🇺 Tiếng Nga",
                        "🇻🇳 Tiếng Việt",
                    )
                var selectedLanguage by remember { mutableStateOf(languages[0]) }

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Ngôn ngữ đọc",
                        fontSize = 16.sp,
                        color = TextDark,
                    )

                    Box {
                        Box(
                            modifier =
                                Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                                    .clickable { isLanguageMenuExpanded = true }
                                    .padding(horizontal = 12.dp, vertical = 6.dp),
                        ) {
                            Text(
                                text = selectedLanguage,
                                fontSize = 14.sp,
                                color = TextDark,
                            )
                        }

                        DropdownMenu(
                            expanded = isLanguageMenuExpanded,
                            onDismissRequest = { isLanguageMenuExpanded = false },
                        ) {
                            languages.forEach { language ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = language,
                                            fontSize = 14.sp,
                                            color = TextDark,
                                        )
                                    },
                                    onClick = {
                                        selectedLanguage = language
                                        isLanguageMenuExpanded = false
                                    },
                                )
                            }
                        }
                    }
                }

                SettingSwitchRow(title = "Ẩn nội dung", defaultChecked = false)

                // Deep Memory Row
                var deepMemoryValue by remember { mutableFloatStateOf(2f) }
                var isDeepMemoryEnabled by remember { mutableStateOf(false) }
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Deep Memory",
                        fontSize = 16.sp,
                        color = TextDark,
                    )
                    Switch(
                        checked = isDeepMemoryEnabled,
                        onCheckedChange = { isDeepMemoryEnabled = it },
                        modifier = Modifier.scale(0.8f),
                        colors =
                            SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = ButtonStartColor,
                            ),
                    )
                }

                Slider(
                    value = deepMemoryValue,
                    onValueChange = { deepMemoryValue = it },
                    valueRange = 1f..5f,
                    steps = 3,
                    colors =
                        SliderDefaults.colors(
                            thumbColor = Color.White,
                            activeTrackColor = ButtonStartColor,
                            inactiveTrackColor = Color.LightGray.copy(alpha = 0.5f),
                        ),
                    modifier = Modifier.fillMaxWidth(),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    for (i in 1..5) {
                        Text(
                            text = i.toString(),
                            fontSize = 12.sp,
                            color = if (i == deepMemoryValue.toInt()) TextDark else Color.Gray,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    OutlinedButton(
                        onClick = onDismissRequest,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = TextDark),
                        modifier = Modifier.height(40.dp),
                    ) {
                        Text("Cancel")
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = {
                            onOkClick()
                            onDismissRequest()
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonStartColor),
                        modifier = Modifier.height(40.dp),
                    ) {
                        Text("OK", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun SettingSwitchRow(
    title: String,
    defaultChecked: Boolean,
) {
    var isChecked by remember { mutableStateOf(defaultChecked) }

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = TextDark,
        )
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            modifier = Modifier.scale(0.8f),
            colors =
                SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = ButtonStartColor,
                ),
        )
    }
}
