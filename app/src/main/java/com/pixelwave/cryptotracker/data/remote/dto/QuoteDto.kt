package com.pixelwave.cryptotracker.data.remote.dto

import com.squareup.moshi.Json

data class QuoteDto(
    @field:Json(name = "USD") val usd: USDDto
)