package com.example.koinanddatastore.retrofitWithKoin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koinanddatastore.retrofitWithKoin.data.repository.MainRepository
import com.example.koinanddatastore.retrofitWithKoin.data.utils.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
): ViewModel() {

    private val _busApi: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val busApi: StateFlow<ApiState> = _busApi

    fun getBus() = viewModelScope.launch {
        repository.getBus()
            .onStart {
                _busApi.value = ApiState.Loading
            }.catch { e->
                _busApi.value = ApiState.Failure(e)
            }.collect { response->
                _busApi.value = ApiState.Success(response)
            }
    }



    fun doNetworkCall() {
        println("Something")
    }
}
