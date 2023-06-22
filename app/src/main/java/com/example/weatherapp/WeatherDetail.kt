package com.example.weatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.R

@Composable
fun DetailsPage(navController: NavController, index:Int?, enteries: SnapshotStateList<Data>,modifier: Modifier = Modifier) {
    val entry: Data = index?.let { enteries.getOrNull(it) } ?: enteries[0]
    Box(
//        modifier = modifier,
        modifier = Modifier.clickable {
            navController.navigateUp()
        },
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(R.drawable.background_detail),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(top = 40.dp)
            ){
            Text(
                text = "${entry.city}",
                fontWeight = FontWeight.W400,
                fontSize = 35.sp,
                color = Color.White,
            )
            Text(
                text = "${entry.temp}°",
                fontWeight = FontWeight.W200,
                fontSize = 88.sp,
                color = Color.White,
            )
            Text(
                text = "${entry.weather}",
                fontWeight = FontWeight.W600,
                fontSize = 20.sp,
                color = Color.Gray,
            )
            Text(
                text = "H:${entry.high}° L:${entry.low}°",
                fontWeight = FontWeight.W600,
                fontSize = 20.sp,
                color = Color.White,
            )
            Spacer(modifier.height(16.dp))
            Image(painter = painterResource(id = R.drawable.house_4_3),
                contentDescription = "house",
                modifier = modifier.fillMaxWidth().height(520.dp)
            )
        }

    }
}

//@Preview()
//@Composable
//fun PreviewDetailsPage(){
//    DetailsPage()
//}