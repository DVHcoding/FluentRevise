package com.fluentez.fluentrevise.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluentez.fluentrevise.ui.theme.ButtonStartColor
import com.fluentez.fluentrevise.ui.theme.CardBlobColor
import com.fluentez.fluentrevise.ui.theme.TermTagColor
import com.fluentez.fluentrevise.ui.theme.TextDark

@Composable
fun VocabularyCard(
    termCount: String,
    title: String,
    author: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Box {
            // Background blob decoration
            Box(
                modifier =
                    Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(CardBlobColor)
                        .align(Alignment.CenterEnd)
                        // Use offset instead of negative padding to avoid IllegalArgumentException in Preview
                        .offset(x = 50.dp),
            )

            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
            ) {
                // Top Row: Tag and Avatar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier =
                            Modifier
                                .background(TermTagColor, RoundedCornerShape(16.dp))
                                .padding(horizontal = 12.dp, vertical = 0.1.dp),
                    ) {
                        Text(
                            text = termCount,
                            color = Color.White,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(id = com.fluentez.fluentrevise.R.drawable.ic_avatar),
                        contentDescription = "Avatar",
                        contentScale = ContentScale.Crop,
                        modifier =
                            Modifier
                                .size(32.dp)
                                .clip(CircleShape),
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Title
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    lineHeight = 24.sp,
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Bottom Row: Author and Checkbox
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = author,
                        fontSize = 12.sp,
                        color = Color.Gray,
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        painter =
                            painterResource(
                                id =
                                    if (isChecked) {
                                        com.fluentez.fluentrevise.R.drawable.ic_check_circle
                                    } else {
                                        com.fluentez.fluentrevise.R.drawable.ic_uncheck_circle
                                    },
                            ),
                        contentDescription = if (isChecked) "Checked" else "Unchecked",
                        tint = if (isChecked) ButtonStartColor else Color.Gray,
                        modifier =
                            Modifier
                                .size(24.dp)
                                .clickable { onCheckedChange(!isChecked) },
                    )
                }
            }
        }
    }
}
