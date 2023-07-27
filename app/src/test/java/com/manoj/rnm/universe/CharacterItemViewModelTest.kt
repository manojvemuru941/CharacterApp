package com.manoj.rnm.universe

import com.manoj.rnm.universe.ui.CharacterItemsViewModel
import com.manoj.rnm.universe.ui.ListUIItem
import com.manoj.rnm.universe.ui.UIState
import com.manoj.rnm.universe.usecase.CharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CharacterItemViewModelTest {

    private val characterUseCase: CharacterUseCase = Mockito.mock(CharacterUseCase::class.java)
    private lateinit var characterItemsViewModel: CharacterItemsViewModel

    @Before
    fun setup() {
        characterItemsViewModel = CharacterItemsViewModel(characterUseCase)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `load character Data success`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        whenever(characterUseCase.invoke()).thenReturn(flowOf(TestData.listUIItem))
        characterItemsViewModel.loadCharacterData()
        Assert.assertTrue((characterItemsViewModel.uiState.value as UIState.Success<List<ListUIItem>>).data.isNotEmpty())
        Dispatchers.resetMain()
    }
}