package com.example.koinanddatastore.retrofitWithKoin

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun providesMoshi(): Moshi = Moshi
    .Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun providesRetrofitBuilder(moshi: Moshi): Retrofit =
    Retrofit.Builder()
        .run {
            baseUrl(ApiService.BASE_URL)
            addConverterFactory(MoshiConverterFactory.create(moshi))
            build()
        }

fun providesApiService(retrofit: Retrofit):ApiService =
    retrofit.create(ApiService::class.java)