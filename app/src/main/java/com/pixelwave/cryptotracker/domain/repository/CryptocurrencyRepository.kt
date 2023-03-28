package com.pixelwave.cryptotracker.domain.repository

import com.pixelwave.cryptotracker.domain.model.CryptocurrencyListing
import com.pixelwave.cryptotracker.util.Resource
import kotlinx.coroutines.flow.Flow

interface CryptocurrencyRepository {

    suspend fun getCryptocurrencyListings(
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<CryptocurrencyListing>>>
}