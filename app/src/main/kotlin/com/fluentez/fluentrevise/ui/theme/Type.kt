package com.fluentez.fluentrevise.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.fluentez.fluentrevise.R

// Khai báo FontFamily liên kết với các file .ttf trong thư mục res/font
val BeVietnamProFontFamily = FontFamily(
    Font(R.font.be_vietnam_pro_regular, FontWeight.Normal),
    Font(R.font.be_vietnam_pro_medium, FontWeight.Medium),
    Font(R.font.be_vietnam_pro_bold, FontWeight.Bold),
    Font(R.font.be_vietnam_pro_extrabold, FontWeight.ExtraBold)
)

private val baseline = Typography()

// Áp dụng font mới cho tất cả các kiểu chữ mặc định của Material 3
val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = BeVietnamProFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = BeVietnamProFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = BeVietnamProFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = BeVietnamProFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = BeVietnamProFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = BeVietnamProFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = BeVietnamProFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = BeVietnamProFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = BeVietnamProFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = BeVietnamProFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = BeVietnamProFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = BeVietnamProFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = BeVietnamProFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = BeVietnamProFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = BeVietnamProFontFamily),
)
