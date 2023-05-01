package com.example.myapplication.data.network

import com.example.myapplication.data.model.CharacterDetailModel
import com.example.myapplication.data.model.CharacterModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiClient {
    @GET("/v1/public/characters?ts=1&apikey=4dc1b141446d7991b4e54a55fc103bf7&hash=710b5b7151872e980d79f983bcb6331d")
    suspend fun getAllCharacters(): Response<CharacterModel>

    @GET("/v1/public/characters?ts=1&apikey=4dc1b141446d7991b4e54a55fc103bf7&hash=710b5b7151872e980d79f983bcb6331d")
    suspend fun getCharacters(@Query("nameStartsWith") characterName: String): Response<CharacterModel>

    @GET("/v1/public/characters/{characterId}?ts=1&apikey=4dc1b141446d7991b4e54a55fc103bf7&hash=710b5b7151872e980d79f983bcb6331d")
    suspend fun getCharacterDetail(@Path("characterId") characterId:Int): Response<CharacterDetailModel>


}