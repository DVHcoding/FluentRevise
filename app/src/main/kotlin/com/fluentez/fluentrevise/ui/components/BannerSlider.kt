package com.fluentez.fluentrevise.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * Auto-scrolling banner slider (ping-pong: 0→1→2→1→0→1→…).
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerSlider(
    imageUrls: List<String>,
    autoScrollDelayMs: Long = 7_000L,
    modifier: Modifier = Modifier,
) {
    if (imageUrls.isEmpty()) return

    val pagerState = rememberPagerState(initialPage = 0) { imageUrls.size }

    // Ping-pong auto-scroll: tiến → đến cuối → lùi → đến đầu → tiến …
    LaunchedEffect(Unit) {
        var forward = true
        while (true) {
            delay(autoScrollDelayMs)
            val current = pagerState.currentPage
            val next = if (forward) current + 1 else current - 1

            if (next >= imageUrls.size) {
                forward = false
                pagerState.animateScrollToPage(current - 1)
            } else if (next < 0) {
                forward = true
                pagerState.animateScrollToPage(current + 1)
            } else {
                pagerState.animateScrollToPage(next)
            }
        }
    }

    val currentIndex by remember { derivedStateOf { pagerState.currentPage } }

    Column(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            pageSpacing = 8.dp,
        ) { page ->
            NetworkImage(
                url = imageUrls[page],
                contentDescription = "Banner ${page + 1}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Dot indicators
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            imageUrls.indices.forEach { index ->
                val isSelected = index == currentIndex
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(if (isSelected) 8.dp else 6.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) Color(0xFF4CAF82) else Color(0xFFBDBDBD)
                        ),
                )
            }
        }
    }
}