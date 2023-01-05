package com.marat.retrofittest.di

import com.turtleteam.network.data.api.ApiService
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

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    single {
        com.turtleteam.network.data.paging.CharactersPagingSource(api = get())
    }
}