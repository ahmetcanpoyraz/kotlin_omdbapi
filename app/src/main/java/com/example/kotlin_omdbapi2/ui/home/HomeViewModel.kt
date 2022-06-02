package com.example.kotlin_omdbapi2.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_omdbapi2.BaseViewModel
import com.example.kotlin_omdbapi2.data.local.MovieDatabase
import com.example.kotlin_omdbapi2.data.remote.MoviesAPIService
import com.example.kotlin_omdbapi2.model.MovieResponse
import com.example.kotlin_omdbapi2.model.MovieResults
import com.example.kotlin_omdbapi2.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {
    private val movieApiService = MoviesAPIService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val movies = MutableLiveData<List<MovieResults>>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    fun refreshData(searchString: String){
        val updateTime = customPreferences.getTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            getDataFromSQLite()
        }
        else {
            getDataFromAPI(searchString)
        }
        getDataFromAPI(searchString)
    }

    fun refreshFromAPI(searchString: String){
        getDataFromAPI(searchString)
    }

    private fun getDataFromSQLite(){
        movieLoading.value = true
        launch {
            val movies = MovieDatabase(getApplication()).movieDao().getAllMovies()
            showGames(movies)
        }
    }

    private fun getDataFromAPI(searchString: String){
        movieLoading.value = true
        launch {
            MovieDatabase(getApplication()).movieDao().deleteAllMovies()
        }
        disposable.add(
            movieApiService.getData(searchString)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieResponse>(){
                    override fun onSuccess(t: MovieResponse) {
                        storeInSQLite(t.Search)
                    }

                    override fun onError(e: Throwable) {
                        movieLoading.value = false
                        movieError.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showGames(list: List<MovieResults>){
        movies.value = list
        movieError.value = false
        movieLoading.value = false
    }

    private fun storeInSQLite(list: List<MovieResults>){
        launch {
            val dao = MovieDatabase(getApplication()).movieDao()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size){
                list[i].uuid = listLong[i].toInt()
                i = i + 1
            }
            showGames(list)
        }

        customPreferences.saveTime(System.nanoTime())
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }




}