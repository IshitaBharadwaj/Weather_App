package com.example.weatherapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

open class Data (
    val temp : Int,
    val high : Int,
    val low : Int,
    val city : String,
    val country : String,
    val weather : String,
    @DrawableRes val drawable : Int,

    )
//val enteries = listOf<Data>(
//    Data(19,24,18,"Coimbatore","India","Mid Rain", getDrawable("Mid Rain")),
//    Data(20,21,-19,"Chennai","India","Fast Wind", getDrawable("Fast Wind")),
//    Data(13,16,8,"Tokyo","Japan","Showers", getDrawable("Showers")),
//    Data(25,30,20,"New York","US","Tornado", getDrawable("Tornado")),
//    Data(20,22,18,"London","UK","Mid Rain", getDrawable("Mid Rain")),
//    Data(23,27,19,"Paris","France","Fast Wind", getDrawable("Fast Wind")),
//
//    )

fun getDrawable(weather: String):Int{
    val drawable = when(weather){
        "Mid Rain" -> R.drawable.moon_cloud_mid_rain
        "Fast Wind" -> R.drawable.moon_cloud_fast_wind
        "Showers" -> R.drawable.sun_cloud_angled_rain
        "Tornado" -> R.drawable.tornado
        else -> R.drawable.tornado
    }
    return drawable
}