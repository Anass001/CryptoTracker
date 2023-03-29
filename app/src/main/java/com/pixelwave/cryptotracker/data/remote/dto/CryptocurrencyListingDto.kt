package com.pixelwave.cryptotracker.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptocurrencyListingDto(
    val id: Int,
    val name: String,
    val symbol: String,
)