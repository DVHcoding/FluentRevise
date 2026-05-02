package com.fluentez.fluentrevise.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluentez.fluentrevise.R
import com.fluentez.fluentrevise.ui.components.AppButton
import com.fluentez.fluentrevise.ui.theme.AppBackground
import com.fluentez.fluentrevise.ui.theme.ButtonStartColor
import com.fluentez.fluentrevise.ui.theme.LogoFluentColor
import com.fluentez.fluentrevise.ui.theme.TextDark

// Màu cho Login Screen
private val LoginBlue = Color(0xFF3D5AFE)
private val FacebookBlue = Color(0xFF1877F2)
private val DividerGray = Color(0xFFBDBDBD)
private val FieldBackground = Color(0xFFF1F3F8)
private val LinkOrange = Color(0xFFF4A261)

@Composable
fun LoginScreen(onNavigateToHome: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(72.dp))

            // ── Title ─────────────────────────────────────────────
            Text(
                text = "Sign In",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = LoginBlue
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ── Subtitle với link ──────────────────────────────────
            val subtitleText = buildAnnotatedString {
                withStyle(SpanStyle(color = TextDark.copy(alpha = 0.65f), fontSize = 14.sp)) {
                    append("If you do not have an account, please visit the website ")
                }
                pushStringAnnotation(tag = "URL", annotation = "https://fluentez.com")
                withStyle(SpanStyle(color = LinkOrange, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)) {
                    append("fluentez.com")
                }
                pop()
                withStyle(SpanStyle(color = TextDark.copy(alpha = 0.65f), fontSize = 14.sp)) {
                    append(" to create one and use it.")
                }
            }
            ClickableText(
                text = subtitleText,
                style = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center, lineHeight = 20.sp),
                onClick = { /* TODO: open browser */ }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ── Social Buttons ────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Facebook Button
                OutlinedButton(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Facebook icon
                        Image(
                            painter = painterResource(id = R.drawable.ic_facebook),
                            contentDescription = "Facebook",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Facebook", color = TextDark, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                    }
                }

                // Google Button
                OutlinedButton(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Google icon
                        Image(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = "Google",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Google", color = TextDark, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── "Or" Divider ──────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = DividerGray.copy(alpha = 0.5f))
                Text(
                    text = "  Or  ",
                    color = DividerGray,
                    fontSize = 13.sp
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = DividerGray.copy(alpha = 0.5f))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── Email Field ───────────────────────────────────────
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text("Email", color = DividerGray, fontSize = 15.sp)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = FieldBackground,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = LoginBlue
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ── Password Field ────────────────────────────────────
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text("Password", color = DividerGray, fontSize = 15.sp)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = FieldBackground,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = LoginBlue
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None
                                       else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(
                                id = if (passwordVisible) R.drawable.ic_eye_open
                                     else R.drawable.ic_eye_closed
                            ),
                            contentDescription = if (passwordVisible) "Ẩn mật khẩu" else "Hiện mật khẩu",
                            tint = DividerGray,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(28.dp))

            // ── Log In Button ─────────────────────────────────────
            AppButton(
                text = "Log In",
                onClick = onNavigateToHome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp),
                backgroundColor = LoginBlue
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ── Sign Up Link ──────────────────────────────────────
            val signUpText = buildAnnotatedString {
                withStyle(SpanStyle(color = TextDark.copy(alpha = 0.6f), fontSize = 14.sp)) {
                    append("Don't have account? ")
                }
                pushStringAnnotation(tag = "SIGNUP", annotation = "signup")
                withStyle(SpanStyle(color = LoginBlue, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)) {
                    append("Sign Up")
                }
                pop()
            }
            ClickableText(
                text = signUpText,
                onClick = { /* TODO: navigate to sign up */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(onNavigateToHome = {})
}
