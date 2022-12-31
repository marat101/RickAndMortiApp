package com.marat.retrofittest.di

import com.turtleteam.network.data.api.ApiService
import com.turtleteam.network.data.paging.CharactersPagingSource
import com.turtleteam.network.data.utils.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(com.turtleteam.network.data.utils.Constants.CHARACTER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<com.turtleteam.network.data.api.ApiService> {
        get<Retrofit>().create(com.turtleteam.network.data.api.ApiService::class.java)
    }

    single {
        com.turtleteam.network.data.paging.CharactersPagingSource(api = get())
    }
}