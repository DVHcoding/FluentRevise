package com.fluentez.fluentrevise.ui.screen
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluentez.fluentrevise.R
import com.fluentez.fluentrevise.ui.components.AppButton
import com.fluentez.fluentrevise.ui.theme.AppBackground
import com.fluentez.fluentrevise.ui.theme.ButtonStartColor
import com.fluentez.fluentrevise.ui.theme.TextDark
@Composable
fun WelcomeScreen(onNavigateToLogin: () -> Unit) {
    // Hiệu ứng fade-in khi màn hình mở ra
    var visible by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 700),
        label = "fade_in"
    )
    LaunchedEffect(Unit) { visible = true }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .alpha(alpha),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            // ── Illustration (đã bao gồm logo bên trong ảnh) ──────
            Image(
                painter = painterResource(id = R.drawable.img_welcome_illustration),
                contentDescription = "Welcome Illustration",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            // ── Text Content ──────────────────────────────────────
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "WELCOME",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextDark,
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "FluentRevise is an application that helps even the busiest people learn vocabulary",
                    fontSize = 15.sp,
                    color = TextDark.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            // ── START NOW Button ──────────────────────────────────
            AppButton(
                text = "START NOW",
                onClick = onNavigateToLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp),
                backgroundColor = ButtonStartColor
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(onNavigateToLogin = {})
}