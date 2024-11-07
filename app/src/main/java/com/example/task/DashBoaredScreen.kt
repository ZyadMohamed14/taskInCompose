package com.example.task

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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

import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.task.presentation.connector.ConnectorScreen
import com.example.task.presentation.home.HomeScreen
import com.example.task.presentation.question.QuestionScreen
import com.example.task.ui.theme.NeutralGrey
import com.example.task.ui.theme.navyBlue
import com.example.task.ui.theme.extraSmallTitleStyle
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
import com.pseudoankit.coachmark.util.CoachMarkKey


@Composable
fun DashBoardScreen2(
    navController: NavController
) {

    var currentScreen by remember { mutableStateOf(BottomNavItem2.Home) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var overlayState by remember { mutableStateOf(true) }
    var showTooltips by remember { mutableStateOf(true) }
    val screens = remember {
        listOf(
            BottomNavItem2.Home,
            BottomNavItem2.Connector,
            BottomNavItem2.Questions,
            BottomNavItem2.Tools,
            BottomNavItem2.Profile
        )
    }

    UnifyCoachmark(

        tooltip = { Tooltip(it) },
        overlayEffect = DimOverlayEffect(Color.Black.copy(alpha = .5f)),
        onOverlayClicked = {
            if(currentIndex<2){
                Log.d("TFizeoAG", "DashBoardScreen2: $currentIndex")
                currentIndex++
                currentScreen = screens[currentIndex]
                OverlayClickEvent.GoNext
            }else{
                OverlayClickEvent.DismissAll
            }



        }
    ){

        Box(modifier = Modifier.fillMaxSize()){


            Scaffold(
                bottomBar = {
                    BottomNavigationBar2(
                        selectedScreen = screens[currentIndex],
                        onItemSelected = { selected ->
                            currentScreen = selected

                        }
                    )
                }
            ) { paddingValues ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .clickable {
                            show(*Keys.entries.toTypedArray())
                            //  currentIndex = (currentIndex + 1) % screens.size
                            //  currentScreen = screens[currentIndex] // Sync currentScreen with currentIndex

                        },
                    color = Color.Transparent
                ) {
                    // Render the current screen based on the selected item in the bottom navigation
                    when (currentScreen) {
                        BottomNavItem2.Home -> HomeScreen(navController){
                            currentScreen = BottomNavItem2.Connector
                        }
                        BottomNavItem2.Connector -> ConnectorScreen(){
                            currentScreen = BottomNavItem2.Questions
                        }
                        BottomNavItem2.Questions -> QuestionScreen(){}
                        BottomNavItem2.Tools -> ToolsScreen()
                        BottomNavItem2.Profile -> ProfileScreen()
                    }
                }
            }


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

}

@Composable
fun ProfileScreen() {
    Text(text = "Tools Screen")
}

@Composable
fun ToolsScreen() {
    Text(text = "Tools Screen")
}


@Composable
fun BottomNavigationBar2(
    selectedScreen: BottomNavItem2,
    onItemSelected: (BottomNavItem2) -> Unit,
    // coachMarkScope: CoachMarkScope
) {
    val selectedItemColor = primaryColor
    val unselectedItemColor = NeutralGrey

    NavigationBar(
        containerColor = Color.White,
    ) {
        BottomNavItem2.entries.forEach { item ->
            val isSelectedItem = item == selectedScreen

            NavigationBarItem(
                icon = {
                    Column(
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
                selected = isSelectedItem,
                onClick = { onItemSelected(item) },
                modifier = Modifier.enableCoachMark(
                    key = item.toCoachMarkKey(),
                    toolTipPlacement = ToolTipPlacement.Top,
                    highlightedViewConfig = HighlightedViewConfig(
                        shape = HighlightedViewConfig.Shape.Rect(8.dp),
                        padding = PaddingValues(4.dp)
                    ),
                    coachMarkScope =  LocalCoachMarkScope.current,
                    tooltip = {
                        Balloon(arrow = Arrow.Bottom()) {
                            Text(text = item.message, color = Color.White)
                        }
                    }
                ),
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

// Extension function to map each BottomNavItem2 to a CoachMarkKey
fun BottomNavItem2.toCoachMarkKey(): CoachMarkKey = when (this) {
    BottomNavItem2.Home -> Keys.Text1
    BottomNavItem2.Connector -> Keys.Text2
    BottomNavItem2.Questions -> Keys.TextStart
    BottomNavItem2.Tools -> Keys.TextBottom
    BottomNavItem2.Profile -> Keys.TextTop
}


enum class BottomNavItem2(val title: String, @DrawableRes val icon: Int, val message: String = "") {
    Home("Home", R.drawable.home, "This is Home"),
    Connector("Connector", R.drawable.connector, "This is Connector"),
    Questions("Question", R.drawable.question, "This is Question"),
    Tools("Tools", R.drawable.tool, "This is Tools"),
    Profile("Profile", R.drawable.profile, "This is Profile");


}
































