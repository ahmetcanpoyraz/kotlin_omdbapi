package com.example.kotlin_omdbapi2.data.remote

import com.example.kotlin_omdbapi2.model.MovieResponse
import com.example.kotlin_omdbapi2.util.Constants
import com.example.kotlin_omdbapi2.util.Constants.APIKey
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MoviesAPIService {
    private val api = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MoviesAPI::class.java)

    fun getData(searchString: String) : Single<MovieResponse> {
        return  api.getGames(searchString,APIKey)
    }

}