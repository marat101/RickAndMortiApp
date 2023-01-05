package com.marat.retrofittest.di

import com.turtleteam.domain.repository.NetworkRepository
import com.turtleteam.domain.repository.StorageRepository
import com.turtleteam.network.data.repository.CharacterRepositoryImpl
import com.turtleteam.storage.preferences.UserPreferencesImpl
import org.koin.dsl.module


val repositoryModule = module {
    single<NetworkRepository> {
        CharacterRepositoryImpl(apiService = get())
    }

    single<StorageRepository> {
        UserPreferencesImpl(context = get())
    }
}