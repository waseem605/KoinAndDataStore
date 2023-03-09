package com.example.koinanddatastore.retrofitWithKoin

import com.example.koinanddatastore.retrofitWithKoin.data.BusAdapter
import com.example.koinanddatastore.retrofitWithKoin.data.repository.MainRepository
import com.example.koinanddatastore.retrofitWithKoin.data.utils.Listener
import com.example.koinanddatastore.retrofitWithKoin.demo.Car
import com.example.koinanddatastore.retrofitWithKoin.demo.Engine
import com.example.koinanddatastore.retrofitWithKoin.demo.Wheel
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Array.get


@OptIn(KoinApiExtension::class)
val appModule = module {

    factory { PostActivity() } bind Listener::class

    //adapter
    factory { BusAdapter(get()) }

    //retrofit
    single { providesMoshi() }
    single { providesRetrofitBuilder(get()) }
    single { providesApiService(get()) }

    //repository
    factory { MainRepository(get()) }

    //viewmodel
    viewModel { MainViewModel(get()) }
}

/*val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    single<MainRepository> {
        MainRepositoryImpl(get())
    }
    viewModel {
        MainViewModel(get())
    }
}*/

val demoModule = module {
    factory { Engine() }
    factory { Wheel() }
    factory { Car(get(),get()) }
}