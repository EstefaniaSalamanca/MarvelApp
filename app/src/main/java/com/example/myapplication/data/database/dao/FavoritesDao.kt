package com.example.myapplication.data.database.dao

import androidx.room.*
import com.example.myapplication.data.database.entities.Favorite


@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<Favorite>

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun getFavoriteById(id: Int): Favorite?
}