package com.pixelwave.cryptotracker.data.remote.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass


class CryptocurrencyListingAdapter {

    fun fromJson(cryptocurrencyListingResponse: CryptocurrencyListingResponse): List<Int> {
        return cryptocurrencyListingResponse.data.mapNotNull { currency ->
            currency.quote?.USD?.let { quote ->
                currency.id
            }
        }
    }

    @JsonClass(generateAdapter = true)
    data class CryptocurrencyListingResponse(
        val data: List<CryptocurrencyListing>
    )

    @JsonClass(generateAdapter = true)
    data class CryptocurrencyListing(
        val id: Int,
        val name: String,
        val symbol: String,
        val quote: Quote?
    )

    @JsonClass(generateAdapter = true)
    data class Quote(
        val USD: USDQuote?,
    )

    @JsonClass(generateAdapter = true)
    data class USDQuote(
        val price: Double,
        val percent_change_24h: Double
    )
}
