package com.example.koinanddatastore.retrofitWithKoin

import com.example.koinanddatastore.retrofitWithKoin.`interface`.Main
import com.example.koinanddatastore.retrofitWithKoin.data.BusAdapter
import com.example.koinanddatastore.retrofitWithKoin.data.repository.MainRepository
import com.example.koinanddatastore.retrofitWithKoin.demo.Car
import com.example.koinanddatastore.retrofitWithKoin.demo.Engine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

//@KoinApiExtension
class Component :KoinComponent {
    val car: Car by inject()
    val engine: Engine by inject()
    val main: Main by inject()

    val busAdapter: BusAdapter by inject()
    val mainViewModel:MainViewModel by inject()
    val mainRepository: MainRepository by inject()
}