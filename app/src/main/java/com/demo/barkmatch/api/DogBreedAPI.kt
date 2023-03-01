package com.demo.barkmatch.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Interface to act as our bridge to the dog breed API. Since we know the resulting data is JSON,
 * we specify that in our Accept header letting Retrofit know what we expect.
 */
interface DogBreedAPI {

    @Headers("Accept: application/json")
    @GET("breed/{breed}/images")
    fun getImagesByBreed(@Path("breed")breed: String): Call<com.demo.barkmatch.model.MatchResult>
}