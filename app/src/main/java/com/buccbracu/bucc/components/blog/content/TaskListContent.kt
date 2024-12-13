package com.buccbracu.bucc.components.blog.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.backend.remote.models.Content

@Composable
fun TaskListContent(content: Content, fontSize: TextUnit = 14.sp) {
    val contentItems = content.content
    Column() {
        contentItems?.forEach { items ->
            items.content?.forEach{ taskItem ->
                Row(

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        checked = false,
                        onCheckedChange = {},
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    taskItem.content?.forEach { paragraph ->
                        paragraph.text?.let {
                            Text(
                                text = it,
                                fontSize = fontSize
                            )
                        }
                    }
                }
            }

        }
    }
}
