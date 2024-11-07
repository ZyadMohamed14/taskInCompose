package com.example.task.presentation.question

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task.Tooltip
import com.example.task.presentation.question.components.OralTab
import com.example.task.presentation.question.components.WritingTab
import com.example.task.ui.theme.primaryColor
import com.example.task.ui.theme.secondaryPrimaryColor
import com.pseudoankit.coachmark.UnifyCoachmark
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.overlay.DimOverlayEffect

enum class QuestionsKeys { E, CourseItem,FilterIcon }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    onNavigateToToolsScreen: () -> Unit
) {

    // State to hold the selected tab index
    var selectedTabIndex by remember { mutableStateOf(0) }

    Log.d("QuestionScreen", "selectedTabIndex $selectedTabIndex")

    var highLightCoures by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        highLightCoures = true

    }
    // Tab titles
    val tabTitles = listOf("Suggestions", "Chat")
    UnifyCoachmark(

        tooltip = { Tooltip(it) },
        overlayEffect = DimOverlayEffect(Color.Black.copy(alpha = .5f)),
        onOverlayClicked = {
          //  selectedTabIndex = 1
            highLightCoures = false
            OverlayClickEvent.DismissAll

        }
    ) {
        if (highLightCoures) {
            show(QuestionsKeys.CourseItem)
        }

        Scaffold(
                topBar = {
                    TopAppBar(
                        {
                            Text(
                                text = "Questions",
                                fontSize = 20.sp,
                                color = primaryColor,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(end = 16.dp) // Add padding as needed
                            )
                        },


                        )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    // Tab Row
                    TabRow(selectedTabIndex = selectedTabIndex,
                        indicator = { tabPositions ->
                            // Draw the indicator with a custom color
                            Box(
                                modifier = Modifier
                                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                                    .background(primaryColor) // Set your desired color here
                                    .height(2.dp) // Adjust the height as needed
                            )
                        }
                    ) {

                        tabTitles.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                text = {
                                    Text(
                                        text = title,
                                        fontSize = 20.sp,
                                        color = secondaryPrimaryColor,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(end = 16.dp) // Add padding as needed
                                    )
                                }
                            )
                        }
                    }

                    // Content based on the selected tab
                    when (selectedTabIndex) {
                        0 -> WritingTab(Modifier.fillMaxSize()){

                        }
                        1 -> OralTab()
                    }
                }

            }


    }

}
