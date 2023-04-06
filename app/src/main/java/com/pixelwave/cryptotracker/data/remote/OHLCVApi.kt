package com.pixelwave.cryptotracker.data.remote

import com.pixelwave.cryptotracker.BuildConfig
import com.pixelwave.cryptotracker.data.remote.dto.OHLCVTimeseriesDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface OHLCVApi {

    @GET("/v1/ohlcv/{symbol_id}/latest")
    suspend fun getHistoricalData(
        @Header("X-CoinAPI-Key") apiKey: String = API_KEY,
        @Path("symbol_id") symbolId: String,
        @Query("period_id") periodId: String = "1DAY",
        @Query("limit") limit: Int = 7,
    ): List<OHLCVTimeseriesDto>

    companion object {
        const val API_KEY = BuildConfig.COIN_API_KEY
        const val BASE_URL = "https://rest.coinapi.io"
    }
}