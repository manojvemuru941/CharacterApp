package com.manoj.rnm.universe.api

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.manoj.rnm.universe.core.AppConstants
import com.manoj.rnm.universe.local.CharacterDatabase
import com.manoj.rnm.universe.local.CharacterEntity
import com.manoj.rnm.universe.local.RemoteKeyEntity
import com.manoj.rnm.universe.mapper.toPokemonEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterMediator @Inject constructor(
    private val pokemonDatabase: CharacterDatabase,
    private val pokemonApi: CharacterApi,
) : RemoteMediator<Int, CharacterEntity>() {

    private val REMOTE_KEY_ID = "character"

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>,
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> "${AppConstants.BASE_API_URL}${AppConstants.CHARACTERS_API_URL}"
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    // RETRIEVE NEXT OFFSET FROM DATABASE
                    val remoteKey = pokemonDatabase.remoteKeyDao.getById(REMOTE_KEY_ID)
                    if (remoteKey?.nextOffset == null) // END OF PAGINATION REACHED
                        return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKey.nextOffset
                }
            }
            // MAKE API CALL
            val apiResponse = pokemonApi.loadCharacterData(offset)
            val results = apiResponse.results
            val nextOffset = apiResponse.info.next ?: "${AppConstants.BASE_API_URL}${AppConstants.CHARACTERS_API_URL}"
            // SAVE RESULTS AND NEXT OFFSET TO DATABASE
            pokemonDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    // IF REFRESHING, CLEAR DATABASE FIRST
                    pokemonDatabase.characterDao.clearAll()
                    pokemonDatabase.remoteKeyDao.deleteById(REMOTE_KEY_ID)
                }
                pokemonDatabase.characterDao.insertAll(
                    results.mapNotNull { it.toPokemonEntity() }
                )
                pokemonDatabase.remoteKeyDao.insert(
                    RemoteKeyEntity(
                        id = REMOTE_KEY_ID,
                        nextOffset = nextOffset,
                    )
                )
            }
            // CHECK IF END OF PAGINATION REACHED
            MediatorResult.Success(endOfPaginationReached = results.size < state.config.pageSize)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
