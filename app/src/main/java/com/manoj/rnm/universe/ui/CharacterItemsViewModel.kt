package com.manoj.rnm.universe.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.manoj.rnm.universe.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

    private var _characterPagingDataFlow : Flow<PagingData<CharacterUIItem>> = load()
    val characterPagingDataFlow: Flow<PagingData<CharacterUIItem>> = _characterPagingDataFlow

    fun onRetry() {
        _characterPagingDataFlow = load()
    }

    private fun load() = characterUseCase.invoke()
}