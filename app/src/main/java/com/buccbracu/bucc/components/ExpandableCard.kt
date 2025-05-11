package com.buccbracu.bucc.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.backend.remote.models.TaskData
import com.buccbracu.bucc.components.AnimatedIcon
import com.buccbracu.bucc.shortForm
import com.buccbracu.bucc.ui.theme.paletteGreen

@Composable
fun ExpandableCard(
    title: String,
    description: String,
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
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            expand = !expand
        },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHigh)

    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column{
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = description,
                                fontWeight = FontWeight.W300
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
            AnimatedVisibility(
                visible = expand,
                enter = expandVertically(animationSpec = tween(300)) + fadeIn(animationSpec = tween(300)),
                exit = shrinkVertically(animationSpec = tween(300)) + fadeOut(animationSpec = tween(300))
            ) {
                content()
            }

        }
    }
}