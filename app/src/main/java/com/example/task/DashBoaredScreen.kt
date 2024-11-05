package com.example.task

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.task.data.local.SharedPrefHelper
import com.example.task.presentation.connector.ConnectorScreen
import com.example.task.presentation.home.HomeScreen
import com.example.task.presentation.question.QuestionScreen
import com.example.task.ui.theme.NeutralGrey
import com.example.task.ui.theme.navyBlue
import com.example.task.ui.theme.extraSmallTitleStyle
import com.example.task.ui.theme.primaryColor


@Composable
fun DashBoardScreen(
    navController: NavController
) {
    var currentScreen by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }

    val viewModel = hiltViewModel<DashBoardViewModel>()
    val currentIndex by viewModel.currentIndex.collectAsState()
    var overlayState by remember { mutableStateOf(true) }
    var showTooltips by remember { mutableStateOf(true) }
    val bottomNavState by viewModel.bottomNavState.collectAsState()
    Log.d("BottomNavigationBar", "bottomNavState: $bottomNavState")
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    selectedScreen = currentScreen,
                    onItemSelected = { selected ->
                        currentScreen = selected

                    },
                    isOverlayShown = bottomNavState
                )
            }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = Color.Transparent
            ) {
                // Render the current screen based on the selected item in the bottom navigation
                when (currentScreen) {
                    BottomNavItem.Home -> HomeScreen(navController = navController){
                        currentScreen = BottomNavItem.Connector // Update for navigation
                    }
                    BottomNavItem.Connector -> ConnectorScreen(){
                        currentScreen = BottomNavItem.Questions // Update for navigation
                    }
                    BottomNavItem.Questions -> QuestionScreen(){
                        currentScreen = BottomNavItem.Tools // Update for navigation
                    }
                    BottomNavItem.Tools -> ToolsScreen()
                    BottomNavItem.Profile -> ProfileScreen()
                }
            }
        }

        // Place OverlayScreen here to ensure it covers the whole screen including the BottomNavigationBar
        if (overlayState) {
            OverlayScreen(
                showTooltips = showTooltips,
            ){
                showTooltips = false
                overlayState = false
              //  viewModel.updateBottomNavState(true)

            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedScreen: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit,
    isOverlayShown: Boolean
) {

    Log.d("BottomNavigationBar", "isOverlayShown: $isOverlayShown")
    val selectedItemColor = primaryColor
    val unselectedItemColor = NeutralGrey
     var backgroundColor = remember {
         mutableStateOf(Color.White)
     }
    NavigationBar(
        containerColor = if (!isOverlayShown) navyBlue else Color.White
    ){
        BottomNavItem.entries.forEachIndexed { index, item ->
            val isSelectedItem = item == selectedScreen
          //  if (isSelectedItem ) backgroundColor.value = Color.White else Color.Transparent

            NavigationBarItem(
                modifier = Modifier.background( if (isSelectedItem) Color.White  else  Color.Unspecified ),
                icon = {
                    TooltipPopup(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            ,
                        requesterView = { modifier ->
                            Icon(
                                painter = painterResource(id = item.icon),
                                modifier = modifier.size(20.dp),
                                contentDescription = null
                            )
                        },
                        tooltipContent = {

                                Text(
                                    modifier = Modifier
                                        .background(navyBlue)
                                        .padding(horizontal = 12.dp)
                                        .padding(vertical = 8.dp),
                                    text = item.message,
                                    style = extraSmallTitleStyle.copy(color = Color.White)
                                )


                        }
                    )


                },
                label = {

                    Text(text = item.title,)
                },
                selected = isSelectedItem,
                onClick = { onItemSelected(item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = selectedItemColor,
                    unselectedIconColor = unselectedItemColor,
                    selectedTextColor = selectedItemColor,
                    unselectedTextColor = unselectedItemColor,
                    indicatorColor = Color.White
                )
            )
        }
    }
}

@Composable
fun ShowHintedNavBar(
    backgroundColor: Color,
    item: BottomNavItem
) {
    TooltipPopup(
        modifier = Modifier
            .padding(start = 8.dp)
            .background(backgroundColor),
        requesterView = { modifier ->
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    modifier = Modifier.size(20.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.title,
                    style = extraSmallTitleStyle,
                    modifier = Modifier.padding(10.dp)
                )
            }
        },
        tooltipContent = {
            Text(
                modifier = Modifier
                    .background(navyBlue)
                    .padding(horizontal = 12.dp)
                    .padding(vertical = 8.dp),
                text = item.message,
                style = extraSmallTitleStyle.copy(color = Color.White)
            )
        }
    )
}


enum class BottomNavItem(val title: String, @DrawableRes val icon: Int, val message: String = "") {
    Home("Home", R.drawable.home, "This is Home"),
    Connector("Connector", R.drawable.connector, "This is Connector"),
    Questions("Question", R.drawable.question, "This is Question"),
    Tools("Tools", R.drawable.tool, "This is Tools"),
    Profile("Profile", R.drawable.profile, "This is Profile")
}






@Composable
fun ToolsScreen() {
    Text("Account Screen", style = MaterialTheme.typography.titleLarge)
}

@Composable
fun ProfileScreen() {
    Text("Account Screen", style = MaterialTheme.typography.titleLarge)
}





































