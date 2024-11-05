package com.example.task


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.task.ui.theme.navyBlue
import com.example.task.ui.theme.extraSmallTitleStyle
import com.example.task.ui.theme.mediumTextStyle
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
                    DashBoardScreen(navController)
                }

            }


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
