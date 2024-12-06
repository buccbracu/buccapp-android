package com.buccbracu.bucc.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.backend.remote.models.TaskData
import com.buccbracu.bucc.shortForm
import com.buccbracu.bucc.ui.theme.palette3violet1
import com.buccbracu.bucc.ui.theme.palette3violet2
import com.buccbracu.bucc.ui.theme.paletteGreen

@Composable
fun ExpandableCard(
    task: TaskData,
    content: @Composable () -> Unit
){

    var expand by remember{
        mutableStateOf(false)
    }
    val rotation by animateFloatAsState(
        targetValue = if (expand) 180f else 0f
    )

    ElevatedCard(
        modifier = Modifier

            .padding(15.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            expand = !expand
        }

    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column{
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Start
                        ){
                            Icon(
                                imageVector = Icons.Filled.CalendarMonth,
                                contentDescription = "Deadline",
                                tint = MaterialTheme.colorScheme.errorContainer,
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Due By: ${task.deadline!!.slice(0..9)}",
                                fontWeight = FontWeight.W400,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        Icon(
                            imageVector = Icons.Filled.Circle,
                            contentDescription = "Status",
                            tint =
                                when(task.status){
                                    "pending" -> Color(0xFFEF5350) // Red 400
                                    "accepted" -> Color(0xFF29B6F6) // Light Blue 400
                                    "completed" -> Color(0xFF66BB6A) // Green 400
                                    else -> Color(0xFFB0BEC5) // Blue Grey 300 (default for unknown status)
                                },
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = task.taskTitle,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.W900,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "${shortForm(task.fromDept!!)} (${shortForm(task.fromDesignation!!)})",
                                fontWeight = FontWeight.W500
                            )
                            AnimatedIcon(
                                initialValue = -10f,
                                targetValue = 10f,
                                icon = Icons.Filled.KeyboardDoubleArrowRight,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                            )
                            Text(
                                text = "${shortForm(task.toDept!!)} (${shortForm(task.toDesignation!!)})",
                                fontWeight = FontWeight.W500
                            )
                        }
                        IconButton(
                            modifier = Modifier
                                .rotate(rotation)
                                .size(25.dp),
                            onClick = {
                                expand = !expand
                            },
                            enabled = true,
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "DropDown",
                                tint = paletteGreen
                            )
                        }
                    }
                }
            }
            if(expand){
                content()
            }

        }
    }
}