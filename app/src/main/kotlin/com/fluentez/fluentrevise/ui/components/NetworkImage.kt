package com.fluentez.fluentrevise.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.ui.unit.dp

/**
 * A reusable component to load images from a URL using Coil.
 * In Android Studio Preview mode, shows a placeholder color box instead.
 *
 * @param url The image URL to load.
 * @param contentDescription Description for accessibility.
 * @param modifier Modifier for the image.
 * @param contentScale How the image should be scaled.
 */
@Composable
fun NetworkImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    // Android Studio Preview không thể load ảnh mạng → hiện placeholder màu
    if (LocalInspectionMode.current) {
        Box(
            modifier = modifier.background(Color(0xFFCCCCCC)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "🖼 Banner",
                color = Color(0xFF777777),
                style = MaterialTheme.typography.bodySmall,
            )
        }
        return
    }

    // Dùng SubcomposeAsyncImage để hiển thị loading và error state rõ ràng
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36")
            .crossfade(true)
            .allowHardware(false) // Giữ để đảm bảo tương thích tối đa
            .build(),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        loading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.dp
                )
            }
        },
        error = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFEEEEEE)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Error",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Lỗi tải ảnh",
                        color = Color.Gray,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    )
}
