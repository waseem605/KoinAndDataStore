package com.example.koinanddatastore.retrofitWithKoin.data.utils

import com.example.koinanddatastore.retrofitWithKoin.data.Post

sealed class ApiState{
    class Success(val data:List<Post>) : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}
