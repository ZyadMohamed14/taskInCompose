package com.example.task.presentation.home

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.task.BottomNavItem
import com.example.task.DashBoardViewModel
import com.example.task.OverlayScreen
import com.example.task.R
import com.example.task.data.local.SharedPrefHelper
import com.example.task.presentation.home.componenets.HomeAppBar
import com.example.task.ui.theme.NeutralGrey
import com.example.task.ui.theme.navyBlue
import com.example.task.ui.theme.primaryColor
import com.example.task.ui.theme.secondaryPrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, onNavigateToConnector: () -> Unit) {

    val lessons = getDummyLessons()
    val viewModel = hiltViewModel<DashBoardViewModel>()
    val homeoverlayState by viewModel.homeoverlayState.collectAsState()
    val backgroundColor = if(homeoverlayState)navyBlue else Color.White
    Box (Modifier.fillMaxSize().background(backgroundColor)){
        Scaffold(
            topBar = {
                TopAppBar(
                    {
                        Text(
                            text = "Home",
                            fontSize = 20.sp,
                            color = secondaryPrimaryColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 16.dp) // Add padding as needed
                        )
                    },
                    actions = {
                        IconButton(onClick = { /* Handle Favorite Click */ }) {
                            Icon(
                                painterResource(id = R.drawable.notification),
                                contentDescription = "Favorite",
                                tint = primaryColor
                            )
                        }

                    },

                    )
            }
        ) { innerPadding ->


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())

            ) {
                Column {

                    Row {
                        Text(
                            text = "Hi",
                            fontSize = 20.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 16.dp) // Add padding as needed
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "User Name",
                            fontSize = 20.sp,
                            color = secondaryPrimaryColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 16.dp) // Add padding as needed
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Study Plan",
                        fontSize = 20.sp,

                        color = secondaryPrimaryColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 16.dp) // Add padding as needed
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ConnectedLessonItems(lessons)

                }
            }
        }
        // Overlay Screen to cover entire content and bottom bar
        if (homeoverlayState) {
            OverlayScreen(showTooltips = false){
                onNavigateToConnector()
                viewModel.updateHomeOverlayState(false)

            }
        }

    }

}

@Composable
fun LessonItem(lesson: Lesson, isLastItem: Boolean,isOverlay:Boolean = false) {
    Column{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val outerCircleColor = if (lesson.isLocked) Color.Gray else secondaryPrimaryColor
            val innerCircleColor = if (lesson.isLocked) Color.Gray else secondaryPrimaryColor
            val titleColor = if (lesson.isLocked) Color.Gray else secondaryPrimaryColor
            val unitTextColor = if (lesson.isLocked) Color.Gray else secondaryPrimaryColor

            // Outer Circle for Lesson Number using Canvas
            Box(
                modifier = Modifier.size(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = outerCircleColor,
                        radius = size.minDimension / 2,
                        style = Stroke(width = 8.dp.toPx())
                    )
                }

                // Inner Circle for Unit Number using Canvas
                Box(
                    modifier = Modifier.size(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = innerCircleColor,
                            radius = size.minDimension / 2,
                            style = Stroke(width = 1.dp.toPx())
                        )
                    }

                    Text(
                        text = lesson.unitNumber.toString(),
                        color = innerCircleColor,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Column for Title
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = lesson.title,
                    color = titleColor,
                    fontSize = 16.sp
                )
                Text(
                    text = "Units: ${lesson.unitNumber}",
                    color = unitTextColor,
                    fontSize = 14.sp
                )
            }
        }

        // Draw the connecting line if it's not the last item
        if (!isLastItem) {

            Canvas(
                modifier = Modifier
                    .width(4.dp)
                    .height(50.dp) // Height of the line, adjust as needed
                    .padding(start = 65.dp) // Adjust the padding as needed
            ) {
                drawLine(
                    color = Color.Gray,
                    start = Offset(x = size.width / 2, y = 0f),
                    end = Offset(x = size.width / 2, y = size.height),
                    strokeWidth = 14.dp.toPx()
                )
            }
        }
    }
}

@Composable
fun ConnectedLessonItems(lessons: List<Lesson>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (index in lessons.indices) {
            LessonItem(lessons[index], isLastItem = index == lessons.lastIndex)
        }
    }
}





data class Lesson(
    val lessonNumber: Int,
    val unitNumber: Int,
    val title: String,
    val isLocked: Boolean = true
)
fun getDummyLessons(): List<Lesson> {
    return listOf(
        Lesson(1, 1, "Introduction to Jetpack Compose",isLocked = false),
        Lesson(2, 2, "Understanding State Management",),
        Lesson(3, 3, "Advanced Composable Functions"),
        Lesson(4, 4, "Working with Animations"),
        Lesson(5, 5, "Navigation in Jetpack Compose"),
        Lesson(6, 6, "State Hoisting"),
        Lesson(7, 7, "Theming and Styling"),
        Lesson(8, 8, "Integration with ViewModels")
    )
}



