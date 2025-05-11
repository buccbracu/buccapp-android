package com.buccbracu.bucc.components.blog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.buccbracu.bucc.backend.remote.models.Blog
import com.buccbracu.bucc.formatDate
import com.buccbracu.bucc.ui.theme.backgroundDark

@Composable
fun BlogHeader(blog: Blog){

    var imageHeight by remember { mutableIntStateOf(0) }
    val labelWeight = FontWeight(300)
    val valueWeight = FontWeight(600)
    val titleWeight = FontWeight.W900

    Box(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()

    ){
        AsyncImage(
            model = blog.featuredImage,
            contentDescription = "Blog Image",
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .aspectRatio(16/9f)
                .onGloballyPositioned { coordinates ->
                    imageHeight = coordinates.size.height
                }
                .graphicsLayer {
                    alpha = 1f
                }
                .padding(bottom = 10.dp)
                ,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Transparent.copy(alpha = 0.6f),
                            backgroundDark.copy(alpha = 0.99f),
                            backgroundDark,
                            ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY // Makes the gradient extend fully
                        )
                )
                .heightIn(min = 250.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            // Title
            Text(
                text = blog.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = titleWeight,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Written By: ",
                    fontWeight = labelWeight,
                    color = Color.White,
                    fontSize = 12.sp
                )
                Text(
                    text = "${blog.author.authorName}",
                    fontWeight = valueWeight,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Designation: ",
                    color = Color.White,
                    fontWeight = labelWeight,
                    fontSize = 12.sp
                )
                Text(
                    text = "${blog.author.authorDesignation}",
                    fontWeight = valueWeight,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Department: ",
                    color = Color.White,
                    fontWeight = labelWeight,
                    fontSize = 12.sp
                )
                Text(
                    text = "${blog.author.authorDepartment}",
                    fontWeight = valueWeight,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
            Row {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Published On: ",
                        color = Color.White,
                        fontWeight = labelWeight,
                        fontSize = 12.sp
                    )
                    Text(
                        text = formatDate(blog.createdDate.slice(0..9)),
                        fontWeight = valueWeight,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Category: ",
                    color = Color.White,
                    fontWeight = labelWeight,
                    fontSize = 12.sp
                )
                Text(
                    text = blog.category!!,
                    fontWeight = valueWeight,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}