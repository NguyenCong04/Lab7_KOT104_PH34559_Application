package com.congntph34559.fpoly.lab7_kot104_ph34559_application.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies
    init {
        _movies.value = Movie.getSampleMovie()
    }
}