package com.example.task.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.task.R

// Load the Montserrat font
val montserratFontFamily = FontFamily(
    Font(R.font.montserrat_regular),  // Regular font
    Font(R.font.montserrat_bold, FontWeight.Bold) // Bold font
)

// Define your custom text style
val extraSmallTitleStyle = TextStyle(
    fontFamily = montserratFontFamily,
    fontSize = 10.sp, // Font size in sp
    fontWeight = FontWeight.Bold, // Font weight
    textAlign = TextAlign.Center, // Text alignment
    color = Color.Gray // Text color
)
val mediumTextStyle = TextStyle(
    fontFamily = montserratFontFamily,
    fontSize = 22.sp, // equivalent to 16px
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    color = primaryColor // You can set a color to match your design
)
