package com.fluentez.fluentrevise.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Component LoadingView dùng để hiển thị vòng xoay tải dữ liệu chuẩn của app.
 * Bất cứ màn hình nào cần hiện loading khi gọi API chỉ cần gọi `LoadingView()`
 */
@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    // Box giống như FrameLayout, giúp đặt phần tử con vào vị trí tuỳ ý
    Box(
        modifier = modifier.fillMaxSize(), // Chiếm toàn bộ màn hình
        contentAlignment = Alignment.Center // Căn phần tử con vào chính giữa
    ) {
        // Vòng xoay mặc định của Material Design
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}
