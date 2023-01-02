package com.marat.retrofittest.di

import com.marat.retrofittest.aaaaaaaaaaaaaaa.usecase.GetCharactersPageUseCase
import com.turtleteam.domain.usecase.SearchCharactersUseCase
import org.koin.dsl.module

val domainModule =  module {
    factory {
        GetCharactersPageUseCase(get())
    }
    factory {
        SearchCharactersUseCase(get())
    }
}