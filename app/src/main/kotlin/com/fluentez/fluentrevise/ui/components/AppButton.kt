package com.fluentez.fluentrevise.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Component Tái Sử Dụng (Reusable Component).
 * Tại sao cần file này?
 * - Để đảm bảo toàn app (các màn hình khác nhau) dùng chung một kiểu Button chuẩn.
 * - Sau này muốn đổi màu hay đổi góc bo tròn cho tất cả nút, chỉ cần sửa ở 1 nơi duy nhất này.
 */
@Composable
fun AppButton(
    text: String, // Chữ hiển thị trên nút
    onClick: () -> Unit, // Hành động khi bấm nút (hàm lambda)
    modifier: Modifier = Modifier, // Cho phép truyền thêm tuỳ chỉnh từ bên ngoài (như width, height)
    backgroundColor: Color = MaterialTheme.colorScheme.primary, // Màu nền mặc định lấy từ Theme
    textColor: Color = MaterialTheme.colorScheme.onPrimary // Màu chữ mặc định lấy từ Theme
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(vertical = 8.dp), // Tự tạo khoảng cách trên dưới 8dp
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
