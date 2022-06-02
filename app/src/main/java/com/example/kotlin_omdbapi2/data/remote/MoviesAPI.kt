package com.example.kotlin_omdbapi2.data.remote

import com.example.kotlin_omdbapi2.model.MovieResponse
import com.example.kotlin_omdbapi2.util.Constants
import com.example.kotlin_omdbapi2.util.Constants.APIKey
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    //@GET("?s=inception&apikey=9e081b10")
   // @GET("?s=inception=&apikey=${APIKey}")
    @GET("/")
    fun getGames(@Query("s") s:String,@Query("apikey") apikey:String): Single<MovieResponse>


    /*

      @GET("Search") //i.e https://api.test.com/Search?
      Call<Products> getProducts(@Query("one") String one, @Query("two") String two,
                                @Query("key") String key)

                                https://api.test.com/Search?one=Whatever&two=here&key=SFSDF24242353434

                                http://www.omdbapi.com/?s=inception&apikey=9e081b10
     */
}