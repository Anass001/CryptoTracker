package com.pixelwave.cryptotracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pixelwave.cryptotracker.data.local.dao.CryptocurrencyListingDao

@Database(
    entities = [CryptocurrencyListingEntity::class],
    version = 2,
    exportSchema = false,

    )
abstract class CryptocurrencyDatabase : RoomDatabase() {
    abstract val dao: CryptocurrencyListingDao
}