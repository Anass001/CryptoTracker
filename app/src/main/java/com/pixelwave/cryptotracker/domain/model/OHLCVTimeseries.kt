package com.pixelwave.cryptotracker.domain.model

import java.time.LocalDate

data class OHLCVTimeseries(
    val price: Double,
    val date: LocalDate,
)
