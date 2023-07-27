package com.manoj.rnm.universe.usecase

import com.manoj.rnm.universe.repo.CharacterRepository
import com.manoj.rnm.universe.ui.ListUIItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
){

    suspend operator fun invoke():Flow<List<ListUIItem>> {
        val listUIItems = mutableListOf<ListUIItem>()
        return characterRepository.loadCharacterData().map { characterDataResponse ->
            characterDataResponse.results.map { characterItem ->
                val item = ListUIItem(
                    characterItem.name,
                    characterItem.imageUrl
                )
                listUIItems.add(item)
            }
            listUIItems
        }
    }
}