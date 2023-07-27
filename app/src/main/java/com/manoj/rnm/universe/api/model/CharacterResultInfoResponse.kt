package com.manoj.rnm.universe.api.model

import com.google.gson.annotations.SerializedName

data class CharacterResultInfoResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?
)
