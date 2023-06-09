package com.pixelwave.cryptotracker.domain.repository

import com.pixelwave.cryptotracker.domain.model.CryptocurrencyListing
import com.pixelwave.cryptotracker.domain.model.OHLCVTimeseries
import com.pixelwave.cryptotracker.util.Resource
import kotlinx.coroutines.flow.Flow

interface CryptocurrencyRepository {

    suspend fun getHistoricalData(
        symbol: String
    ): Flow<Resource<List<OHLCVTimeseries>>>

    suspend fun getCryptocurrencyListings(
        fetchFromRemote: Boolean,
        searchQuery: String
    ): Flow<Resource<List<CryptocurrencyListing>>>

    suspend fun updateFavorite(symbol: String, isFavorite: Boolean)

    suspend fun getCryptocurrencyListing(symbol: String): Flow<Resource<CryptocurrencyListing>>
}