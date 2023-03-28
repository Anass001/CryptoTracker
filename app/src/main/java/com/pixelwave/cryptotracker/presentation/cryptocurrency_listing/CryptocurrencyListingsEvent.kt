package com.pixelwave.cryptotracker.presentation.cryptocurrency_listing

sealed class CryptocurrencyListingsEvent {
    object Refresh : CryptocurrencyListingsEvent()
}