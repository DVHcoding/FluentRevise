package com.fluentez.fluentrevise.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Màn hình LearningScreen là một ví dụ tổng hợp về Jetpack Compose.
 * Bạn có thể xem code này để hiểu:
 * 1. Cách import và sử dụng Material3.
 * 2. Quản lý trạng thái (State) với remember và mutableStateListOf.
 * 3. Sử dụng các Layout cơ bản: Column, Row, Box.
 * 4. Sử dụng Modifier để căn chỉnh, tạo padding, bo góc.
 * 5. Tái sử dụng component (Tạo một Composable nhỏ và gọi lại nhiều lần).
 * 6. LazyColumn (tương đương RecyclerView) để hiển thị danh sách.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningScreen() {
    // 1. QUẢN LÝ TRẠNG THÁI (STATE)
    // Dùng remember để giữ giá trị khi UI vẽ lại (recomposition)
    var textInput by remember { mutableStateOf("") }
    
    // Dùng mutableStateListOf để quản lý một danh sách động (thêm/xóa phần tử sẽ cập nhật UI)
    val taskList = remember { mutableStateListOf("Học Kotlin", "Thực hành Jetpack Compose", "Làm một app nhỏ") }

    // Scaffold là cấu trúc cơ bản của màn hình Material Design, có topBar, floatingActionButton...
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ứng dụng Học tập", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (textInput.isNotBlank()) {
                    taskList.add(textInput)
                    textInput = "" // Reset ô nhập liệu
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "Thêm công việc")
            }
        }
    ) { innerPadding ->
        // 2. LAYOUT: Column sắp xếp các thành phần theo chiều dọc
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // padding từ Scaffold (tránh bị topBar đè)
                .padding(16.dp), // padding thêm cho nội dung
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            // Text cơ bản
            Text(
                text = "Quản lý công việc",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(16.dp)) // Tạo khoảng trống

            // 3. NHẬP LIỆU: OutlinedTextField
            OutlinedTextField(
                value = textInput,
                onValueChange = { newValue -> textInput = newValue },
                label = { Text("Nhập công việc mới...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 4. HIỂN THỊ DANH SÁCH: LazyColumn (tương tự RecyclerView)
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(taskList) { task ->
                    // 5. TÁI SỬ DỤNG COMPONENT
                    // Gọi hàm TaskItemComponent (được định nghĩa bên dưới)
                    TaskItemComponent(
                        taskName = task,
                        onDeleteClick = {
                            taskList.remove(task)
                        }
                    )
                }
            }
        }
    }
}

/**
 * 5. TÁI SỬ DỤNG COMPONENT (REUSABLE COMPONENT)
 * Việc chia nhỏ UI thành các hàm @Composable giúp code dễ đọc, dễ bảo trì và dùng lại được.
 */
@Composable
fun TaskItemComponent(taskName: String, onDeleteClick: () -> Unit) {
    // Card để tạo một thẻ có đổ bóng và bo góc
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp), // Khoảng cách giữa các thẻ
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        // Row sắp xếp các thành phần theo chiều ngang
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically, // Căn giữa theo chiều dọc
            horizontalArrangement = Arrangement.SpaceBetween // Đẩy 2 phần tử ra 2 mép
        ) {
            // Hiển thị tên công việc
            Text(
                text = taskName,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f) // Cho Text chiếm không gian còn lại
            )

            // Nút xóa
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Xóa",
                    tint = Color.Red
                )
            }
        }
    }
}

/**
 * PREVIEW
 * Hàm này giúp bạn xem trước UI ngay trong Android Studio mà không cần chạy máy ảo.
 */
@Preview(showBackground = true)
@Composable
fun LearningScreenPreview() {
    MaterialTheme { // Cần bọc trong MaterialTheme để màu sắc hiển thị đúng
        LearningScreen()
    }
}
