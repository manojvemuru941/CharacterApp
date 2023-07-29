package com.manoj.rnm.universe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.manoj.rnm.universe.ui.CharacterActivity
import com.manoj.rnm.universe.ui.compose.DisplayList
import com.manoj.rnm.universe.ui.theme.RNMUniverseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RNMUniverseTheme {
                DisplayList { characterItem ->
                    startActivity(CharacterActivity.newIntent(this@MainActivity, characterItem))
                }
            }
        }
    }
}