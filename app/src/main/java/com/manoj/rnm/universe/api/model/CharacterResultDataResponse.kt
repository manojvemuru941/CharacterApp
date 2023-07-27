package com.manoj.rnm.universe.api.model

import com.google.gson.annotations.SerializedName

data class CharacterResultDataResponse(
    @SerializedName("info") val info: CharacterResultInfoResponse,
    @SerializedName("results") val results: List<CharacterDataResponse>
)
