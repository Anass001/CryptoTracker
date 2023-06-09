package com.pixelwave.cryptotracker.presentation.cryptocurrency_listing

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NorthEast
import androidx.compose.material.icons.outlined.SouthEast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pixelwave.cryptotracker.domain.model.CryptocurrencyListing
import com.pixelwave.cryptotracker.presentation.VolumeChangeItem
import com.pixelwave.cryptotracker.ui.theme.Green
import com.pixelwave.cryptotracker.ui.theme.Red
import com.pixelwave.cryptotracker.util.formatChange
import com.pixelwave.cryptotracker.util.formatPrice
import com.pixelwave.cryptotracker.util.getResId
import java.text.DecimalFormat

@Composable
fun CryptocurrencyListingItem(
    cryptocurrencyListing: CryptocurrencyListing,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            AsyncImage(
                model = getResId(
                    cryptocurrencyListing.symbol.lowercase(),
                    com.pixelwave.cryptotracker.R.drawable::class.java
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(32.dp)
                    .align(Alignment.CenterVertically)
            )
            Column {
                Text(
                    text = cryptocurrencyListing.name,
                    modifier = Modifier.padding(bottom = 4.dp),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                )
                Text(
                    text = cryptocurrencyListing.symbol,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                )
            }
        }
        Column(modifier = Modifier) {
            Text(
                text = cryptocurrencyListing.price.formatPrice(),
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .align(Alignment.End),
                style = MaterialTheme.typography.body1,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
            )
            VolumeChangeItem(
                change = cryptocurrencyListing.change,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}
