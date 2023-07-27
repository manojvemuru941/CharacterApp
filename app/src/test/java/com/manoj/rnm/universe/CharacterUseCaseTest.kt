package com.manoj.rnm.universe

import com.manoj.rnm.universe.api.CharacterApi
import com.manoj.rnm.universe.repo.CharacterRepository
import com.manoj.rnm.universe.usecase.CharacterUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

class CharacterUseCaseTest {

    private lateinit var characterApiRepository: CharacterRepository
    private lateinit var characterUseCase: CharacterUseCase
    private lateinit var characterApi: CharacterApi

    @Before
    fun setUp() {
        characterApi = Mockito.mock(CharacterApi::class.java)
        characterApiRepository = CharacterRepository(characterApi)
        characterUseCase = CharacterUseCase(characterApiRepository)
    }


    @Test
    fun `load traffic data success`() {
        runBlocking {
            whenever(characterApi.loadCharacterData()).thenReturn(TestData.characterResultDataResponse)
            val characterData = characterUseCase.invoke()
            characterData.collect {
                Assert.assertTrue(it.isNotEmpty())
                Assert.assertTrue(it[0].title == TestData.listUIItem[0].title)
            }
        }
    }
}