package com.example.task.presentation.connector

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task.ui.theme.secondaryPrimaryColor


@Composable
fun ChatScreen() {
    Text(
        text = "Chats",
        fontSize = 20.sp,
        color = secondaryPrimaryColor,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(end = 16.dp) // Add padding as needed
    )
}