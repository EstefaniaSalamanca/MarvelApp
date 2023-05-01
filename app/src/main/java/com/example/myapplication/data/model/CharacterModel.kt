package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class CharacterModel(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: DataModel
)

data class DataModel(
    @SerializedName("results") val results: List<ResultsModel>,
)

data class ResultsModel(
    @SerializedName("id") val characterId: Int,
    @SerializedName("name") val characterName: String,
    @SerializedName("thumbnail") val image: ImageModel,
)
{
    val imageUrl: String
        get() = "${image.path}.${image.extension}"
}

data class ImageModel(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String

)