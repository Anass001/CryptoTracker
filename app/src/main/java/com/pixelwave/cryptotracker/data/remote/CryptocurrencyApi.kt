package com.pixelwave.cryptotracker.data.remote

import com.pixelwave.cryptotracker.BuildConfig
import com.pixelwave.cryptotracker.data.remote.OHLCVApi.Companion.API_KEY
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
        const val API_KEY = BuildConfig.COIN_MARKET_CAP_API_KEY
        const val BASE_URL = "https://pro-api.coinmarketcap.com"
    }
}