package com.pixelwave.cryptotracker.di

import com.pixelwave.cryptotracker.data.repository.CryptocurrencyRepositoryImpl
import com.pixelwave.cryptotracker.domain.repository.CryptocurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCryptocurrencyRepository(
        cryptocurrencyRepositoryImpl: CryptocurrencyRepositoryImpl
    ): CryptocurrencyRepository
}