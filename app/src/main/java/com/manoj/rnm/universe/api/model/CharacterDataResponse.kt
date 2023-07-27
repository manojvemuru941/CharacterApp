package com.manoj.rnm.universe.api.model

import com.google.gson.annotations.SerializedName

data class CharacterDataResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("species") val species: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("episode") val episode: List<String>
)
