package com.manoj.rnm.universe

import com.manoj.rnm.universe.api.model.CharacterDataResponse
import com.manoj.rnm.universe.api.model.CharacterResultDataResponse
import com.manoj.rnm.universe.api.model.CharacterResultInfoResponse
import com.manoj.rnm.universe.ui.CharacterUIItem

object TestData {

    private val listCharacterDataResponse = listOf(
        CharacterDataResponse(
            id = 1,
            name = "Rick Sanchez",
            species = "Human",
            status = "Alive",
            gender = "Male",
            imageUrl = "some url",
            episode = listOf("one", "two")
        ),
        CharacterDataResponse(
            id = 2,
            name = "Rick Sanchez2",
            species = "Human2",
            status = "Alive",
            gender = "Male",
            imageUrl = "some url2",
            episode = listOf("one2", "two2")
        )
    )

    val characterResultDataResponse = CharacterResultDataResponse(
        info = CharacterResultInfoResponse(
            count = 1,
            pages = 1,
            next = null,
            prev = null
        ),
        results = listCharacterDataResponse
    )

    val characterUIItems = listOf(
        CharacterUIItem(
            id = 1,
            name = "Rick Sanchez",
            species = "Human",
            status = "Alive",
            gender = "Male",
            imageUrl = "some url",
            episode = listOf("one", "two")
        ),
        CharacterUIItem(
            id = 2,
            name = "Rick Sanchez2",
            species = "Human2",
            status = "Alive",
            gender = "Male",
            imageUrl = "some url2",
            episode = listOf("one2", "two2")
        )
    )
}