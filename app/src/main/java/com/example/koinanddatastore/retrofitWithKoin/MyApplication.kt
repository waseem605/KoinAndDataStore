package com.example.koinanddatastore.retrofitWithKoin

import android.app.Application
import com.example.koinanddatastore.retrofitWithKoin.`interface`.interfaceModule
import org.koin.core.context.startKoin


class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {

            modules(appModule)

//            modules(demoModule,interfaceModule)
//            modules(interfaceModule)
//            androidLogger()
//            androidContext(this@MyApplication)
//            modules(appModule, activityModule)
        }
    }
}