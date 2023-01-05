package com.marat.retrofittest.di

import com.turtleteam.domain.usecase.local.CheckIdUseCase
import com.turtleteam.domain.usecase.local.SaveIdsUseCase
import com.turtleteam.domain.usecase.network.GetCharactersPageUseCase
import com.turtleteam.domain.usecase.network.GetFavoriteCharactersUseCase
import com.turtleteam.domain.usecase.network.SearchCharactersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetCharactersPageUseCase(get())
    }
    factory {
        SearchCharactersUseCase(get())
    }
    factory {
        GetFavoriteCharactersUseCase(storage = get(), networkRepository = get())
    }
    factory {
        CheckIdUseCase(storage = get())
    }
    factory {
        SaveIdsUseCase(get())
    }
}