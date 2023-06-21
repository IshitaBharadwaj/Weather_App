package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//import com.example.weatherapp.ui.theme.LightGrey
//import com.example.weatherapp.ui.theme.WeatherAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DetailsPage()
                }
//            }
        }
    }
}


@Composable
fun HomePageCardImage(){
        val painter = painterResource(id = R.drawable.rectangle_5)

        Image(
            painter = painter,
            contentDescription = null,
//            modifier = Modifier.fillMaxSize()
        )
}


@Composable
fun HomePageCard(
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

//        modifier = Modifier
//            .paint(
//                painter = painterResource(id = R.drawable.rectangle_5),
//                contentScale = ContentScale.FillWidth
//            )
//            .height(250.dp),
            contentAlignment = Alignment.TopCenter,
//            modifier = Modifier
//                .height(maxHeight)
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
//            horizontalAlignment = Alignment.CenterHorizontally,
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
                    color = MaterialTheme.colors.primary,
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
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),

        ) {

        items(enteries) { item ->
            HomePageCard(
                drawable = item.drawable,
                temp = item.temp,
                high = item.high,
                low = item.low,
                city = item.city,
                country = item.country,
                weather = item.weather
            )
        }
    }
}


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(vertical = 16.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF2E335A), Color(0xFF1C1B33)),
                    start = Offset(97.5f, 0f),
                    end = Offset(292.5f, 0f)
                )
            )

    ) {
        Spacer(Modifier.height(16.dp))
        Header()
        SearchBar(Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(12.dp))
        HomePage()
        Spacer(Modifier.height(16.dp))
    }
}


@Preview()
@Composable
fun PreviewHomePage(){
    HomePage()
}

@Preview(showBackground = true)
@Composable
fun PreviewHomePageCardImage(){
    HomePageCardImage()
}


