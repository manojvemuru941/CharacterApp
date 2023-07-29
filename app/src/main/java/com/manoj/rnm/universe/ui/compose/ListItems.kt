package com.manoj.rnm.universe.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.manoj.rnm.universe.ui.CharacterItemsViewModel
import com.manoj.rnm.universe.ui.CharacterUIItem
import com.manoj.rnm.universe.ui.theme.typography

@Composable
fun DisplayList(
    navigateToProfile: (CharacterUIItem) -> Unit
) {
    val viewModel = hiltViewModel<CharacterItemsViewModel>()
    val listItems = viewModel.characterPagingDataFlow.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            listItems.loadState.refresh is LoadState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(
                    Alignment.Center
                )
            )

            listItems.itemCount < 1 -> EmptyState()

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(
                        count = listItems.itemCount,
                        key = listItems.itemKey { it.id }
                    ) { index ->
                        listItems[index]?.let {
                            CharacterListItem(
                                characterItem = it,
                                navigateToProfile
                            )
                        }
                    }
                    item {
                        if (listItems.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterListItem(
    characterItem: CharacterUIItem,
    navigateToProfile: (CharacterUIItem) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(Modifier.clickable { navigateToProfile(characterItem) }) {
            CharacterImage(characterItem)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = characterItem.name, style = typography.bodyMedium)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun CharacterImage(characterItem: CharacterUIItem) {
    GlideImage(
        model = characterItem.imageUrl,
        contentDescription = characterItem.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}