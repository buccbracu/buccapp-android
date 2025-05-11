package com.buccbracu.bucc.components.dashboard.taskoverview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.backend.remote.models.TaskOverview
import com.buccbracu.bucc.ui.theme.palette2DarkRed

@Composable
fun TaskGraph(taskCounts: TaskOverview, modifier: Modifier = Modifier) {
    val barData = listOf(
        Pair("Accepted", taskCounts.accepted to Color(0xFF29B6F6)), // Green
        Pair("Pending", taskCounts.pending to Color(0xFFEF5350)),  // Amber
        Pair("Completed", taskCounts.completed to Color(0xFF66BB6A)), // Blue
        Pair("Due TMR", taskCounts.dueTomorrow to palette2DarkRed) // Red
    )

    val maxValue =taskCounts.accepted + taskCounts.pending+ taskCounts.completed

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            barData.forEach { (label, data) ->
                val (value, color) = data
                Bar(label, value, maxValue, color)
            }
        }
    }
}

@Composable
fun Bar(label: String, value: Int, maxValue: Int, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .height(200.dp) // Ensure all bars are within the same height
            .padding(horizontal = 8.dp)
    ) {
        Canvas(
            modifier = Modifier
                .width(30.dp)
                .weight(1f) // Make it fill proportional to remaining space
        ) {
            val barHeight = if (maxValue > 0) size.height * (value.toFloat() / maxValue) else 0f
            drawRect(
                color = color,
                size = androidx.compose.ui.geometry.Size(width = size.width, height = barHeight),
                topLeft = Offset(x = 0f, y = size.height - barHeight)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = value.toString(),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = label,
            modifier = Modifier.padding(top = 4.dp),
            fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground
        )
    }
}
