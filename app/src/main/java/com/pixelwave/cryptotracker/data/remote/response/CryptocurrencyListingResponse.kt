package com.pixelwave.cryptotracker.data.remote.response

import com.pixelwave.cryptotracker.data.remote.dto.CryptocurrencyListingDto

data class CryptocurrencyListingResponse(
    val data: List<CryptocurrencyListingDto>
)