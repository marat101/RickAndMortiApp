package com.marat.retrofittest.di

import com.marat.retrofittest.data.repository.CharacterRepository
import com.marat.retrofittest.data.repository.implementation.CharacterRepositoryImpl
import org.koin.dsl.module


val repositoryModule = module {
    single<CharacterRepository> {
        CharacterRepositoryImpl(api = get())
    }
}