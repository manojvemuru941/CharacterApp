package com.manoj.rnm.universe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.manoj.rnm.universe.ui.CharacterListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CharacterListFragment())
                .commitNow()
        }
    }
}