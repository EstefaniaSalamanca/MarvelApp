package com.example.myapplication.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}