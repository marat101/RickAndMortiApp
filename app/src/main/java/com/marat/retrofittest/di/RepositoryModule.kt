package com.marat.retrofittest.di

import com.turtleteam.domain.repository.CharacterRepository
import com.turtleteam.network.data.repository.CharacterRepositoryImpl
import org.koin.dsl.module


val repositoryModule = module {
    single<CharacterRepository> {
        CharacterRepositoryImpl(apiService = get())
    }
}