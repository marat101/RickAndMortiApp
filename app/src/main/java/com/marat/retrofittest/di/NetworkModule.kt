package com.marat.retrofittest.di

import com.marat.retrofittest.data.api.ApiService
import com.marat.retrofittest.data.paging.CharactersPagingSource
import com.marat.retrofittest.utils.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.CHARACTER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    single {
        CharactersPagingSource(api = get())
    }
}