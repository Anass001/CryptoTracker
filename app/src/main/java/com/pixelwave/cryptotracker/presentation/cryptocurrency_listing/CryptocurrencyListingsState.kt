package com.pixelwave.cryptotracker.presentation.cryptocurrency_listing

import com.pixelwave.cryptotracker.domain.model.CryptocurrencyListing

data class CryptocurrencyListingsState (
    val cryptocurrencies: List<CryptocurrencyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
)