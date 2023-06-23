package com.example.weatherapp

import android.annotation.SuppressLint
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.ui.theme.grey


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePageCard(
    index: Int,
    enteries: SnapshotStateList<Data>,
    navController: NavController,
    @DrawableRes drawable: Int,
    temp: Int,
    high: Int,
    low: Int,
    city: String,
    country: String,
    weather: String,
    modifier: Modifier = Modifier

) {
    val cardSize = dimensionResource(R.dimen.CardSize)
    val smallSpacing = dimensionResource(R.dimen.smallSpacing)
    val mediumHeight = dimensionResource(R.dimen.mediumHeight)
    val dimen10 = dimensionResource(R.dimen.dimen10)
    val dimen30 = dimensionResource(R.dimen.dimen30)
    val dimen24 = dimensionResource(R.dimen.dimen24)


    BoxWithConstraints(
        modifier = modifier.height(200.dp)
    ) {
        Box(
            modifier = Modifier.combinedClickable(
                onClick = {
                    navController.navigate("${Screen.Details.route}/$index")
                },
                onLongClick = {
                    enteries.removeAt(index)
                }
            ),
            contentAlignment = Alignment.TopCenter,
        ) {
            Image(
                painter = painterResource(R.drawable.rectangle_5),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.height(cardSize)
            )
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = smallSpacing)
                    .height(mediumHeight)
                    .offset(y = smallSpacing)
            ) {
                Text(
                    text = "$temp°",
                    fontWeight = FontWeight.W400,
                    fontSize = 64.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = dimen24)
                )
                Text(
                    "H:$high° L:$low°\n ",
                    fontWeight = FontWeight.W400,
                    fontSize = 17.sp,
                    color = grey,
                    modifier = Modifier.padding(horizontal = dimen24)
                )
                Text(
                    "$city, $country",
                    fontWeight = FontWeight.W400,
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = dimen10)
                        .paddingFromBaseline(top = dimen30)
                )
            }
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                modifier = Modifier
                    .height(mediumHeight)
                    .width(mediumHeight)
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
    val filteredEnteries = enteries.filter { entry ->
        entry.city.contains(searchQuery, ignoreCase = true) || entry.country.contains(
            searchQuery,
            ignoreCase = true
        ) || entry.weather.contains(searchQuery, ignoreCase = true)
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

private fun filterEntries(
    enteries: List<Data>,
    searchQuery: String
): List<Data> {
    return enteries.filter { entry ->
        entry.city.contains(searchQuery, ignoreCase = true) ||
                entry.country.contains(searchQuery, ignoreCase = true) ||
                entry.weather.contains(searchQuery, ignoreCase = true)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(
    navController: NavController,
    enteries: SnapshotStateList<Data>,
    enteriesCopy: List<Data>,
    modifier: Modifier = Modifier
) {
    val updatedEnteries = mutableStateOf(enteries)
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier
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
            searchQuery = searchQuery, onSearchQueryChange = { query ->
                searchQuery = query
            }, Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(12.dp))
        HomePage(searchQuery, navController, enteries)
        Spacer(Modifier.height(16.dp))
    }
}