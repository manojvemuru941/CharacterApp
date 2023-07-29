package com.manoj.rnm.universe.api

import com.manoj.rnm.universe.api.model.CharacterResultDataResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterApi {

    @GET
    suspend fun loadCharacterData(
        @Url url: String
    ): CharacterResultDataResponse
}