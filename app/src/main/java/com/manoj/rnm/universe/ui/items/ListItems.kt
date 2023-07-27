package com.manoj.rnm.universe.ui.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.manoj.rnm.universe.ui.ListUIItem

@OptIn(ExperimentalUnitApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun DisplayList(
    title: String,
    listItems: List<ListUIItem>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = title,
            modifier = Modifier.padding(10.dp),
            style = TextStyle(
                Color.Black,
                fontSize = TextUnit(value = 20.0F, type = TextUnitType.Sp)
            ),
            fontWeight = FontWeight.Normal
        )

        LazyColumn {
            items(listItems) { character ->
                GlideImage(
                    model = character.url,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                )
                Text(character.title, modifier = Modifier.padding(15.dp))
                Divider()
            }
        }
    }
}