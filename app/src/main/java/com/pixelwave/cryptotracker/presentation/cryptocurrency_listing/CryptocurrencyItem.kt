package com.pixelwave.cryptotracker.presentation.cryptocurrency_listing

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pixelwave.cryptotracker.domain.model.CryptocurrencyListing

@Composable
fun CryptocurrencyListingItem(
    cryptocurrencyListing: CryptocurrencyListing,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = cryptocurrencyListing.name,
                modifier = Modifier.padding(bottom = 4.dp),
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = MaterialTheme.typography.h6.fontWeight
                )
            )
            Text(
                text = cryptocurrencyListing.symbol,
                style = MaterialTheme.typography.body1
            )
        }
        Column(modifier = Modifier) {
            Text(
                text = "$425.18",
                modifier = Modifier.padding(bottom = 4.dp),
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "1.29%",
                style = MaterialTheme.typography.body1
            )
        }
    }
}
