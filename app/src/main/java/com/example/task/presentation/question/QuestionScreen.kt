package com.example.task.presentation.question

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalAirport
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.task.DashBoardViewModel
import com.example.task.OverlayScreen
import com.example.task.presentation.connector.ChatScreen
import com.example.task.presentation.connector.SuggestionsTab
import com.example.task.presentation.question.components.OralTab
import com.example.task.presentation.question.components.WritingTab
import com.example.task.ui.theme.NeutralWhite
import com.example.task.ui.theme.NeutralWhite2
import com.example.task.ui.theme.extraSmallTitleStyle
import com.example.task.ui.theme.lightPrimaryColor
import com.example.task.ui.theme.navyBlue
import com.example.task.ui.theme.primaryColor
import com.example.task.ui.theme.secondaryPrimaryColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    onNavigateToToolsScreen: () -> Unit
) {
    // State to hold the selected tab index
    var selectedTabIndex by remember { mutableStateOf(0) }
    val viewModel = hiltViewModel<DashBoardViewModel>()
    val questionState by viewModel.questionsState.collectAsState()

    // Tab titles
    val tabTitles = listOf("Suggestions", "Chat")
    Box(
        Modifier.fillMaxSize()
    ) {
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
                    0 -> WritingTab()
                    1 -> OralTab()
                }
            }
            if (questionState) {
                OverlayScreen(showTooltips = false,) {
                    // onNavigateToToolsScreen()
                    viewModel.updateWritingState(true)
                    viewModel.updateQuestionsState(false)
                    viewModel.updateBottomNavState(true)
                }
            }
        }

    }
}
