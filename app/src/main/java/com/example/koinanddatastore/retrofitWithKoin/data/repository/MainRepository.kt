package com.example.koinanddatastore.retrofitWithKoin.data.repository

import com.example.koinanddatastore.retrofitWithKoin.ApiService
import com.example.koinanddatastore.retrofitWithKoin.data.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository  constructor(private val apiService: ApiService)  {
//    fun doNetworkCall()

    fun getBus(): Flow<List<Post>> = flow {
        emit(apiService.getPost())
    }.flowOn(Dispatchers.IO)

}