package com.manoj.rnm.universe.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.manoj.rnm.universe.R

@OptIn(ExperimentalUnitApi::class)
@Composable
fun EmptyState(
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.something_went_wrong),
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            style = TextStyle(
                color = Color.Black,
                fontSize = TextUnit(value = 24.0F, TextUnitType.Sp)
            )
        )

        Button(
            onClick = { onRetry.invoke() }
        ) {
            Text(text = stringResource(id = R.string.retry), color = Color.Black)
        }
    }
}