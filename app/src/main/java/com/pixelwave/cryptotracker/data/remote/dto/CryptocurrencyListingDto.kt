package com.pixelwave.cryptotracker.data.remote.dto

data class CryptocurrencyListingDto(
    val id: Int,
    val name: String,
    val symbol: String,
    val quote: QuoteDto
)