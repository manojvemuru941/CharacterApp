package com.manoj.rnm.universe.repo

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.manoj.rnm.universe.local.CharacterEntity
import com.manoj.rnm.universe.mapper.toCharacterItem
import com.manoj.rnm.universe.ui.CharacterUIItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CharactersDataRepository {
    fun getCharacterList(): Flow<PagingData<CharacterUIItem>>
}

class CharactersDataRepositoryImpl @Inject constructor(
    private val characterPager: Pager<Int, CharacterEntity>
): CharactersDataRepository{

    override fun getCharacterList(): Flow<PagingData<CharacterUIItem>> {
        return characterPager.flow.map { pagingData ->
            pagingData.map { it.toCharacterItem() }
        }
    }
}