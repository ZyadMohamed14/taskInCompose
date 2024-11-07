package com.example.task


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialShapes.Companion.Arrow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.task.ui.theme.extraSmallTitleStyle
import com.example.task.ui.theme.mediumTextStyle
import com.example.task.ui.theme.navyBlue
import com.pseudoankit.coachmark.LocalCoachMarkScope
import com.pseudoankit.coachmark.UnifyCoachmark
import com.pseudoankit.coachmark.model.HighlightedViewConfig
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.overlay.DimOverlayEffect
import com.pseudoankit.coachmark.scope.enableCoachMark
import com.pseudoankit.coachmark.shape.Arrow
import com.pseudoankit.coachmark.shape.Balloon
import com.skydoves.balloon.Balloon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()


            NavHost(navController = navController, startDestination = "dashboard") {
                composable("dashboard") {
                    DashBoardScreen2(navController)
                }

            }

          //  RevealSample()

           // UnifyCoachmarkDemo()
        }
    }
}



@Composable
fun OverlayScreen(
    showTooltips: Boolean = true, backgroundColor: Color = navyBlue, onDismiss: () -> Unit,

    ) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor) // Background color
            .clickable { onDismiss() }, // Handle tap to dismiss
        contentAlignment = Alignment.Center // Center the content
    ) {
        if (showTooltips) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center, // Center vertically
                horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
            ) {
                Text(
                    text = "Welcome to: How to use and enjoy \n Examate",
                    style = extraSmallTitleStyle.copy(fontSize = 18.sp),

                    modifier = Modifier.padding(bottom = 8.dp) // Add some space between texts
                )
                Text(
                    text = "Tap anywhere on the screen to \n continue",
                    style = mediumTextStyle
                )
            }
        }

    }
}
