package com.manoj.rnm.universe.ui

import java.io.Serializable

class CharacterUIItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val status: String,
    val species: String,
    val gender: String,
    val episode: List<String>
): Serializable