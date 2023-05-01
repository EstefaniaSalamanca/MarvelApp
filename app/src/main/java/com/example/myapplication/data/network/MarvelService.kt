package com.example.myapplication.data.network

import com.example.myapplication.data.model.CharacterDetailModel
import com.example.myapplication.data.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MarvelService @Inject constructor (private val api:MarvelApiClient){
    suspend fun getAllCharacters(character:String): CharacterModel {
        return withContext(Dispatchers.IO) {
            val call: Response<CharacterModel> = api.getCharacters(character)
            call.body()!!
        }
    }


    suspend fun getCharacters(characterName:String): CharacterModel {
        return withContext(Dispatchers.IO) {
            val call: Response<CharacterModel> = api.getCharacters(characterName)
            call.body()!!
        }
    }

    suspend fun getCharacterDetail(characterId:Int): CharacterDetailModel {
        return withContext(Dispatchers.IO) {
            val call: Response<CharacterDetailModel> = api.getCharacterDetail(characterId)
            call.body()!!
        }
    }

}