package com.pixelwave.cryptotracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pixelwave.cryptotracker.data.local.entity.CryptocurrencyListingEntity
import com.pixelwave.cryptotracker.domain.model.CryptocurrencyListing

@Dao
interface CryptocurrencyListingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptocurrencyListings(
        cryptocurrencyListingEntities: List<CryptocurrencyListingEntity>
    )

    @Query("UPDATE cryptocurrencylistingentity SET price = :price, change = :change WHERE symbol = :symbol")
    suspend fun updateCryptocurrencyListing(
        symbol: String,
        price: Double,
        change: Double
    )

    @Query("DELETE FROM cryptocurrencylistingentity")
    suspend fun clearCryptocurrencyListings()

    @Query(
        """
            SELECT * 
            FROM cryptocurrencylistingentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                UPPER(:query) == symbol
        """
    )
    suspend fun searchCryptocurrencyListings(query: String): List<CryptocurrencyListingEntity>

    @Query("SELECT * FROM cryptocurrencylistingentity WHERE symbol = :symbol")
    suspend fun getCryptocurrencyListing(symbol: String): CryptocurrencyListingEntity?

    @Query("UPDATE cryptocurrencylistingentity SET isFavorite = :isFavorite WHERE symbol = :symbol")
    suspend fun updateFavorite(symbol: String, isFavorite: Boolean)
}