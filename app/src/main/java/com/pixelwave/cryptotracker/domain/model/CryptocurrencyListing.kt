package com.pixelwave.cryptotracker.domain.model

data class CryptocurrencyListing(
    val id: Int,
    val name: String,
    val symbol: String,
    val price: Double,
    val change: Double,
    val isFavorite: Boolean = false
)
