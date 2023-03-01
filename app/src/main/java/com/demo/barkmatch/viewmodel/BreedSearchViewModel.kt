package com.demo.barkmatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.barkmatch.application.BarkMatchApplication
import com.demo.barkmatch.model.MatchResult
import com.demo.barkmatch.util.SingleEventLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreedSearchViewModel: ViewModel() {

     val matchResults: SingleEventLiveData<MatchResult> = SingleEventLiveData()
     val errorMessage: SingleEventLiveData<String> = SingleEventLiveData()

    fun searchDogsByBreed(breed: String): LiveData<com.demo.barkmatch.model.MatchResult> {

        BarkMatchApplication.getInstance().getAPI()?.getImagesByBreed(breed)?.enqueue(object:
            Callback<com.demo.barkmatch.model.MatchResult> {
            override fun onResponse(call: Call<com.demo.barkmatch.model.MatchResult>, response: Response<com.demo.barkmatch.model.MatchResult>) {
                matchResults.postValue(response.body())

                if(!response.isSuccessful || response.errorBody() != null){
                    errorMessage.postValue(response.errorBody()?.string())
                }

            }

            override fun onFailure(call: Call<com.demo.barkmatch.model.MatchResult>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })

        return matchResults
    }


    }
