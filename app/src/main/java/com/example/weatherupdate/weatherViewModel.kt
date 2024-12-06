package com.example.weatherupdate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherupdate.api.ApiKey
import com.example.weatherupdate.api.NetworkResponse
import com.example.weatherupdate.api.RetrofitInstance
import com.example.weatherupdate.api.WeatherModel
import kotlinx.coroutines.launch

class weatherViewModel: ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getCity(city: String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            val response = weatherApi.getWeather(ApiKey.key, city)

            try {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _weatherResult.value = NetworkResponse.Error("FAILED TO FETCH DATA")
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error("FAILED TO FETCH DATA")
            }
        }

    }
}