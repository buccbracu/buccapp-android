package com.buccbracu.bucc.components.blog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.FontDownload
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.FontDownload
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.remote.models.Blog
import com.buccbracu.bucc.backend.viewmodels.BlogVM
import com.buccbracu.bucc.components.MovableFloatingActionButton
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import com.buccbracu.bucc.ui.theme.backgroundDark
import kotlinx.coroutines.launch

/*
1. Image view
 */

@Composable
fun BlogView(
    id: String?,
    back: () -> Unit
) {
    val blogvm: BlogVM = hiltViewModel()
    val scope = rememberCoroutineScope()
    var blog by remember{
        mutableStateOf<Blog?>(null)
    }
    var isLoading by remember{
        mutableStateOf(true)
    }
    val (fontSize, setFontSize) = remember { mutableStateOf(14.sp) }
    LaunchedEffect(Unit) {
        scope.launch {
            id?.let {
                blogvm.getBlog(it){
                    blog = it
                    isLoading = false
                }
            }
        }
    }
    if(isLoading){
        NoButtonCircularLoadingDialog(
            title = "Loading Blog",
            message = "Please wait..."
        )
    }
    else{
        blog?.let {
            CompositionLocalProvider(LocalContentColor provides Color.White){
                Column(
                    modifier = Modifier
                        .background(backgroundDark)
                        .fillMaxSize()
                        .padding(top = 30.dp, bottom = 50.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Text(
                            text = "Aa"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        IconButton(
                            onClick = {
                                setFontSize((fontSize.value - 2).sp)
                            },
                            enabled = fontSize > 14.sp
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Remove,
                                contentDescription = "Decrease text size"
                            )
                        }
                        IconButton(
                            onClick = {
                                setFontSize((fontSize.value + 2).sp)
                            },
                            enabled = fontSize < 22.sp
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "Increase text size"
                            )
                        }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .padding(top = 0.dp)
                    ) {
                        item {
                            BlogHeader(blog!!)

                            Spacer(modifier = Modifier.height(20.dp))


                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                            )
                            {
                                RenderContent(blog!!.content, fontSize = fontSize)
                            }
                        }
                    }
                }
            }
            MovableFloatingActionButton(
                onClick = {
                    back()
                },
                icon = Icons.Filled.ArrowBackIosNew
            )
        }
    }
}

