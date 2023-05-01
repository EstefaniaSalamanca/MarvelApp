package com.example.myapplication.data.model


import com.google.gson.annotations.SerializedName


data class CharacterDetailModel(
    @SerializedName("data") val data: DataDetailModel,
    @SerializedName("status") val status: String
)

data class DataDetailModel(

    @SerializedName("results") val results: List<ResultDetailModel>,

    )

data class ResultDetailModel(
    @SerializedName("description") val description: String,
    @SerializedName("id") val characterId: Int,
    @SerializedName("name") val characterName: String,
    @SerializedName("thumbnail") val image: ImageDetailModel,

    )
{
    val imageUrl: String
        get() = "${image.path}.${image.extension}"
}

data class ImageDetailModel(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)