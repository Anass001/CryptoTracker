package com.pixelwave.cryptotracker.presentation.cryptocurrency_listing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelwave.cryptotracker.presentation.destinations.CryptocurrencyInfoScreenDestination
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
                            navigator.navigate(
                                CryptocurrencyInfoScreenDestination(
                                    symbol = cryptocurrency.symbol
                                )
                            )
                        }
                )
            }
        }
    }
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}