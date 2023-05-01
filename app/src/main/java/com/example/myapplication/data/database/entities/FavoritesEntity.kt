package com.example.myapplication.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import retrofit2.http.Url


    @Entity(tableName = "favorites")
    data class Favorite(
        @PrimaryKey val id: Int,
        @ColumnInfo (name = "name") val name: String,
        @ColumnInfo (name = "image") val image: String
    )
