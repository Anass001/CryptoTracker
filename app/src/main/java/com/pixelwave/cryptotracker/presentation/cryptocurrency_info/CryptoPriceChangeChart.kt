package com.pixelwave.cryptotracker.presentation.cryptocurrency_info

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pixelwave.cryptotracker.domain.model.OHLCVTimeseries

//@Composable
//fun Graph(data: List<OHLCVTimeseries>, modifier: Modifier = Modifier) {
//    val prices = data.map { it.price }
//    val dates = data.map { it.date }
//    Box(
//        modifier = modifier
//            .background(Color.White)
//            .padding(horizontal = 8.dp, vertical = 12.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Canvas(
//            modifier = Modifier.fillMaxSize(),
//            onDraw = {
//                val origin = Offset(x = 50f, y = size.height - 50f)
//                val xMax = 70f
//                val yMax = prices.maxOrNull() ?: 0f
//
//                val scaleX = (size.width - 100) / xMax
//                val scaleY = (size.height - 100) / yMax.toDouble()
//
//                // Draw x and y axes
//                drawLine(
//                    Color.Cyan,
//                    start = origin,
//                    end = Offset(x = size.width - 50f, y = origin.y)
//                )
//
//                // Draw data points
//                prices.forEachIndexed { index, x ->
//                    val y = prices[index]
//                    if (index < data.size - 1) {
//                        drawLine(
//                            color = Color.Blue,
//                            strokeWidth = 5f,
//                            start = Offset(x = origin.x + x * scaleX, y = origin.y - y * scaleY),
//                            end = Offset(
//                                x = origin.x + data[index + 1] * scaleX,
//                                y = origin.y - prices[index + 1] * scaleY
//                            )
//                        )
//                    }
//                }
//            }
//        )
//    }
//
//}
//
//
