package com.pixelwave.cryptotracker.data.local

import androidx.room.Entity

@Entity
data class CryptocurrencyListingEntity(
    val id: String,
    val name: String,
    val symbol: String,
)
