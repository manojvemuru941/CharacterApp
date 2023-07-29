package com.manoj.rnm.universe.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.manoj.rnm.universe.ui.compose.ProfileScreen
import com.manoj.rnm.universe.ui.theme.RNMUniverseTheme

class CharacterActivity : ComponentActivity() {

    private val characterUIItem: CharacterUIItem by lazy {
        intent?.getSerializableExtra(PUPPY_ID) as CharacterUIItem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RNMUniverseTheme {
                ProfileScreen(characterUIItem)
            }
        }
    }

    companion object {
        private const val PUPPY_ID = "puppy_id"
        fun newIntent(context: Context, characterUIItem: CharacterUIItem) =
            Intent(context, CharacterActivity::class.java).apply {
                putExtra(PUPPY_ID, characterUIItem)
            }
    }
}