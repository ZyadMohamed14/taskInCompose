package com.example.task.presentation.connector

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.task.DashBoardViewModel
import com.example.task.OverlayScreen
import com.example.task.ui.theme.extraSmallTitleStyle
import com.example.task.ui.theme.lightPrimaryColor
import com.example.task.ui.theme.navyBlue
import com.example.task.ui.theme.primaryColor
import com.example.task.ui.theme.secondaryPrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConnectorScreen(
    onNavigateToQuestionScreen: () -> Unit
) {
    // State to hold the selected tab index
    var selectedTabIndex by remember { mutableStateOf(0) }
    val viewModel = hiltViewModel<DashBoardViewModel>()
    val connectionState by viewModel.connectorState.collectAsState()
    val backgroundColor = if (connectionState) navyBlue else Color.White
    // Tab titles
    val tabTitles = listOf("Suggestions", "Chat")
    Box(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    {
                        Text(
                            text = "Connects",
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
                    0 -> SuggestionsTab()
                    1 -> ChatScreen()
                }
            }
        }
        if (connectionState) {
            OverlayScreen(showTooltips = false) {
                onNavigateToQuestionScreen()
                viewModel.updateConnectorState(false)
            }
        }
    }
}

@Composable
fun SuggestionsTab(

) {


    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically // Aligns items vertically to the center
    ) {
        Text(
            text = "suggests study Partners",
            fontSize = 17.sp,
            color = secondaryPrimaryColor,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.weight(1f)) // Pushes the icon to the end

        IconButton(onClick = { /* Handle filter action */ }) {
            Icon(
                imageVector = Icons.Default.FilterList, // Use the built-in Material 3 Filter icon
                contentDescription = "Filter",
                tint = primaryColor
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    LazyColumn {
        items(10) {
            LanguageProfileCard()
        }
    }
}

data class LanguageProfile(
    val name: String,
    val lastSeen: String,
    val languages: List<String>,
    val location: String,
    val gender: String,
    val age: Int,
    val birthday: String
)

@Composable
fun LanguageProfileCard() {

    Card(
        modifier = Modifier.padding(16.dp), // Add padding around the card if needed
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation =CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Internal padding to keep content away from card edges
        ) {

            Row (modifier = Modifier.padding(8.dp)) {
                Box(
                    modifier = Modifier.size(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = secondaryPrimaryColor,
                            radius = size.minDimension / 2
                        )
                    }

                    Text(
                        text = "RS",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = "Reem Sayed",
                        fontSize = 20.sp,
                        color = secondaryPrimaryColor,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Last seen 10 minutes ago",
                        style = extraSmallTitleStyle
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        Text(
                            text = "English",
                            style = extraSmallTitleStyle.copy(
                                color = Color.Black,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.background(lightPrimaryColor).padding(4.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Arabic",
                            style = extraSmallTitleStyle.copy(
                                color = Color.Black,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.background(lightPrimaryColor).padding(4.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "French",
                            style = extraSmallTitleStyle.copy(
                                color = Color.Black,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.background(lightPrimaryColor).padding(4.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Targeting B1",
                    modifier = Modifier
                        .background(primaryColor, shape = RoundedCornerShape(4.dp))
                        .padding(4.dp),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, start = 8.dp, end = 8.dp)
            ) {
                InfoItem(icon = Icons.Default.LocationOn, text = "Egypt", modifier = Modifier.weight(1f))
                InfoItem(icon = Icons.Default.Person, text = "Female", modifier = Modifier.weight(1f))
                InfoItem(icon = Icons.Default.Cake, text = "26", modifier = Modifier.weight(1f))
                InfoItem(icon = Icons.Default.DateRange, text = "21 June 1997", modifier = Modifier.weight(1f))
            }
        }
    }
}


@Composable
fun InfoItem(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "$text Icon",
            modifier = Modifier.size(16.dp),
            tint = Color.Gray // Adjust the tint as desired
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text,style = extraSmallTitleStyle)
    }
}












