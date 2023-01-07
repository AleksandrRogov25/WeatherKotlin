package com.example.weatherkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherkotlin.model.Repository
import com.example.weatherkotlin.model.RepositoryImpl

class MainViewModel (
    private val liveDataToObserve : MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl : Repository = RepositoryImpl()
    ) : ViewModel() {

        fun getLiveData() = liveDataToObserve

        fun getWeather() = getDataFromLocalSource()

        private fun getDataFromLocalSource() {
            liveDataToObserve.value = AppState.Loading

            Thread {
                Thread.sleep(2000)

                liveDataToObserve.postValue(
                    AppState.Success(repositoryImpl.getWeatherFromLocalStorage()))
            }.start()
        }
    }