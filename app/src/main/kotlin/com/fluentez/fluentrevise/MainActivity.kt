package com.fluentez.fluentrevise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.fluentez.fluentrevise.ui.navigation.AppNavGraph
import com.fluentez.fluentrevise.ui.theme.FluentReviseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FluentReviseTheme {
                // Gọi NavGraph (Bản đồ) để quản lý toàn bộ các màn hình
                AppNavGraph()
            }
        }
    }
}

