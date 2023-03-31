package com.pixelwave.cryptotracker.presentation.cryptocurrency_info

import com.pixelwave.cryptotracker.domain.model.OHLCVTimeseries

data class CryptocurrencyInfoState(
    val data: List<OHLCVTimeseries> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)