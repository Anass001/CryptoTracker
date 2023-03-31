package com.pixelwave.cryptotracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pixelwave.cryptotracker.data.local.entity.CryptocurrencyListingEntity

@Dao
interface CryptocurrencyListingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptocurrencyListings(
        cryptocurrencyListingEntities: List<CryptocurrencyListingEntity>
    )

    @Query("DELETE FROM cryptocurrencylistingentity")
    suspend fun clearCryptocurrencyListings()

    @Query("SELECT * FROM cryptocurrencylistingentity")
    suspend fun getAllCryptocurrencyListings(): List<CryptocurrencyListingEntity>
}