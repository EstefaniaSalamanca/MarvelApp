package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.database.dao.FavoritesDao
import com.example.myapplication.data.database.entities.Favorite


@Database(entities = [Favorite::class], version = 1)

abstract class CharacterDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        fun getInstance(context: Context): CharacterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java,
                    "mi_base_de_datos"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}