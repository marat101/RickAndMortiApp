package com.marat.retrofittest.di

import com.marat.retrofittest.aaaaaaaaaaaaaaa.repository.CharacterRepository
import com.marat.retrofittest.data.repository.CharacterRepositoryImpl
import org.koin.dsl.module


val repositoryModule = module {
    single<CharacterRepository> {
        CharacterRepositoryImpl(pagingSource = get())
    }
}