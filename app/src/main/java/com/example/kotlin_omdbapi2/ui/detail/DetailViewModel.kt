package com.example.kotlin_omdbapi2.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_omdbapi2.BaseViewModel
import com.example.kotlin_omdbapi2.data.local.MovieDatabase
import com.example.kotlin_omdbapi2.model.MovieResults
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val movieLiveData = MutableLiveData<MovieResults>()
    fun getDataFromRoom(uuid: Int){
        launch {
            val dao = MovieDatabase(getApplication()).movieDao()
            val game = dao.getMovie(uuid)
            movieLiveData.value = game
        }
    }
}