package com.pixelwave.cryptotracker.presentation.cryptocurrency_info

import com.pixelwave.cryptotracker.domain.model.CryptocurrencyListing
import com.pixelwave.cryptotracker.domain.model.OHLCVTimeseries

data class CryptocurrencyInfoState(
    val cryptocurrencyListing: CryptocurrencyListing? = null,
    val data: List<OHLCVTimeseries> = emptyList(),
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val error: String? = null
)