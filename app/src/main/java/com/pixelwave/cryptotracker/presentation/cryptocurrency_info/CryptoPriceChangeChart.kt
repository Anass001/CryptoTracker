package com.pixelwave.cryptotracker.presentation.cryptocurrency_info

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pixelwave.cryptotracker.domain.model.OHLCVTimeseries
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun formatDate(date: LocalDate): String {
    return date.format(DateTimeFormatter.ofPattern("dd MMM"))
}

@Composable
fun PriceChart(data: List<OHLCVTimeseries>, modifier: Modifier = Modifier) {

    val mData = remember { data.reversed() }
    val minPrice = remember { mData.minOfOrNull { it.price } ?: 0.0 }
    val graphData = remember { mData.map { (it.price - minPrice) } }

    Canvas(modifier = modifier) {
        val xSpacing = (size.width - 100f) / 6f
        val yRatio =
            (graphData.maxOfOrNull { it }?.let { ((size.width - 100f)).div(it) })?.times(9f / 16f)
                ?: 0.0

        drawContext.canvas.nativeCanvas.apply {
            drawText(
                formatDate(mData[0].date),
                50f,
                size.height - 100f,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.BLACK
                    textAlign = android.graphics.Paint.Align.LEFT
                    textSize = density.run { 12.sp.toPx() }
                }
            )
            drawText(
                formatDate(mData[3].date),
                size.width / 2,
                size.height - 100f,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.BLACK
                    textAlign = android.graphics.Paint.Align.CENTER
                    textSize = density.run { 12.sp.toPx() }
                }
            )
            drawText(
                formatDate(mData[6].date),
                size.width - 50f,
                size.height - 100f,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.BLACK
                    textAlign = android.graphics.Paint.Align.RIGHT
                    textSize = density.run { 12.sp.toPx() }
                }
            )
        }

        // draw a stroke path line
        val strokePath = Path().apply {
            moveTo(50f, size.height - 200f)
            graphData.forEachIndexed { index, item ->
                lineTo(
                    ((index).toFloat() * xSpacing) + 50f,
                    size.height - ((item.toFloat()) * yRatio.toFloat()) - 200f
                )
            }
        }

        val fillPath = android.graphics.Path(strokePath.asAndroidPath())
            .asComposePath()
            .apply {
                lineTo(size.width - 50f, size.height - 100f)
                lineTo(50f, size.height - 100f)
                close()
            }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Green.copy(alpha = 0.5f),
                    Color.Transparent
                ),
                endY = size.height - 100f
            )
        )

        drawPath(
            path = strokePath,
            color = Color.Green,
            style = Stroke(width = 10f)
        )
    }
}


@Composable
@Preview
private fun GraphPreview() {
    PriceChart(data = emptyList(), modifier = Modifier.fillMaxSize())
}