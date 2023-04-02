package com.pixelwave.cryptotracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CryptocurrencyListingEntity(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val symbol: String,
    val price: Double,
    val change: Double,
    val isFavorite: Boolean = false
)
