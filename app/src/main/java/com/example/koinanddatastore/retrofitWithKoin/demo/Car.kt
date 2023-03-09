package com.example.koinanddatastore.retrofitWithKoin.demo

import android.util.Log

class Car constructor(val engine:Engine,val wheel: Wheel) {

    fun getCar(){
        engine.startEngine()
        wheel.startWheel()
        Log.d("Car","Car is calling")
    }
}
class Engine {

    fun startEngine(){
        Log.d("Car","startEngine")
    }
}
class Wheel {

    fun startWheel(){
        Log.d("Car","startWheel  ")
    }
}