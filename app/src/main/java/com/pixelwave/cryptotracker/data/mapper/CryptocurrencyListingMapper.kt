package com.pixelwave.cryptotracker.data.mapper

import com.pixelwave.cryptotracker.data.local.CryptocurrencyListingEntity
import com.pixelwave.cryptotracker.data.remote.dto.CryptocurrencyListingDto
import com.pixelwave.cryptotracker.domain.model.CryptocurrencyListing

fun CryptocurrencyListingEntity.toCryptocurrencyListing(): CryptocurrencyListing {
    return CryptocurrencyListing(
        id = id ?: 0,
        name = name,
        symbol = symbol
    )
}

fun CryptocurrencyListing.toCryptocurrencyListingEntity(): CryptocurrencyListingEntity {
    return CryptocurrencyListingEntity(
        id = id,
        name = name,
        symbol = symbol
    )
}

fun CryptocurrencyListingDto.toCryptocurrencyListing(): CryptocurrencyListing {
    return CryptocurrencyListing(
        id = id,
        name = name,
        symbol = symbol
    )
}

fun CryptocurrencyListing.toCryptocurrencyListingDto(): CryptocurrencyListingDto {
    return CryptocurrencyListingDto(
        id = id,
        name = name,
        symbol = symbol
    )
}