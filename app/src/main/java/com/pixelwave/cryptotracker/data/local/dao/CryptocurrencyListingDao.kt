package com.pixelwave.cryptotracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pixelwave.cryptotracker.data.local.CryptocurrencyListingEntity
import com.pixelwave.cryptotracker.domain.model.CryptocurrencyListing

@Dao
interface CryptocurrencyListingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptocurrencyListings(
        cryptocurrencyListingEntities: List<CryptocurrencyListingEntity>
    )

    @Query("DELETE FROM cryptocurrencylistingentity")
    suspend fun clearCryptocurrencyListings()

    @Query(
        """
        SELECT * FROM cryptocurrencylistingentity
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
        :query == UPPER(:query) == symbol
    """
    )
    suspend fun searchCryptocurrencyListings(query: String): List<CryptocurrencyListing>
}