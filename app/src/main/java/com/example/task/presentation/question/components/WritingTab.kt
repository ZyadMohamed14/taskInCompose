package com.example.task.presentation.question.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.LocalAirport
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.task.DashBoardViewModel
import com.example.task.OverlayScreen
import com.example.task.R
import com.example.task.TooltipPopup
import com.example.task.ui.theme.NeutralWhite
import com.example.task.ui.theme.NeutralWhite2
import com.example.task.ui.theme.black40
import com.example.task.ui.theme.extraSmallTitleStyle
import com.example.task.ui.theme.lightGreen
import com.example.task.ui.theme.lightPrimaryColor
import com.example.task.ui.theme.navyBlue
import com.example.task.ui.theme.primaryColor

@Composable
fun WritingTab() {
    val viewModel = hiltViewModel<DashBoardViewModel>()
    val writingState by viewModel.writingState.collectAsState()
    val shadowColor = NeutralWhite2 // Adjust the opacity here
    val foregroundColor = if (writingState) shadowColor else Color.White

    LazyVerticalGrid(
        // modifier = Modifier.background(Color.Red),
        columns = GridCells.Fixed(2), // Number of columns
        contentPadding = PaddingValues(16.dp) // Optional padding around the grid
    ) {
        items(10) {
            Box (modifier = Modifier.padding(8.dp)){
                WritingItemWithToolTip(){

                }
            }

        }
    }


}

@Composable
fun WritingItemWithToolTip(
    onClickItem: () -> Unit
) {
    TooltipPopup(
        modifier = Modifier
            .padding(start = 8.dp)
        ,
        requesterView = { modifier ->
            Box(modifier = modifier) {
                WritingItem(modifier)
            }

        },
        tooltipContent = {

            Text(
                modifier = Modifier
                    .background(navyBlue)
                    .padding(horizontal = 12.dp)
                    .padding(vertical = 8.dp),
                text = "this is a Question Number 1",
                style = extraSmallTitleStyle.copy(color = Color.White)
            )


        }
    )



}
@Composable
fun WritingItem(
    modifier: Modifier= Modifier
){
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(modifier = modifier.fillMaxSize()) { // Use Box to layer items
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


        }
    }
}


@Composable
fun CustomLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    progressColor: Color = Color(0xFF21B6B6),
    backgroundColor: Color = Color.Gray,
    clipShape: Shape = RoundedCornerShape(16.dp)
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(backgroundColor)
            .height(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
    }
}


/*
@Composable
fun WritingItem(
    isFirstItem: Boolean = false,
    isOverLayState: Boolean = false
) {
    val backgroundColor = if (isFirstItem && isOverLayState) Color.White  else NeutralWhite

    Card(
        modifier = Modifier.padding(16.dp), // Padding around the card
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        // Using Column to stack elements vertically
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
                        color =  Color.Black,
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
                    tint =  if(!isFirstItem && isOverLayState) primaryColor else Color.Unspecified

                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Voyage",
                    style = extraSmallTitleStyle.copy(fontSize = 15.sp,
                        color =  if(!isFirstItem && isOverLayState) primaryColor else Color.Gray),
                    modifier = Modifier.weight(1f) // Takes remaining space
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "progress 50",
                style = extraSmallTitleStyle.copy(
                    color = if(!isFirstItem && isOverLayState) primaryColor else Color.Gray
                ),


            )
            Spacer(modifier = Modifier.width(4.dp))
            // Vertical progress bar
            CustomLinearProgressIndicator(progress = 0.5f)
        }
    }
}
@Composable
 */