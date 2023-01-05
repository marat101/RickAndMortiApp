package com.marat.retrofittest.di

import com.turtleteam.ui.fragments.detailfragment.DetailInformationViewModel
import com.turtleteam.ui.fragments.favoritesfragment.FavoritesViewModel
import com.turtleteam.ui.fragments.listfragment.CharactersListViewModel
import com.turtleteam.ui.fragments.searchfragment.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel {
        CharactersListViewModel(get())
    }
    viewModel {
        SearchViewModel(get())
    }
    viewModel {
        FavoritesViewModel(get())
    }
    viewModel {
        DetailInformationViewModel(
            saveId = get(),
            isFavorite = get()
        )
    }
}