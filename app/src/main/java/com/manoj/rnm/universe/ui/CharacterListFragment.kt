package com.manoj.rnm.universe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.manoj.rnm.universe.ui.compose.DisplayList

class CharacterListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply { 
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner))
            setContent { 
                MaterialTheme {
                    DisplayCharacters {
//                        requireActivity().startActivity(CharacterActivity.newIntent(requireContext(), it))
                    }
                }
            }
        }
    }

    @Composable
    fun DisplayCharacters(
        navigateToProfile: (CharacterUIItem) -> Unit
    ) {
        Column(
            Modifier
                .background(color = Color.White)
        ) {
//            DisplayList(
//                navigateToProfile
//            )
        }
    }
}