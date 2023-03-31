package com.pixelwave.cryptotracker.data.remote.dto

import com.squareup.moshi.Json

data class USDDto(
    @field:Json(name = "percent_change_24h") val change: Double = 0.0,
    val price: Double = 0.0,
)