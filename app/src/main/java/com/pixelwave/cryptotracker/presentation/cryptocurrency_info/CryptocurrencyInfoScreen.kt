package com.pixelwave.cryptotracker.presentation.cryptocurrency_info

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelwave.cryptotracker.presentation.VolumeChangeItem
import com.pixelwave.cryptotracker.util.formatPrice
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun CryptocurrencyInfoScreen(
    symbol: String,
    viewModel: CryptocurrencyInfoViewModel = hiltViewModel()
) {

    val state = viewModel.state
    if (state.error == null) {
        Column {
            if (state.cryptocurrencyListing != null) {
                Row(
                    Modifier
                        .padding(16.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = state.cryptocurrencyListing.name,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                        )
                        Text(
                            text = state.cryptocurrencyListing.price.formatPrice(),
                            style = MaterialTheme.typography.h4,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                        )
                        VolumeChangeItem(
                            change = state.cryptocurrencyListing.change,
                            modifier = Modifier.align(Alignment.Start)
                        )
                    }
                    IconButton(
                        onClick = {
                            viewModel.onToggleFavorite()
                        }, modifier = Modifier
                            .size(34.dp)
                            .padding(horizontal = 4.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = "is crypto favorite",
                            tint = if (state.cryptocurrencyListing.isFavorite) MaterialTheme.colors.primary else Color.Gray,
                        )
                    }
                }
            }
            state.data.let { data ->
                if (data.isNotEmpty()) {
                    PriceChart(
                        data,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error
            )
        }
    }
}