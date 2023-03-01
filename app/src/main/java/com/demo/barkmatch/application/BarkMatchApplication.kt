package com.demo.barkmatch.application

import android.app.Application
import com.demo.barkmatch.api.DogBreedAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BarkMatchApplication: Application() {
    var api: DogBreedAPI? = null

    // Let's use the singleton pattern here since we only need to initialize the RetroFit object once
    companion object {
        private lateinit var instance: BarkMatchApplication


        fun getInstance(): BarkMatchApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        var retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        api = retrofit.create(DogBreedAPI::class.java);
    }

    fun getAPI(): DogBreedAPI? {
        return api
    }
}