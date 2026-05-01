package com.fluentez.fluentrevise.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fluentez.fluentrevise.ui.screen.LearningScreen
import com.fluentez.fluentrevise.ui.screen.LoginScreen
import com.fluentez.fluentrevise.ui.screen.WelcomeScreen
import com.fluentez.fluentrevise.ui.screen.home.HomeScreen

/**
 * AppNavGraph là "bản đồ" quản lý việc chuyển trang (điều hướng) của toàn bộ App.
 *
 * Luồng điều hướng:
 *   WELCOME → LOGIN → HOME → LEARNING
 */
@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.WELCOME // Trạm xuất phát là Welcome Screen
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // Trạm 1: Welcome Screen
        composable(Routes.WELCOME) {
            WelcomeScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        // Trạm 2: Login Screen
        composable(Routes.LOGIN) {
            LoginScreen(
                onNavigateToHome = {
                    // popUpTo WELCOME để Back từ Home không quay về Login/Welcome
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.WELCOME) { inclusive = true }
                    }
                }
            )
        }

        // Trạm 3: Home Screen
        composable(Routes.HOME) {
            HomeScreen(onNavigateToLearning = {
                navController.navigate(Routes.LEARNING)
            })
        }

        // Trạm 4: Learning Screen
        composable(Routes.LEARNING) {
            LearningScreen()
        }
    }
}
