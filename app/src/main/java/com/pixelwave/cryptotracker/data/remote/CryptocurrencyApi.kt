package com.pixelwave.cryptotracker.data.remote

import com.pixelwave.cryptotracker.data.remote.dto.CryptocurrencyListingDto
import com.pixelwave.cryptotracker.data.remote.response.CryptocurrencyListingResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface CryptocurrencyApi {

    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getListings(
        @Header("Accepts") accepts: String = "application/json",
        @Header("X-CMC_PRO_API_KEY") apiKey: String = API_KEY,
    ): CryptocurrencyListingResponse

    companion object {
        const val API_KEY = "f5fa3ec3-bbe5-4137-8daf-99a8a59b456e"
        const val BASE_URL = "https://pro-api.coinmarketcap.com"
    }
}