package com.example.weatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.dimensionResource


@Composable
fun Header(
    enteries: SnapshotStateList<Data>,
    enteriesCopy: List<Data>,
    modifier: Modifier = Modifier
) {
    var updateFlag = remember { mutableStateOf(false) }
    val smallSpacing = dimensionResource(R.dimen.smallSpacing)
    val dimen32 = dimensionResource(R.dimen.dimen32)

    LaunchedEffect(updateFlag.value) {
        if (updateFlag.value) {
            enteries.clear()
            enteries.addAll(enteriesCopy)
            updateFlag.value = false
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.header),
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 12.dp)
        )

        Image(
            painter = painterResource(id = R.drawable._right_title),
            contentDescription = stringResource(id = R.string.settings),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(end = smallSpacing)
                .size(dimen32)
                .clickable {
                    updateFlag.value = true
                }
        )
    }
}