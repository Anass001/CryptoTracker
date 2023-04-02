package com.pixelwave.cryptotracker.data.repository

import android.util.Log
import com.pixelwave.cryptotracker.data.local.CryptocurrencyDatabase
import com.pixelwave.cryptotracker.data.local.dao.CryptocurrencyListingDao
import com.pixelwave.cryptotracker.data.mapper.toCryptocurrencyListing
import com.pixelwave.cryptotracker.data.mapper.toCryptocurrencyListingEntity
import com.pixelwave.cryptotracker.data.mapper.toOhlcvTimeseries
import com.pixelwave.cryptotracker.data.remote.CryptocurrencyApi
import com.pixelwave.cryptotracker.data.remote.OHLCVApi
import com.pixelwave.cryptotracker.domain.model.CryptocurrencyListing
import com.pixelwave.cryptotracker.domain.model.OHLCVTimeseries
import com.pixelwave.cryptotracker.domain.repository.CryptocurrencyRepository
import com.pixelwave.cryptotracker.util.Resource
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptocurrencyRepositoryImpl @Inject constructor(
    private val api: CryptocurrencyApi,
    private val ohlcvApi: OHLCVApi,
    private val db: CryptocurrencyDatabase,
) : CryptocurrencyRepository {

    private val dao: CryptocurrencyListingDao = db.dao
    override suspend fun getHistoricalData(symbol: String): Flow<Resource<List<OHLCVTimeseries>>> {

        val symbolId = "BITSTAMP_SPOT_" + symbol + "_USD"

        return flow {
            emit(Resource.Loading(true))
            val response = try {
                ohlcvApi.getHistoricalData(symbolId = symbolId)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }
            response?.let {
                val data = it.map { item -> item.toOhlcvTimeseries() }
                emit(Resource.Success(data))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getCryptocurrencyListings(
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<CryptocurrencyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.getAllCryptocurrencyListings()
            emit(Resource.Success(data = localListings.map { it.toCryptocurrencyListing() }))

            val isDbEmpty = localListings.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings().data
                response.map { it.toCryptocurrencyListing() }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCryptocurrencyListings()
                dao.insertCryptocurrencyListings(listings.map {
                    it.toCryptocurrencyListingEntity()
                })
                emit(
                    Resource.Success(
                        dao.getAllCryptocurrencyListings().map { it.toCryptocurrencyListing() })
                )
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun updateFavorite(symbol: String, isFavorite: Boolean) {
        dao.updateFavorite(symbol, isFavorite)
    }

    override suspend fun getCryptocurrencyListing(symbol: String): Flow<Resource<CryptocurrencyListing>> {
        val cryptocurrencyListing = dao.getCryptocurrencyListing(symbol)?.toCryptocurrencyListing()
        return flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(cryptocurrencyListing))
            emit(Resource.Loading(false))
        }
    }
}