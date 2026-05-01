package com.fluentez.fluentrevise.ui.navigation

/**
 * Định nghĩa các Route (đường dẫn) trong ứng dụng.
 * Có thể dùng String thông thường, nhưng dùng object chứa hằng số (const val) 
 * giúp tránh lỗi gõ sai chữ (typo) khi lập trình.
 */
object Routes {
    // Mỗi màn hình được định danh bằng một String duy nhất
    const val HOME = "home_screen"
    const val LEARNING = "learning_screen"
}
