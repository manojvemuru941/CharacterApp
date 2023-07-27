package com.manoj.rnm.universe.repo

import com.manoj.rnm.universe.api.CharacterApi
import com.manoj.rnm.universe.api.model.CharacterResultDataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterApi: CharacterApi
) {
    suspend fun loadCharacterData(): Flow<CharacterResultDataResponse> {
        return flow { emit(characterApi.loadCharacterData()) }
    }
}