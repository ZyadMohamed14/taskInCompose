package com.example.task

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun NavigationBarWithTooltip() {
    // Track the currently highlighted item index
    var highlightedIndex by remember { mutableStateOf(0) }
    val tooltipMessages = listOf(
        "Vous trouverez ici votre plan d'étude",
        "This is the search section"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                // Move to the next highlight when the screen is clicked
                highlightedIndex = (highlightedIndex + 1) % tooltipMessages.size
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        // Custom Navigation Bar
        CustomNavigationBar(
            highlightedIndex = highlightedIndex,
            onItemClick = { index ->
                highlightedIndex = index  // Move to the clicked item
            }
        )

        // Display the tooltip above the selected navigation item
        if (highlightedIndex < tooltipMessages.size) {
            Tooltip(
                message = tooltipMessages[highlightedIndex],
                highlightedIndex = highlightedIndex
            )
        }
    }
}

@Composable
fun Tooltip(message: String, highlightedIndex: Int) {
    // Using a Column to center the tooltip above the selected item
    Box(
        modifier = Modifier
            .padding(bottom = 75.dp) // Adjust for spacing above the nav bar
    ) {
        Text(
            text = message,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(Color.DarkGray, shape = MaterialTheme.shapes.medium)
                .padding(8.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun CustomNavigationBar(highlightedIndex: Int, onItemClick: (Int) -> Unit) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Gray
    ) {
        val items = listOf(
            "Accueil" to Icons.Default.Home,
            "Search" to Icons.Default.Search,
        )

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.second, contentDescription = item.first) },
                label = { Text(item.first) },
                selected = highlightedIndex == index,
                onClick = {
                    onItemClick(index) // Notify parent of item click
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = if (highlightedIndex == index) Color.Cyan else Color.Transparent
                )
            )
        }
    }
}







/*
@Composable
fun NonHelightWritingItem(
    isFirstItem: Boolean = false,
    isOverLayState: Boolean = false
){
    val shadowColor = Color.Black.copy(alpha = 0.2f) // Adjust the opacity here
    val backgroundColor = if (isFirstItem && isOverLayState) Color.White else Color.Transparent
    val shadowElevation =
        if (isFirstItem && isOverLayState) 8.dp else 0.dp // Change based on conditions

    Card(
        modifier = Modifier
            .padding(16.dp)
            .shadow(elevation = shadowElevation, shape = RoundedCornerShape(8.dp)), // Apply shadow here
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeutralWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        // Use Box to layer items
            // Content inside the card
            Column(
                modifier = Modifier
                    .padding(16.dp) // Add padding inside the card
                    .fillMaxWidth(), // Ensures the column takes full width
                verticalArrangement = Arrangement.spacedBy(8.dp) // Spacing between items
            ) {
                // First Row for the questions text
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween // Distribute space evenly
                ) {
                    Text(
                        text = "5 sur 10 Questions",
                        style = extraSmallTitleStyle.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .background(lightGreen)
                            .padding(4.dp)
                    )
                }

                // Second Row for the icon and text
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.travel), // Example filter icon
                        contentDescription = "icon",
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Voyage",
                        style = extraSmallTitleStyle.copy(
                            fontSize = 15.sp,
                        ),
                        modifier = Modifier.weight(1f) // Takes remaining space
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "progress 50",
                    style = extraSmallTitleStyle.copy(
                        color = Color.Gray
                    ),
                )
                Spacer(modifier = Modifier.width(4.dp))
                // Vertical progress bar
                CustomLinearProgressIndicator(progress = 0.5f)
            }

            // Overlay effect for non-first items


    }
}



































 */

/*
@HiltViewModel
class DashBoardViewModel @Inject constructor():ViewModel() {
    // Overlay state to show or hide the overlay
    private val _overlayState = MutableStateFlow(false)
    val overlayState: StateFlow<Boolean> get() = _overlayState

    // Index of the currently highlighted navigation item
    private val _highlightedIndex = MutableStateFlow(0)
    val highlightedIndex: StateFlow<Int> get() = _highlightedIndex

    // List of navigation items, could also be a predefined enum list in your code
    private val navigationItems = BottomNavItem.values()

    // Function to toggle overlay state
    fun toggleOverlay() {
        _overlayState.update { !it }
        if (_overlayState.value) {
            _highlightedIndex.value = 0 // Reset to the first item when overlay is activated
        }
    }

    // Function to advance to the next tooltip or hide overlay if at the end
    fun advanceTooltip() {
        if (_highlightedIndex.value < navigationItems.size - 1) {
            _highlightedIndex.update { it + 1 } // Move to the next item
        } else {
            _highlightedIndex.value = 0 // Reset index
            _overlayState.value = false // Hide overlay after the last item
        }
    }

    // for home screen tips
    private val _homeoverlayState = MutableStateFlow(false)
    val homeoverlayState: StateFlow<Boolean> get() = _homeoverlayState

}
 */