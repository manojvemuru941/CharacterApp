package com.manoj.rnm.universe

import androidx.paging.PagingData
import androidx.paging.map
import com.manoj.rnm.universe.repo.CharactersDataRepository
import com.manoj.rnm.universe.ui.CharacterItemsViewModel
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CharacterItemViewModelTest {

    @Mock
    private lateinit var charactersDataRepository: CharactersDataRepository

    @Mock
    private lateinit var characterUseCase: CharacterUseCase

    @Before
    fun setup() {
        charactersDataRepository = Mockito.mock(CharactersDataRepository::class.java)
        characterUseCase = CharacterUseCase(charactersDataRepository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `load character Data success`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        whenever(charactersDataRepository.getCharacterList()).thenReturn(flowOf(PagingData.from(TestData.characterUIItems)))
        whenever(characterUseCase.invoke()).thenReturn(flowOf(PagingData.from(TestData.characterUIItems)))
        val data = CharacterItemsViewModel(characterUseCase).characterPagingDataFlow
        data.collect { pagingItem ->
            pagingItem.map { item ->
                Assert.assertTrue(TestData.characterUIItems.any { item.name == it.name })
            }
        }
        Dispatchers.resetMain()
    }
}