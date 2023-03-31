package com.pixelwave.cryptotracker.di

import android.app.Application
import androidx.room.Room
import com.pixelwave.cryptotracker.data.local.CryptocurrencyDatabase
import com.pixelwave.cryptotracker.data.remote.CryptocurrencyApi
import com.pixelwave.cryptotracker.data.remote.OHLCVApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCryptocurrencyApi(moshi: Moshi): CryptocurrencyApi {
        return Retrofit.Builder()
            .baseUrl(CryptocurrencyApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create()
    }

    @Provides
    @Singleton
    fun provideOHLCVApi(moshi: Moshi): OHLCVApi {
        return Retrofit.Builder()
            .baseUrl(OHLCVApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create()
    }

    @Provides
    @Singleton
    fun providesStockDatabase(app: Application): CryptocurrencyDatabase {
        return Room.databaseBuilder(
            app,
            CryptocurrencyDatabase::class.java,
            "crypto_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }
}