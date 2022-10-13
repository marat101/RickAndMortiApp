package com.marat.retrofittest.di

import com.marat.retrofittest.data.api.ApiService
import com.marat.retrofittest.data.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(api: ApiService) = CharacterRepository(api)
}