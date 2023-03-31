package com.pixelwave.cryptotracker.data.remote.dto

import com.squareup.moshi.Json

data class OHLCVTimeseriesDto(
    @field:Json(name = "price_close") val priceClose: Double,
    @field:Json(name = "time_close") val timeClose: String,
)