package com.fluentez.fluentrevise.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
    startDestination: String = Routes.WELCOME,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            fadeIn(animationSpec = tween(100)) +
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(100),
                )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(100)) +
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(100),
                )
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(100)) +
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(100),
                )
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(100)) +
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(100),
                )
        },
    ) {
        // Trạm 1: Welcome Screen
        composable(Routes.WELCOME) {
            WelcomeScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN)
                },
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
                },
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
