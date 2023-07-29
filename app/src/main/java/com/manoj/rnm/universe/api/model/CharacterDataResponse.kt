package com.manoj.rnm.universe.api.model

import com.google.gson.annotations.SerializedName
import com.manoj.rnm.universe.ui.CharacterUIItem

data class CharacterDataResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("episode") val episode: List<String>
)

fun CharacterDataResponse.toCharacterUIItem(): CharacterUIItem {
    return CharacterUIItem(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        status = this.status,
        species = this.species,
        gender = this.gender,
        episode = this.episode
    )
}
