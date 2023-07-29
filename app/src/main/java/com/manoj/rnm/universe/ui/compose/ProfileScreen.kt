package com.manoj.rnm.universe.ui.compose

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.manoj.rnm.universe.R
import com.manoj.rnm.universe.ui.CharacterUIItem

@Composable
fun ProfileScreen(characterItem: CharacterUIItem) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    ProfileHeader(
                        scrollState,
                        characterItem,
                        this@BoxWithConstraints.maxHeight
                    )
                    ProfileContent(characterItem, this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    characterItem: CharacterUIItem,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    GlideImage(
        model = characterItem.imageUrl,
        contentDescription = characterItem.name,
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun ProfileContent(characterItem: CharacterUIItem, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        Name(characterItem)

        ProfileProperty(stringResource(R.string.status), characterItem.status)
        ProfileProperty(stringResource(R.string.gender), characterItem.gender)
        ProfileProperty(stringResource(R.string.species), characterItem.species)
        ProfileProperty(stringResource(R.string.episode), characterItem.episode.size.toString())

        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun Name(
    characterItem: CharacterUIItem
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Name(
            characterItem = characterItem,
            modifier = Modifier.baselineHeight(32.dp)
        )
    }
}

@Composable
private fun Name(characterItem: CharacterUIItem, modifier: Modifier = Modifier) {
    Text(
        text = characterItem.name,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProfileProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider() {
            Text(
                text = label,
                modifier = Modifier.baselineHeight(24.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary)
        } else {
            MaterialTheme.typography.bodyMedium
        }
        Text(
            text = value,
            modifier = Modifier.baselineHeight(24.dp),
            style = style
        )
    }
}
