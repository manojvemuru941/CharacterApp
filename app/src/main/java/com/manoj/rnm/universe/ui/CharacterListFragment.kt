package com.manoj.rnm.universe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.manoj.rnm.universe.R
import com.manoj.rnm.universe.ui.compose.EmptyState
import com.manoj.rnm.universe.ui.compose.ErrorDialog
import com.manoj.rnm.universe.ui.items.DisplayList

class CharacterListFragment : Fragment() {

    private val viewModel: CharacterItemsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply { 
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner))
            setContent { 
                MaterialTheme {
                    DisplayCharacters()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCharacterData()
    }

    @Composable
    fun DisplayCharacters() {
        Column(
            Modifier
                .background(color = Color.White)
        ) {
            when (val state = viewModel.uiState.collectAsState().value) {
                is UIState.Loading ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                is UIState.Error -> {
                    EmptyState { viewModel.loadCharacterData() }
                    ErrorDialog(
                        message = state.throwable.message.orEmpty(),
                        positiveOnclick = { viewModel.loadCharacterData() },
                        negativeOnclick = { }
                    )
                }
                is UIState.Success<List<ListUIItem>> -> {
                    if(state.data.isEmpty()) EmptyState { viewModel.loadCharacterData() }
                    DisplayList(
                        getString(R.string.app_name),
                        state.data
                    )
                }
            }
        }
    }
}