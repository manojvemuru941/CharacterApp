package com.manoj.rnm.universe

import com.manoj.rnm.universe.api.CharacterApi
import com.manoj.rnm.universe.repo.CharacterRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.whenever

class CharacterRepositoryTest {

    @Mock
    private lateinit var characterApi: CharacterApi

    @Mock
    private lateinit var repo: CharacterRepository

    @Before
    fun setUp() {
        characterApi = Mockito.mock(CharacterApi::class.java)
        repo = CharacterRepository(characterApi)
    }

    @Test
    fun `load character data success`() {
        runBlocking {
            whenever(characterApi.loadCharacterData()).thenReturn(TestData.characterResultDataResponse)
            val trafficData = repo.loadCharacterData()
            trafficData.collect {
                Assert.assertTrue(it === TestData.characterResultDataResponse)
            }
        }
    }
}