package com.pixelwave.cryptotracker.presentation.cryptocurrency_listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pixelwave.cryptotracker.domain.model.CryptocurrencyListing

@Composable
fun CryptocurrencyListingItem(
    cryptocurrencyListing: CryptocurrencyListing,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = cryptocurrencyListing.name)
            Text(text = cryptocurrencyListing.symbol)
        }
    }
}