package com.example.myapplication.data

import com.example.myapplication.data.database.dao.FavoritesDao
import com.example.myapplication.data.database.entities.Favorite
import com.example.myapplication.data.model.CharacterModel
import com.example.myapplication.data.model.ResultsModel
import com.example.myapplication.data.network.MarvelService
import javax.inject.Inject

class MarvelRepository @Inject constructor( private val favoritesDao: FavoritesDao) {

    suspend fun insertFavorite(favorite: Favorite) {
        favoritesDao.insertFavorite(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite) {
        favoritesDao.deleteFavorite(favorite)
    }

    suspend fun getAllFavorites(): List<Favorite> {
        return favoritesDao.getAllFavorites()
    }

    suspend fun getFavoriteById(id: Int): Favorite? {
        return favoritesDao.getFavoriteById(id)
    }
}