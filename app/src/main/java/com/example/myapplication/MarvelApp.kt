package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.database.CharacterDatabase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp

class MarvelApp: Application(){
    companion object {
        lateinit var database: CharacterDatabase
    }


    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            CharacterDatabase::class.java, "my-db-name"
        ).build()
    }
}

