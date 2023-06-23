package com.example.weatherapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.grey


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details")
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                color = MaterialTheme.colors.background
            ) {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {

    val enteries = remember {
        mutableStateListOf<Data>(
            Data(19, 24, 18, "Coimbatore", "India", "Mid Rain", getDrawable("Mid Rain")),
            Data(20, 21, -19, "Chennai", "India", "Fast Wind", getDrawable("Fast Wind")),
            Data(13, 16, 8, "Tokyo", "Japan", "Showers", getDrawable("Showers")),
            Data(25, 30, 20, "New York", "US", "Tornado", getDrawable("Tornado")),
            Data(20, 22, 18, "London", "UK", "Mid Rain", getDrawable("Mid Rain")),
            Data(23, 27, 19, "Paris", "France", "Fast Wind", getDrawable("Fast Wind")),
        )
    }

    val enteriesCopy: List<Data> = remember { enteries.map { it.copy() } }
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        addHomeScreen(navController, enteries, enteriesCopy)
        addDetailsScreen(navController, enteries)
    }
}

fun NavGraphBuilder.addHomeScreen(
    navController: NavController, enteries: SnapshotStateList<Data>, enteriesCopy: List<Data>
) {
    composable(Screen.Home.route) {
        HomeScreen(navController = navController, enteries = enteries, enteriesCopy = enteriesCopy)
    }
}

fun NavGraphBuilder.addDetailsScreen(
    navController: NavController, enteries: SnapshotStateList<Data>
) {
    composable(
        route = "${Screen.Details.route}/{index}",
        arguments = listOf(navArgument("index") { type = NavType.IntType })
    ) { entry ->
        val index = entry.arguments?.getInt("index")
        DetailsPage(navController = navController, index = index, enteries = enteries)
    }
}


//@Preview()
//@Composable
//fun PreviewHomePage(){
//    HomePage()
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewHomePageCardImage(){
//    HomePageCardImage()
//}


