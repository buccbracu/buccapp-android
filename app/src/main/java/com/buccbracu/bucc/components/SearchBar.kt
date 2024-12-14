package com.buccbracu.bucc.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.automirrored.outlined.Backspace
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.outlined.ScreenSearchDesktop
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    query: String,
    onChange: (String) -> Unit,
    label: String,
    placeholder: String,
    onClear: () -> Unit,
    leadingIcon: ImageVector = Icons.Outlined.Search,
    isLeadingIconButton: Boolean = false,
    leadingIconAction: () -> Unit ={},
    trailingIcon: ImageVector = Icons.AutoMirrored.Outlined.Backspace,
    modifier: Modifier = Modifier
        .padding(horizontal = 10.dp)
        .fillMaxWidth()
){
    OutlinedTextField(
        value = query,
        onValueChange = onChange,
        modifier = modifier,
        label = {
            Text(label)
        },
        placeholder = {
            Text(
                placeholder,
                fontSize = 12.sp,
                fontWeight = FontWeight(300)
            )
        },
        shape = RoundedCornerShape(15.dp),
        trailingIcon = {
            IconButton(
                onClick = onClear
            ) {
                Icon(
                    imageVector = trailingIcon ,
                    contentDescription = "Backspace",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        },
        leadingIcon = {
            if(isLeadingIconButton){
                IconButton(
                    onClick = leadingIconAction
                ) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = "Search",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }
            else{
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }

    )
}