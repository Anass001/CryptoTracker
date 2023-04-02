package com.pixelwave.cryptotracker.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NorthEast
import androidx.compose.material.icons.outlined.SouthEast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pixelwave.cryptotracker.ui.theme.Green
import com.pixelwave.cryptotracker.ui.theme.Red
import com.pixelwave.cryptotracker.util.formatChange

@Composable
fun VolumeChangeItem(modifier: Modifier = Modifier, change: Double) {
    Row(modifier = modifier) {
        Icon(
            imageVector = if (change > 0) Icons.Outlined.NorthEast else Icons.Outlined.SouthEast,
            contentDescription = null,
            tint = if (change < 0) Red else Green,
            modifier = Modifier
                .size(24.dp)
                .alignByBaseline()
                .padding(horizontal = 4.dp)
        )
        Text(
            text = change.formatChange(),
            color = if (change < 0) Red else Green,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Medium
        )
    }
}