package com.manoj.rnm.universe

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.manoj.rnm.universe.api.CharacterApi
import com.manoj.rnm.universe.api.CharacterMediator
import com.manoj.rnm.universe.local.CharacterDao
import com.manoj.rnm.universe.local.CharacterDatabase
import com.manoj.rnm.universe.local.CharacterEntity
import com.manoj.rnm.universe.mapper.toEntity
import com.manoj.rnm.universe.repo.CharactersDataRepository
import com.manoj.rnm.universe.repo.CharactersDataRepositoryImpl
import com.manoj.rnm.universe.usecase.CharacterUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class CharacterUseCaseTest {

    @Mock
    private lateinit var charactersDataRepository: CharactersDataRepository

    @Before
    fun setUp() {
        charactersDataRepository = Mockito.mock(CharactersDataRepository::class.java)
    }


    @Test
    fun `load character data success`() {
        runBlocking {
            whenever(charactersDataRepository.getCharacterList()).thenReturn(flowOf(PagingData.from(TestData.characterUIItems)))
            val data = CharacterUseCase(charactersDataRepository).invoke()
            data.collect { pagingItem ->
                pagingItem.map { item ->
                    Assert.assertTrue(TestData.characterUIItems.any { item.name == it.name })
                }
            }
        }
    }
}