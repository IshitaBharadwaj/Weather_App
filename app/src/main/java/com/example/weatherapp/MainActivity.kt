package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.theme.grey

//import com.example.weatherapp.ui.theme.LightGrey
//import com.example.weatherapp.ui.theme.WeatherAppTheme

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
            Data(19,24,18,"Coimbatore","India","Mid Rain", getDrawable("Mid Rain")),
            Data(20,21,-19,"Chennai","India","Fast Wind", getDrawable("Fast Wind")),
            Data(13,16,8,"Tokyo","Japan","Showers", getDrawable("Showers")),
            Data(25,30,20,"New York","US","Tornado", getDrawable("Tornado")),
            Data(20,22,18,"London","UK","Mid Rain", getDrawable("Mid Rain")),
            Data(23,27,19,"Paris","France","Fast Wind", getDrawable("Fast Wind")),
        )
    }

//    var enteriesCopy: List<Data> = enteries.map { it.copy() }
    val enteriesCopy: List<Data> = remember { enteries.map { it.copy() } }
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        addHomeScreen(navController, enteries,enteriesCopy)
        addDetailsScreen(navController, enteries)
    }
}

fun NavGraphBuilder.addHomeScreen(navController: NavController, enteries: SnapshotStateList<Data>, enteriesCopy: List<Data>) {
    composable(Screen.Home.route) {
        HomeScreen(navController = navController, enteries = enteries,enteriesCopy = enteriesCopy)
    }
}

fun NavGraphBuilder.addDetailsScreen(navController: NavController,enteries: SnapshotStateList<Data> ) {
    composable(
        route = "${Screen.Details.route}/{index}",
        arguments = listOf(
            navArgument("index") { type = NavType.IntType }
        )
    ) { entry ->
        val index = entry.arguments?.getInt("index")
        DetailsPage(navController = navController, index = index, enteries = enteries)
    }
}

@Composable
fun HomePageCardImage(){
        val painter = painterResource(id = R.drawable.rectangle_5)

        Image(
            painter = painter,
            contentDescription = null,
        )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePageCard(
    index :Int,
    enteries: SnapshotStateList<Data>,
    navController: NavController,
    @DrawableRes drawable : Int,
    temp : Int,
    high : Int,
    low : Int,
    city : String,
    country : String,
    weather : String,
    modifier: Modifier = Modifier

){
    BoxWithConstraints(
        modifier = modifier.height(200.dp)
    ) {
        Box(
            modifier = Modifier.combinedClickable (
                onClick = {
                    navController.navigate("${Screen.Details.route}/$index")
                },
                onLongClick = {
                    enteries.removeAt(index)
                }
            ),
            contentAlignment = Alignment.TopCenter,
        )
        {
            Image(
                painter = painterResource(R.drawable.rectangle_5),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.height(200.dp)
            )
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(150.dp)
                    .offset(y = 20.dp)
            ) {
                Text(
                    text = "$temp°",
                    fontWeight = FontWeight.W400,
                    fontSize = 64.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Text(
                    "H:$high° L:$low°\n ",
                    fontWeight = FontWeight.W400,
                    fontSize = 17.sp,
                    color = grey,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Text(
                    "$city, $country",
                    fontWeight = FontWeight.W400,
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .paddingFromBaseline(top = 30.dp)
                )
            }
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .align(Alignment.TopEnd)
            )
            Text(
                text = "$weather",
                fontWeight = FontWeight.W400,
                fontSize = 15.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .align(Alignment.BottomEnd)
                    .padding(end = 40.dp, bottom = 40.dp)
            )
        }
    }
}



@Composable
fun HomePage(
    searchQuery: String,
    navController: NavController,
    enteries: SnapshotStateList<Data>,
    modifier: Modifier = Modifier
) {
    var filteredEnteries = enteries.filter { entry ->
        entry.city.contains(searchQuery, ignoreCase = true)||
                entry.country.contains(searchQuery, ignoreCase = true)||
                entry.weather.contains(searchQuery, ignoreCase = true)
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),

        ) {

        itemsIndexed(filteredEnteries) { index, item ->
            HomePageCard(
                index,
                enteries = enteries,
                navController = navController,
                drawable = item.drawable,
                temp = item.temp,
                high = item.high,
                low = item.low,
                city = item.city,
                country = item.country,
                weather = item.weather,
            )
        }
    }
}


@Composable
fun HomeScreen(navController: NavController,
               enteries:  SnapshotStateList<Data>,
               enteriesCopy: List<Data>,
               modifier: Modifier = Modifier)
{
    var updatedEnteries = mutableStateOf(enteries)
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier
//            .padding(vertical = 16.dp)
            .fillMaxHeight()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF2E335A), Color(0xFF1C1B33)),
                    start = Offset(97.5f, 0f),
                    end = Offset(292.5f, 0f)
                )
            )

    ) {
        Spacer(Modifier.height(16.dp))
        Header(
            enteries = updatedEnteries.value,
            enteriesCopy = enteriesCopy,

        )
        SearchBar(
//            enteries = enteries,
            searchQuery = searchQuery,
            onSearchQueryChange = { query ->
                searchQuery = query
            },
            Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(12.dp))
        HomePage(searchQuery,navController, enteries)
        Spacer(Modifier.height(16.dp))
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


