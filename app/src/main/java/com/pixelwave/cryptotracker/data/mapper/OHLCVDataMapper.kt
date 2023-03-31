package com.pixelwave.cryptotracker.data.mapper

import com.pixelwave.cryptotracker.data.remote.dto.OHLCVTimeseriesDto
import com.pixelwave.cryptotracker.domain.model.OHLCVTimeseries
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun OHLCVTimeseriesDto.toOhlcvTimeseries(): OHLCVTimeseries {

    val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDate = LocalDate.parse(timeClose, formatter)

    return OHLCVTimeseries(
        price = priceClose,
        date = localDate
    )
}

fun OHLCVTimeseries.toOhlcvTimeseriesDto(): OHLCVTimeseriesDto {

    val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDate = date.format(formatter)

    return OHLCVTimeseriesDto(
        priceClose = price,
        timeClose = localDate
    )
}

