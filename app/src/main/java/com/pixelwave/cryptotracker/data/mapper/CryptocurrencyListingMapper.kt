package com.pixelwave.cryptotracker.data.mapper

import com.pixelwave.cryptotracker.data.local.entity.CryptocurrencyListingEntity
import com.pixelwave.cryptotracker.data.remote.dto.CryptocurrencyListingDto
import com.pixelwave.cryptotracker.data.remote.dto.QuoteDto
import com.pixelwave.cryptotracker.data.remote.dto.USDDto
import com.pixelwave.cryptotracker.domain.model.CryptocurrencyListing

fun CryptocurrencyListingEntity.toCryptocurrencyListing(): CryptocurrencyListing {
    return CryptocurrencyListing(
        id = id ?: 0,
        name = name,
        symbol = symbol,
        price = price,
        change = change,
        isFavorite = isFavorite
    )
}

fun CryptocurrencyListing.toCryptocurrencyListingEntity(): CryptocurrencyListingEntity {
    return CryptocurrencyListingEntity(
        id = id,
        name = name,
        symbol = symbol,
        price = price,
        change = change,
        isFavorite = isFavorite
    )
}

fun CryptocurrencyListingDto.toCryptocurrencyListing(): CryptocurrencyListing {
    return CryptocurrencyListing(
        id = id,
        name = name,
        symbol = symbol,
        price = quote.usd.price,
        change = quote.usd.change
    )
}

fun CryptocurrencyListing.toCryptocurrencyListingDto(): CryptocurrencyListingDto {
    return CryptocurrencyListingDto(
        id = id,
        name = name,
        symbol = symbol,
        quote = QuoteDto(
            usd = USDDto(
                price = price,
                change = change
            )
        )
    )
}