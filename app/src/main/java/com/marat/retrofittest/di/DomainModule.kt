package com.marat.retrofittest.di

import com.marat.retrofittest.aaaaaaaaaaaaaaa.usecase.GetCharactersPageUseCase
import org.koin.dsl.module

val domainModule =  module {
    factory {
        GetCharactersPageUseCase(get())
    }
}