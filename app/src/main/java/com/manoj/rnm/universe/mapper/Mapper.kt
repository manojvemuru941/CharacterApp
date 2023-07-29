package com.manoj.rnm.universe.mapper

import com.manoj.rnm.universe.api.model.CharacterDataResponse
import com.manoj.rnm.universe.local.CharacterEntity
import com.manoj.rnm.universe.ui.CharacterUIItem

fun CharacterEntity.toCharacterItem() = CharacterUIItem(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
    status = this.status,
    species = this.species,
    gender = this.gender,
    episode = this.episode
)

fun CharacterDataResponse.toPokemonEntity() = CharacterEntity(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
    status = this.status,
    species = this.species,
    gender = this.gender,
    episode = this.episode
)

fun CharacterUIItem.toEntity() = CharacterEntity(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
    status = this.status,
    species = this.species,
    gender = this.gender,
    episode = this.episode
)