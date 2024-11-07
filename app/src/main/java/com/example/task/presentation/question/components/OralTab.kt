package com.example.task.presentation.question.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.task.Tooltip
import com.example.task.presentation.question.QuestionsKeys
import com.example.task.ui.theme.primaryColor
import com.pseudoankit.coachmark.LocalCoachMarkScope
import com.pseudoankit.coachmark.UnifyCoachmark
import com.pseudoankit.coachmark.model.HighlightedViewConfig
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.overlay.DimOverlayEffect
import com.pseudoankit.coachmark.scope.enableCoachMark
import com.pseudoankit.coachmark.shape.Arrow
import com.pseudoankit.coachmark.shape.Balloon


@Composable
fun OralTab(){
    var highLightFilterIcon by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        highLightFilterIcon = true

    }
    UnifyCoachmark(

        tooltip = { Tooltip(it) },
        overlayEffect = DimOverlayEffect(Color.Black.copy(alpha = .5f)),
        onOverlayClicked = {
            OverlayClickEvent.DismissAll

        }
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth().clickable {
                    highLightFilterIcon =false
                }
        ) {
            if (highLightFilterIcon) {
                show(QuestionsKeys.FilterIcon)
            }
            // Button with Filter Icon
            Button(
                onClick = { /* Handle filter action */ },
                modifier = Modifier.background(primaryColor).enableCoachMark(
                    key = QuestionsKeys.FilterIcon,
                    toolTipPlacement = ToolTipPlacement.Top,
                    highlightedViewConfig = HighlightedViewConfig(
                        shape = HighlightedViewConfig.Shape.Rect(8.dp),
                        padding = PaddingValues(start = 14.dp, bottom = 10.dp,top = 30.dp,end = 14.dp)
                    ),
                    coachMarkScope = LocalCoachMarkScope.current,
                    tooltip = {
                        Balloon(arrow = Arrow.Bottom()) {
                            Text(text = "Filter Each Task", color = Color.White)
                        }
                    }
                ),

                ) {
                Icon(
                    imageVector = Icons.Default.FilterList, // Filter Icon from Icons library
                    contentDescription = "Filter",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Filter", color = Color.White )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Space between the button and the row

            LazyColumn {
                items(10) {
                    TaskCard()
                }
            }

        }
    }

}
@Composable
fun TaskCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), // Add padding for the Card
       elevation = CardDefaults.cardElevation()

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Row with "Task" and "Options" icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Task", // Text for task

                )
                IconButton(onClick = { /* Handle options action */ }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert, // Options icon
                        contentDescription = "Options",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp)) // Space between the row and description

            // Text description under the row
            Text(
                text = "This is a dummy description. The content here describes something important.",

                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Another line of the description.",

                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "This is the third line of the description.",

            )

            Spacer(modifier = Modifier.height(16.dp)) // Space between the description and the bottom row

            // Row with "11 questions" and "13 May 2024"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "11 Questions", // Text for questions

                )
                Text(
                    text = "13 May 2024", // Date text

                )
            }
        }
    }
}
