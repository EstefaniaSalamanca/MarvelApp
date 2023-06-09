package com.example.myapplication.di


import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.database.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val CHARACTER_DATABASE_NAME = "character_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, CharacterDatabase::class.java, CHARACTER_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideFavoritesDao(db: CharacterDatabase) = db.favoritesDao()
}
