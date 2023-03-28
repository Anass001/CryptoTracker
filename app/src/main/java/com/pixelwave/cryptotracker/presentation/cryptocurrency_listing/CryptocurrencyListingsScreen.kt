package com.pixelwave.cryptotracker.presentation.cryptocurrency_listing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun CryptocurrencyListingsScreen(
    navigator: DestinationsNavigator,
    viewModel: CryptocurrencyListingsViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.cryptocurrencies.size) { i ->
                val cryptocurrency = state.cryptocurrencies[i]
                CryptocurrencyListingItem(
                    cryptocurrencyListing = cryptocurrency,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                        }
                )
            }
        }
    }
}
