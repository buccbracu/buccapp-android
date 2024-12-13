package com.buccbracu.bucc.components


import android.graphics.Color
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopActionBar(
    drawerState: DrawerState,
    currentPage: String = ""
){
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxWidth()
            .height(50.dp)
//            .border(1.dp, androidx.compose.ui.graphics.Color.Green)

    ){

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(0.15f)
//                .border(1.dp, androidx.compose.ui.graphics.Color.Red )
        ) {
            IconButton(onClick = {
                scope.launch{
                    drawerState.apply{
                        if (isClosed){
                            open()
                        }
                        else{
                            close()
                        }
                    }
                }
            }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier
                        .size(25.dp)
                )
            }



        }

        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = currentPage
            )
        }


    }
}