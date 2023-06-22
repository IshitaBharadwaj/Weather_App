package com.example.weatherapp

import android.provider.Settings.Global.getString
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import androidx.compose.foundation.clickable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun Header(enteries: SnapshotStateList<Data>,
           enteriesCopy: List<Data>,
           onCopyClicked: () -> Unit,
           modifier: Modifier = Modifier)
{
    var updateFlag = remember { mutableStateOf(false) }

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
                .padding(end=20.dp)
                .size(32.dp)
                .clickable {
                    updateFlag.value = true
//                        onCopyClicked
                }
        )
    }
}