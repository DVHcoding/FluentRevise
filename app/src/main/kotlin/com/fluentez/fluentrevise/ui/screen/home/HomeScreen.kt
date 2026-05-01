package com.fluentez.fluentrevise.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluentez.fluentrevise.R
import com.fluentez.fluentrevise.ui.components.AppButton
import com.fluentez.fluentrevise.ui.components.FilterDialog
import com.fluentez.fluentrevise.ui.components.ProfileMenu
import com.fluentez.fluentrevise.ui.components.VocabularyCard
import com.fluentez.fluentrevise.ui.navigation.Routes
import com.fluentez.fluentrevise.ui.theme.LogoFluentColor
import com.fluentez.fluentrevise.ui.theme.LogoReviseColor
import com.fluentez.fluentrevise.ui.theme.TextDark
import com.fluentez.fluentrevise.ui.theme.IconTint

@Composable
fun HomeScreen(onNavigateToLearning: () -> Unit) {
    var showProfileMenu by remember { mutableStateOf(false) }
    var showFilterDialog by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // 1. Top Bar: Logo and Avatar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = LogoFluentColor, fontWeight = FontWeight.Bold)) {
                            append("Fluent")
                        }
                        withStyle(style = SpanStyle(color = LogoReviseColor, fontWeight = FontWeight.Bold)) {
                            append("Revise")
                        }
                    },
                    fontSize = 24.sp
                )

                Box {
                    Image(
                        painter = painterResource(id = com.fluentez.fluentrevise.R.drawable.ic_avatar),
                        contentDescription = "Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable { showProfileMenu = true }
                    )
                    
                    ProfileMenu(
                        expanded = showProfileMenu,
                        onDismissRequest = { showProfileMenu = false },
                        onLogoutClick = { /* TODO: Handle Logout */ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Banner
            Image(
                painter = painterResource(id = com.fluentez.fluentrevise.R.drawable.img_banner),
                contentDescription = "Happy Weekend Banner",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Action Row: Button and Filter
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppButton(
                    text = "Bắt đầu",
                    onClick = onNavigateToLearning,
                    modifier = Modifier.width(120.dp),
                    backgroundColor = MaterialTheme.colorScheme.primary
                )

                Icon(
                    painter = painterResource(id = com.fluentez.fluentrevise.R.drawable.ic_filter),
                    contentDescription = "Filter",
                    tint = IconTint,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { showFilterDialog = true }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Title Row: Vocabularies and Sync
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Vocabularies",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )

                Icon(
                    painter = painterResource(id = com.fluentez.fluentrevise.R.drawable.ic_sync),
                    contentDescription = "Sync",
                    tint = IconTint,
                    modifier = Modifier
                        .size(15.dp)
                        .clickable { /* TODO: Sync Action */ }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 5. List of Vocabulary Cards
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    var isChecked by remember { mutableStateOf(true) }
                    VocabularyCard(
                        termCount = "99 thuật ngữ",
                        title = "Daily Words You'll Use at Home 🏡 | Comprehensible input Young Electronic Engineer Competition Self-Introduction - Topic 1...",
                        author = "Đỗ Hùng",
                        isChecked = isChecked,
                        onCheckedChange = { isChecked = it }
                    )
                }
                item {
                    var isChecked by remember { mutableStateOf(false) }
                    VocabularyCard(
                        termCount = "99 thuật ngữ",
                        title = "Hobbies & Interests - Topic 4",
                        author = "Đỗ Hùng",
                        isChecked = isChecked,
                        onCheckedChange = { isChecked = it }
                    )
                }
                item {
                    var isChecked by remember { mutableStateOf(false) }
                    VocabularyCard(
                        termCount = "99 thuật ngữ",
                        title = "Eye For Colour Exhibition",
                        author = "Đỗ Hùng",
                        isChecked = isChecked,
                        onCheckedChange = { isChecked = it }
                    )
                }
            }
        }
        
        if (showFilterDialog) {
            FilterDialog(
                onDismissRequest = { showFilterDialog = false },
                onOkClick = { showFilterDialog = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    com.fluentez.fluentrevise.ui.theme.FluentReviseTheme {
        HomeScreen(onNavigateToLearning = {})
    }
}
