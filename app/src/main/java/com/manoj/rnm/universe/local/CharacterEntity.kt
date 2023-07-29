package com.manoj.rnm.universe.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("character")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val status: String,
    val species: String,
    val gender: String,
    val episode: List<String>
)