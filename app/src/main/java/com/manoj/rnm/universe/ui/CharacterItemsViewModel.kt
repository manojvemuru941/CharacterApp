package com.manoj.rnm.universe.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manoj.rnm.universe.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterItemsViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<ListUIItem>>>(UIState.Loading)
    val uiState: StateFlow<UIState<List<ListUIItem>>> = _uiState

    fun loadCharacterData() {
        viewModelScope.launch {
            characterUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .onStart {
                    _uiState.value = UIState.Loading
                }
                .catch {
                    _uiState.value = UIState.Error(it)
                }
                .collect {
                    _uiState.value = UIState.Success(it)
                }
        }
    }
}