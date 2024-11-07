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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task.R
import com.example.task.presentation.question.QuestionsKeys
import com.example.task.ui.theme.extraSmallTitleStyle
import com.example.task.ui.theme.lightGreen
import com.pseudoankit.coachmark.LocalCoachMarkScope
import com.pseudoankit.coachmark.model.HighlightedViewConfig
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.scope.enableCoachMark
import com.pseudoankit.coachmark.shape.Arrow
import com.pseudoankit.coachmark.shape.Balloon

@Composable
fun WritingTab(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,

) {


        LazyVerticalGrid(
            modifier = modifier.clickable {
                           onItemClick()
            },
            columns = GridCells.Fixed(2), // Number of columns
        ) {

            items(10) { index ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    if (index == 0) {
                        // Show the coach mark on the first item
                        WritingItem(
                            modifier = Modifier.enableCoachMark(
                                key = QuestionsKeys.CourseItem,
                                toolTipPlacement = ToolTipPlacement.Top,
                                highlightedViewConfig = HighlightedViewConfig(
                                    shape = HighlightedViewConfig.Shape.Rect(8.dp),
                                    padding = PaddingValues(start = 14.dp, bottom = 10.dp,top = 30.dp,end = 14.dp)
                                ),
                                coachMarkScope = LocalCoachMarkScope.current,
                                tooltip = {
                                    Balloon(arrow = Arrow.Bottom()) {
                                        Text(text = "Quiz", color = Color.White)
                                    }
                                }
                            )
                        )
                    } else {
                        WritingItem()
                    }
                }
            }
        }

}




@Composable
fun WritingItem(
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp) // Explicit height for consistent size
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth() // Keeps the Box within the card's width
                .padding(8.dp) // Padding for inner content
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), // Padding inside the card
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // First Row for the questions text
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
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
                        painter = painterResource(id = R.drawable.travel),
                        contentDescription = "icon",
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Voyage",
                        style = extraSmallTitleStyle.copy(
                            fontSize = 15.sp,
                        ),
                        modifier = Modifier.weight(1f)
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

 */