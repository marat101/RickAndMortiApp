package com.marat.retrofittest.di

import com.marat.retrofittest.data.repository.CharacterRepository
import com.marat.retrofittest.data.repository.implementation.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(repository: CharacterRepositoryImpl): CharacterRepository
}