package com.manoj.rnm.universe.api

import com.manoj.rnm.universe.api.model.CharacterResultDataResponse
import com.manoj.rnm.universe.core.AppConstants.CHARACTERS_API_URL
import retrofit2.http.GET

interface CharacterApi {

    @GET(CHARACTERS_API_URL)
    suspend fun loadCharacterData(): CharacterResultDataResponse
}