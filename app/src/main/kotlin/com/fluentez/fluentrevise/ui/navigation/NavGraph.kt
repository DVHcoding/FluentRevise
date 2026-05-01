package com.fluentez.fluentrevise.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fluentez.fluentrevise.ui.screen.LearningScreen
import com.fluentez.fluentrevise.ui.screen.home.HomeScreen

/**
 * AppNavGraph là "bản đồ" quản lý việc chuyển trang (điều hướng) của toàn bộ App.
 *
 * Cơ chế hoạt động (Navigation trong Compose):
 * 1. NavController: Giống như "tài xế", nhận lệnh để đi đến một Route (đích đến) nào đó.
 * 2. NavHost: Giống như "khung xe", nơi chứa và hiển thị các màn hình. Nó cần biết "tài xế" là ai (navController) và trạm bắt đầu.
 * 3. composable("tên_route"): Định nghĩa từng trạm dừng, khai báo Route nào tương ứng với Composable UI nào.
 */
@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.HOME // Trạm xuất phát mặc định khi mở app là HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Khai báo trạm dừng 1: Màn hình Home
        composable(Routes.HOME) {
            // Thay vì truyền navController, ta truyền một lambda function để gọi lệnh chuyển trang
            HomeScreen(onNavigateToLearning = {
                navController.navigate(Routes.LEARNING)
            })
        }

        // Khai báo trạm dừng 2: Màn hình Learning (đã tạo ở bước trước)
        composable(Routes.LEARNING) {
            // Không truyền navController nếu từ màn hình này không cần đi tiếp đi đâu nữa (hoặc chỉ bấm back)
            LearningScreen()
        }
    }
}
