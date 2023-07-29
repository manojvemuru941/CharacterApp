package com.manoj.rnm.universe.usecase

import androidx.paging.PagingData
import com.manoj.rnm.universe.repo.CharactersDataRepository
import com.manoj.rnm.universe.ui.CharacterUIItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val characterRepository: CharactersDataRepository
){
    operator fun invoke(): Flow<PagingData<CharacterUIItem>> {
        return characterRepository.getCharacterList().flowOn(Dispatchers.IO)
    }
}